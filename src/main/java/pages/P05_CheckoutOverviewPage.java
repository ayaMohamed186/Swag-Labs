package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.shortWait;
import static util.Utility.removeNonDigits;

public class P05_CheckoutOverviewPage {

    WebDriver driver;

    public P05_CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By actualTotalPrice = By.xpath("//div[@class='summary_info']/div[@class='summary_subtotal_label']");
    private final  By taxesInCheckoutPage = By.xpath("//div[@class='summary_info']/div[@class='summary_tax_label']");
    private final By totalPriceWithTaxesAtCheckoutPage = By.xpath("//div[@class='summary_info']/div[@class='summary_total_label']");
    private final By finishBtnAtChecoutPage = By.xpath("//a[@href='./checkout-complete.html']");
    public double returnActualTotalPriceAtCheckoutPage(){
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.actualTotalPrice));
        double priceNumber;
        String actualTotalPriceWithCharChar = driver.findElement(this.actualTotalPrice).getText();
        String actualTotalPriceWithoutChar = removeNonDigits(actualTotalPriceWithCharChar) ;

        priceNumber = Double.parseDouble(actualTotalPriceWithoutChar);
        return priceNumber;
    }

    public double returnTaxesAtCheckoutPage(){
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.taxesInCheckoutPage));
        double taxes;
        String actualTaxesWithCharChar = driver.findElement(this.taxesInCheckoutPage).getText();
        String actualTaxesWithoutChar = removeNonDigits(actualTaxesWithCharChar) ;

        taxes = Double.parseDouble(actualTaxesWithoutChar);
        return taxes;
    }
    public double returnTotalPriceWithTaxesAtCheckoutPage(){
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.totalPriceWithTaxesAtCheckoutPage));
        double displayedTotalPriceWithTaxes;
        String actualTotalPriceTaxesWithChar = driver.findElement(this.totalPriceWithTaxesAtCheckoutPage).getText();
        String actualTotalPriceTaxesWithoutChar = removeNonDigits(actualTotalPriceTaxesWithChar) ;

        displayedTotalPriceWithTaxes = Double.parseDouble(actualTotalPriceTaxesWithoutChar);
        return displayedTotalPriceWithTaxes;
    }

    public P05_CheckoutOverviewPage clickOnFinishBtn(){
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.finishBtnAtChecoutPage));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.finishBtnAtChecoutPage).click();
        return this;
    }

}