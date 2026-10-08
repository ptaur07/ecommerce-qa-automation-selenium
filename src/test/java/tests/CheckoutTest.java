package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Base.BaseTest;
import listeners.RetryAnalyzer;
import page.*;
import utils.ConfigReader;
import utils.ExtentManager;
import utils.ScreenshotUtil;

import org.testng.Assert;
import org.testng.annotations.*;

public class CheckoutTest extends BaseTest {

    private ExtentReports extent;
    private ExtentTest test;

    @BeforeClass
    public void startReport() {

        extent =
                ExtentManager.getInstance();
    }

    @Test(
        retryAnalyzer =
            RetryAnalyzer.class
    )
    public void completeOrderTest() {

        test =
                extent.createTest(
                        "Complete E2E Order");

        try {

            test.info("Opening application");

            LoginPage loginPage =
                    new LoginPage(driver);

            test.info("Logging in");

            ProductsPage productsPage =
                    loginPage.login(
                            ConfigReader.get("username"),
                            ConfigReader.get("password"));

            test.info(
                    "Adding Sauce Labs Backpack");

            productsPage.addBackpackToCart();

            test.info("Opening cart");

            CartPage cartPage =
                    productsPage.openCart();

            test.info("Opening checkout");

            CheckoutPage checkoutPage =
                    cartPage.clickCheckout();

            test.info(
                    "Entering customer information");

            checkoutPage.enterCustomerDetails(
                    "Pooja",
                    "Taur",
                    "411001");

            checkoutPage.clickContinue();

            test.info("Completing order");

            checkoutPage.clickFinish();

            String message =
                    checkoutPage
                            .getConfirmationMessage();

            Assert.assertEquals(
                    message,
                    "Thank you for your order!");

            test.pass(
                    "Order completed successfully");

        } catch (Exception e) {

            String screenshot =
                    ScreenshotUtil.takeScreenshot(
                            driver,
                            "completeOrderTest");

            test.fail(
                    "Test failed: "
                    + e.getMessage());

            if (screenshot != null) {

                test.addScreenCaptureFromPath(
                        screenshot);
            }

            throw e;

        } finally {

            extent.flush();
        }
    }
}