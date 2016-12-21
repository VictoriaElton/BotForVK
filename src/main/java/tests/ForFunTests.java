package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.ConversationsPage;
import pages.FriendsPage;
import pages.LoginPage;
import pages.UserPage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Victoria on 19.12.2016.
 */
public class ForFunTests {
    private WebDriver driver;
    private LoginPage loginPage;
    private UserPage userPage;
    private FriendsPage friendsPage;
    private SoftAssert softAssert;
    private ConversationsPage conversationsPage;
    private File fileInfo;

    private static final String LOGIN = ResourceBundle.getBundle("application").getString("login");
    private static final String PASSWORD = ResourceBundle.getBundle("application").getString("password");
    private static final String EMAIL = ResourceBundle.getBundle("application").getString("email");

    /**
     * Default preconditions:
     * 1. Open FireFox browser
     * 2. Set implicit waits to 10 seconds
     * 3. Open application Login Page URL
     * 4. Sign in VK
     */
    @BeforeSuite
    public void beforeSuite() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        fileInfo = new File("fileInfo.txt");

        userPage = new UserPage(driver);
        conversationsPage = new ConversationsPage(driver);
        friendsPage = new FriendsPage(driver);
        loginPage = new LoginPage(driver);
        loginPage.open();

        loginPage.setLoginInput(LOGIN);
        loginPage.setPasswordInput(PASSWORD);
        loginPage.clickOnLogInButton();

        Assert.assertFalse(loginPage.getCurrentUrl().equals("Log in | VK"));
    }

    @BeforeMethod
    public void beforeMethod() {
        softAssert = new SoftAssert();
    }

    @BeforeGroups("friendTest")
    public void selectAndOpenFriend() {
        friendsPage.getSideBarFragment().clickOnFriendsLink();
        friendsPage.clickOnFriend(1);
    }

    @DataProvider
    public Object[][] comments() {
        return new Object[][]{
                {"Бот работает, а ты нет!"},
                {"Какой увлекательный пост^^ (нет)"},
                {"Безудержное."},
                {"Не забудь почистить комментарии, мне лень"}
        };
    }

    @Test(dataProvider = "comments", groups = "friendTest")
    public void commentAll(String text) {
        int number = new Random().nextInt(userPage.getPostCount() - 1);
        userPage.sendComment(number, text);
        Assert.assertEquals(userPage.getLastComment(number), text, "Last comment: " + userPage.getLastComment(number));
    }

    @DataProvider
    public Object[][] postText() {
        return new Object[][]{
                {"Привет, че как? "}
        };
    }

    @Test(dataProvider = "postText", groups = "friendTest")
    public void addPostOnWall(String text) {
        userPage.setPostField(text);
        userPage.clickOnSendPostButton();
        Assert.assertEquals(userPage.getPostText(1), text, "Text in last post: " + userPage.getPostText(1));
    }

    @Test(groups = "friendTest")
    public void saveFriendInfo() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(fileInfo);

        printWriter.write(userPage.getFirstAndLastName() + "\n");

        printWriter.write("Contact information:\n");
        ArrayList<String> contacts = userPage.getContacts();
        if (contacts.size() != 0) {
            for (int i = 0; i < contacts.size(); i++) {
                printWriter.write(contacts.get(i) + "\n");
            }
        } else {
            printWriter.write("None\n");
        }
        printWriter.write("Amount friends: \n");
        printWriter.write(userPage.getAmountFriends() + "\n");

        printWriter.flush();
        printWriter.close();
    }

    @BeforeGroups(value = "conversationsTests", dependsOnGroups = "friendTest")
    public void openConversations() {
        conversationsPage.getHeaderFragment().clickOnHomeLink();
        conversationsPage.getSideBarFragment().clickOnMessagesLink();
    }

    @Test(groups = "conversationsTests")
    public void sendInfoOnEmail() throws FileNotFoundException {
        conversationsPage.setDialogsSearch(EMAIL);
        conversationsPage.selectDialogByUserName(EMAIL);

        Scanner scanner = new Scanner(new FileReader(fileInfo));
        String text = scanner.nextLine();
        while (scanner.hasNext()) {
            text = text + " | " + scanner.nextLine();
        }
        scanner.close();

        conversationsPage.setMessageInput(text);
        conversationsPage.clickOnSendButton();
    }

    @AfterMethod
    public void takeScreenShot(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            snapScreenShot("failure", testResult.getName());
        } else if (testResult.getStatus() == ITestResult.SUCCESS) {
            snapScreenShot("passed", testResult.getName());
        }
    }

    /**
     * 1. Logout
     * 2. Close the browser
     */

    @AfterSuite
    public void afterSuite() {
        loginPage.getHeaderFragment().clickOnProfileLink();
        loginPage.getHeaderFragment().clickOnLogoutLink();
        driver.quit();
    }

    private void snapScreenShot(String folder, String name) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = "\\Snapshots\\" + folder + "\\" + name + "_" +
                new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + ".jpg";
        String filePath = System.getProperties().get("user.dir") + fileName;
        FileUtils.copyFile(scrFile, new File(filePath));
    }
}
