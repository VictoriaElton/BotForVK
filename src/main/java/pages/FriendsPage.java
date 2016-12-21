package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.fragments.SideBarFragment;

import java.util.List;

/**
 * Created by Victoria on 20.12.2016.
 */
public class FriendsPage extends AbstractPage {
    @FindBy(xpath = ".//*[contains(@id,'friends_user_row')]")
    private List<WebElement> friendsList;

    public FriendsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnFriend(int numberOfFriend){
        friendsList.get(numberOfFriend-1).findElement(By.xpath(".//*[contains(@class,'friends_field_title')]//a")).click();
    }

    public SideBarFragment getSideBarFragment() {
        return new SideBarFragment(driver);
    }
}
