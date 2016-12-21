package pages.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by Victoria on 18.12.2016.
 */
public class HeaderFragment extends AbstractFragment {

    @FindBy(xpath = ".//a[contains(@class, 'top_home_link fl_l')]")
    private WebElement homeLink;

    @FindBy(xpath = ".//input[@id='ts_input']")
    private WebElement searchInput;

    @FindBy(xpath = ".//a[@id='ts_search_link']")
    private WebElement linkAllResults;

    @FindBy(id = "top_notify_btn")
    private WebElement notifyButton;

    @FindBy(id = "top_audio")
    private WebElement audioButton;

    @FindBy(id = "top_profile_link")
    private WebElement profileLink;

    @FindBy(id = "top_logout_link")
    private WebElement logoutLink;

    @FindBy(xpath = ".//button[contains(@class,'audio_page_player_play')]")
    private WebElement playButton;

    public HeaderFragment(WebDriver driver) {
        super(driver);
    }

    public void clickOnHomeLink() {
        homeLink.click();
    }

    public void setSearchInput(String strForSearch) {
        searchInput.clear();
        searchInput.sendKeys(strForSearch);
    }

    public void clickOnLinkAllResults() {
        linkAllResults.click();
    }

    public void clickOnNotifyButton() {
        notifyButton.click();
    }

    public void clickOnAudioButton() {
        audioButton.click();
    }

    public void clickOnProfileLink() {
        profileLink.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void clickOnPlayButton() {
        playButton.click();
    }

    public void clickOnSearchInput() {
        searchInput.clear();
        searchInput.click();
    }
}
