package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    private By username =
            By.id("user-name");

    private By password =
            By.id("password");

    private By loginButton =
            By.id("login-button");

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    public void enterUsername(String value) {

        driver.findElement(username)
                .sendKeys(value);
    }

    public void enterPassword(String value) {

        driver.findElement(password)
                .sendKeys(value);
    }

    public ProductsPage clickLogin() {

        driver.findElement(loginButton)
                .click();

        return new ProductsPage(driver);
    }

    public ProductsPage login(
            String user,
            String pass) {

        enterUsername(user);
        enterPassword(pass);

        return clickLogin();
    }
}