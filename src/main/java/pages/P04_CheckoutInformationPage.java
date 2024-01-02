package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.shortWait;

public class P04_CheckoutInformationPage {
    WebDriver driver;

    public P04_CheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By firstName = By.xpath("//input[@id='first-name']");
    private final By lastName =By.xpath("//input[@id='last-name']");
    private final By postalCode = By.xpath("//input[@id='postal-code']");
    private final By continueBtn = By.xpath("//input[@class='btn_primary cart_button']");

    public P04_CheckoutInformationPage fillFirstName(String firstName) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.firstName));
        driver.findElement(this.firstName).sendKeys(firstName);
        return this;
    }

    public P04_CheckoutInformationPage fillLastName(String lastName) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.lastName));
        driver.findElement(this.lastName).sendKeys(lastName);
        return this;
    }

    public P04_CheckoutInformationPage fillPostalCode(String postalCode) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.postalCode));
        driver.findElement(this.postalCode).sendKeys(postalCode);
        return this;
    }
    public P04_CheckoutInformationPage clickOnContinueBtn() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.continueBtn));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.continueBtn).click();
        return this;
    }



}
