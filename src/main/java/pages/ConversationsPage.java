package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.fragments.SideBarFragment;

/**
 * Created by Victoria on 19.12.2016.
 */
public class ConversationsPage extends AbstractPage {
    @FindBy(xpath = ".//input[@id='im_dialogs_search']")
    private WebElement dialogsSearch;

    @FindBy(xpath = ".//*[@id='im_editable0']")
    private WebElement messageInput;

    @FindBy(xpath = ".//button[contains(@class,'im-chat-input--send')]")
    private WebElement sendButton;

    public ConversationsPage(WebDriver driver) {
        super(driver);
    }

    public SideBarFragment getSideBarFragment() {
        return new SideBarFragment(driver);
    }

    public void setDialogsSearch(String textForSearch){
        dialogsSearch.sendKeys(textForSearch);
    }

    public void selectDialogByUserName(String userName){
        driver.findElement(By.xpath(".//*[contains(text(),'"+userName+"')]/ancestor::*[@class='nim-dialog--cw']")).click();
    }

    public void setMessageInput(String text){
        messageInput.sendKeys(text);
    }

    public void clickOnSendButton(){
        sendButton.click();
    }
}
