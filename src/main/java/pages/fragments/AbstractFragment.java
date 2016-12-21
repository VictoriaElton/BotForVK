package pages.fragments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Victoria on 18.12.2016.
 */
public abstract class AbstractFragment {
    protected WebDriver driver;

    public AbstractFragment(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
