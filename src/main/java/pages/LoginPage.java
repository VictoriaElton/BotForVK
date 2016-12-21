package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ResourceBundle;

/**
 * Created by Victoria on 18.12.2016.
 */
public class LoginPage extends AbstractPage {
    @FindBy(id = "email")
    private WebElement loginInput;

    @FindBy(id = "pass")
    private WebElement passwordInput;

    @FindBy(id = "login_button")
    private WebElement logInButton;

    private static final String URL = "https://vk.com/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(URL);
    }

    public void setLoginInput(String login) {
        loginInput.clear();
        loginInput.sendKeys(login);
    }

    public void setPasswordInput(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickOnLogInButton() {
        logInButton.click();

        //if (driver.getTitle().equals("Проверка безопасности")) {
            driver.findElement(By.id("authcheck_code")).sendKeys(ResourceBundle.getBundle("application").getString("reserveCode"));
            driver.findElement(By.id("login_authcheck_submit_btn")).click();
        //}
    }
}
