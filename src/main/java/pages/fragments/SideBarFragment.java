package pages.fragments;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Victoria on 18.12.2016.
 */
public class SideBarFragment extends AbstractFragment {
    @FindBy(xpath = ".//li[@id='l_pr']/a")
    private WebElement myProfileLink;

    @FindBy(xpath = ".//li[@id='l_nwsf']/a")
    private WebElement newsLink;

    @FindBy(xpath = ".//li[@id='l_msg']/a")
    private WebElement messagesLink;

    @FindBy(xpath = ".//li[@id='l_fr']/a")
    private WebElement friendsLink;

    @FindBy(xpath = ".//li[@id='l_gr']/a")
    private WebElement communitiesLink;

    @FindBy(xpath = ".//li[@id='l_ph']/a")
    private WebElement photosLink;

    @FindBy(xpath = ".//li[@id='l_aud']/a")
    private WebElement audiosLink;

    @FindBy(xpath = ".//li[@id='l_vid']/a")
    private WebElement videosLink;

    @FindBy(xpath = ".//li[@id='l_ap']/a")
    private WebElement gamesLink;

    @FindBy(xpath = ".//li[@id='l_mk']/a")
    private WebElement marketLink;

    @FindBy(xpath = ".//li[@id='l_fav']/a")
    private WebElement bookmarksLink;

    @FindBy(xpath = ".//li[@id='l_doc']/a")
    private WebElement documentsLink;

    public SideBarFragment(WebDriver driver) {
        super(driver);
    }

    public void clickOnMyProfileLink() {
        myProfileLink.click();
    }

    public void clickOnNewsLink() {
        newsLink.click();
    }

    public void clickOnMessagesLink() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", messagesLink);
    }

    public void clickOnFriendsLink() {
        friendsLink.click();
    }

    public void clickOnCommunitiesLink() {
        communitiesLink.click();
    }

    public void clickOnPhotosLink() {
        photosLink.click();
    }

    public void clickVideosLink() {
        videosLink.click();
    }

    public void clickOnGamesLink() {
        gamesLink.click();
    }

    public void clickOnMarketLink() {
        marketLink.click();
    }

    public void clickOnBookmarksLink() {
        bookmarksLink.click();
    }

    public void clickOnDocumentsLink() {
        documentsLink.click();
    }
}
