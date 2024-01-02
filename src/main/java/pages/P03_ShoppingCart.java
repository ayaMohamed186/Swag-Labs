package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.shortWait;

public class P03_ShoppingCart {

    WebDriver driver;

    public P03_ShoppingCart(WebDriver driver) {
        this.driver = driver;
    }

    private final By shoppingCartBtn = By.xpath("//div[@id='shopping_cart_container']");
    private final By checkoutBtn = By.xpath("//a[@href='./checkout-step-one.html']");
    public P03_ShoppingCart clickOnShoppingCart(){
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.shoppingCartBtn));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.shoppingCartBtn).click();
        return this;
    }
    public P03_ShoppingCart clickOnCheckoutBtn(){
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.checkoutBtn));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.checkoutBtn).click();
        return this;
    }

}
