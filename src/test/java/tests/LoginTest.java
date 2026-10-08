package tests;

import Base.BaseTest;
import page.LoginPage;
import page.ProductsPage;
import utils.ConfigReader;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage =
                new LoginPage(driver);

        ProductsPage productsPage =
                loginPage.login(
                        ConfigReader.get("username"),
                        ConfigReader.get("password"));

        Assert.assertTrue(
                driver.getCurrentUrl()
                        .contains("inventory.html"));
    }
}