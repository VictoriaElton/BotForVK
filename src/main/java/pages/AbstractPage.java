package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.fragments.HeaderFragment;
import pages.fragments.SideBarFragment;

/**
 * Created by Victoria on 18.12.2016.
 */
public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public HeaderFragment getHeaderFragment() {
        return new HeaderFragment(driver);
    }

}
