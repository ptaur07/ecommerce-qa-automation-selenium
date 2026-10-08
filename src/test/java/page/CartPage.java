package page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public CheckoutPage clickCheckout() {

        // Wait for checkout button
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutButton));

        // Scroll to button
        driver.findElement(checkoutButton)
                .click();

        // Wait for checkout page
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        return new CheckoutPage(driver);
    }
}