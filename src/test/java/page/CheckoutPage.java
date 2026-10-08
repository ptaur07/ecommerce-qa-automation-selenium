package page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");

    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");

    private By confirmationMessage = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterCustomerDetails(
            String firstNameValue,
            String lastNameValue,
            String postalCodeValue) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName))
                .clear();

        driver.findElement(firstName)
                .sendKeys(firstNameValue);

        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName))
                .clear();

        driver.findElement(lastName)
                .sendKeys(lastNameValue);

        wait.until(ExpectedConditions.visibilityOfElementLocated(postalCode))
                .clear();

        driver.findElement(postalCode)
                .sendKeys(postalCodeValue);
    }

    public void clickContinue() {

        // Make sure all fields contain values
        wait.until(ExpectedConditions.attributeToBe(
                firstName,
                "value",
                "Pooja"
        ));

        wait.until(ExpectedConditions.attributeToBe(
                lastName,
                "value",
                "Taur"
        ));

        wait.until(ExpectedConditions.attributeToBe(
                postalCode,
                "value",
                "411001"
        ));

        // Click Continue
        wait.until(ExpectedConditions.elementToBeClickable(continueButton))
                .click();

        // Wait for checkout overview
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void clickFinish() {

        wait.until(ExpectedConditions.elementToBeClickable(finishButton))
                .click();
    }

    public String getConfirmationMessage() {

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationMessage
                )
        ).getText();
    }
}