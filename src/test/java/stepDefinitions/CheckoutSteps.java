package stepDefinitions;

import org.testng.Assert;

import Base.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import page.LoginPage;
import page.ProductsPage;
import page.CartPage;
import page.CheckoutPage;

import utils.ConfigReader;

public class CheckoutSteps extends BaseTest {

    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @Given("user is logged into the ecommerce application")
    public void user_is_logged_into_the_ecommerce_application() {

        loginPage = new LoginPage(driver);

        productsPage = loginPage.login(
                ConfigReader.get("username"),
                ConfigReader.get("password")
        );
    }

    @When("user adds Sauce Labs Backpack to the cart")
    public void user_adds_sauce_labs_backpack_to_the_cart() {

        productsPage.addBackpackToCart();
    }

    @When("user opens the cart")
    public void user_opens_the_cart() {

        cartPage = productsPage.openCart();
    }

    @When("user proceeds to checkout")
    public void user_proceeds_to_checkout() {

        checkoutPage = cartPage.clickCheckout();
    }

    @When("user enters customer details")
    public void user_enters_customer_details() {

        checkoutPage.enterCustomerDetails(
                "Pooja",
                "Taur",
                "411001"
        );

        checkoutPage.clickContinue();
    }

    @When("user completes the order")
    public void user_completes_the_order() {

        checkoutPage.clickFinish();
    }

    @Then("order confirmation should be displayed")
    public void order_confirmation_should_be_displayed() {

        String message =
                checkoutPage.getConfirmationMessage();

        Assert.assertEquals(
                message,
                "Thank you for your order!"
        );
    }
}