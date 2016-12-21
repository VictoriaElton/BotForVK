package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import pages.fragments.SideBarFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victoria on 18.12.2016.
 */
public class UserPage extends AbstractPage {

    @FindBys(@FindBy(xpath = ".//div[@class='_post_content']"))
    private List<WebElement> posts;

    @FindBy(xpath = ".//*[@class='page_name']")
    private WebElement firstAndLastName;

    @FindBy(xpath = ".//*[contains(text(),'Friends') or contains(text(),'Друзья')]/following-sibling::*[contains(@class,'header_count')]")
    private WebElement amountFriends;

    @FindBy(xpath = ".//*[@id='post_field']")
    private WebElement postField;

    @FindBy(xpath = ".//*[@id='send_post']")
    private WebElement sendPostButton;

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public SideBarFragment getSideBarFragment() {
        return new SideBarFragment(driver);
    }

    public int getPostCount() {
        return posts.size();
    }

    public String getPostText(int numberOfPost) {
        return posts.get(numberOfPost - 1).findElement(By.xpath(".//div[@class='wall_post_text']")).getText();
    }

    public String getCommentText(int numberOfPost, int numberOfComment) {
        if (posts.get(numberOfPost - 1).findElements(By.xpath(".//div[@class='wall_reply_text']")).get(numberOfComment - 1) != null) {
            return posts.get(numberOfPost - 1).findElements(By.xpath(".//div[@class='wall_reply_text']")).get(numberOfComment - 1).getText();
        } else {
            return "No such comment #" + numberOfComment;
        }
    }

    public ArrayList<String> getContacts() {
        ArrayList<String> contacts = new ArrayList<String>();
        List<WebElement> keys = driver.findElements(By.xpath("//*[contains(text(),'Contact information')]/ancestor::div[contains(@class,'profile_info_block')]//div[contains(@class,'fl_l')]"));
        List<WebElement> values = driver.findElements(By.xpath("//*[contains(text(),'Contact information')]/ancestor::div[contains(@class,'profile_info_block')]//div[@class='labeled']"));

        for (int i = 0; i < keys.size(); i++) {
            contacts.add(keys.get(i).getText() + ": " + values.get(i).getText());
        }

        return contacts;
    }

    public String getLastComment(int numberOfPost) throws StaleElementReferenceException {
        int size = posts.get(numberOfPost - 1).findElements(By.xpath(".//div[@class='wall_reply_text']")).size();
        if (size != 0) {
            return posts.get(numberOfPost - 1).findElements(By.xpath(".//div[@class='wall_reply_text']")).get(size - 1).getText();
        } else {
            return "No comments";
        }
    }

    public void sendComment(int numberOfPost, String text) throws StaleElementReferenceException,NoSuchElementException {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        if (posts.get(numberOfPost - 1).findElements(By.xpath(".//div[@class='wall_reply_text']")).size() != 0) {
            jse.executeScript("arguments[0].click();", posts.get(numberOfPost - 1).findElement(By.xpath(".//div[@class='reply_fakebox']")));
        } else {
            jse.executeScript("arguments[0].click();", posts.get(numberOfPost - 1).findElement(By.xpath(".//a[@class='reply_link']")));
        }
        posts.get(numberOfPost - 1).findElement(By.xpath(".//*[contains(@class,'submit_post_field')]")).sendKeys(text);
        jse.executeScript("arguments[0].click();", posts.get(numberOfPost - 1).findElement(By.xpath(".//button[contains(@id,'reply_button')]")));
    }

    public String getFirstAndLastName() {
        return firstAndLastName.getText();
    }

    public int getAmountFriends() {
        return Integer.parseInt(amountFriends.getText());
    }

    public void setPostField(String text) {
        postField.sendKeys(text);
    }

    public void clickOnSendPostButton() {
        new Actions(driver).moveToElement(sendPostButton).click().perform();
    }
}
