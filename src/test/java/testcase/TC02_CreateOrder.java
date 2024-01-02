package testcase;

import com.github.javafaker.Faker;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import util.Utility;

import java.io.IOException;

import static util.Utility.captureScreenshot;

public class TC02_CreateOrder extends TestBase {

    String password = Utility.getSingleJsonData(System.getProperty("user.dir") + "/src/test/resources/data_driven/loginData.json", "password");
    String userName = Utility.getSingleJsonData(System.getProperty("user.dir") + "/src/test/resources/data_driven/loginData.json", "userName");
    int max = 6;
    static Faker faker = new Faker();
    static final String firstName = faker.name().fullName();
    static final String lastName = faker.name().lastName();
    static final String postalCode = faker.number().digits(6);

    public TC02_CreateOrder() throws IOException, ParseException {
    }

    @Test(priority = 1 , description = " Create success order for logged user" )
    public void createOrderWithAllProducts() throws InterruptedException {
        new P01_LoginPage(driver).fillUsername(userName).fillPassword(password).clickLoginButton();
        new P02_ProductsPage(driver).addItemsToCart(max);

        double actualCalculatedTotalPrice = new P02_ProductsPage(driver).calculatePriceOfAllItems(max);

        new P03_ShoppingCart(driver).clickOnShoppingCart().clickOnCheckoutBtn();
        new P04_CheckoutInformationPage(driver).fillFirstName(firstName).fillLastName(lastName).fillPostalCode(postalCode).clickOnContinueBtn();

        double expectedTotalPriceAtCheckoutPage = new P05_CheckoutOverviewPage(driver).returnActualTotalPriceAtCheckoutPage();

        // assert in checkout page that total price displayed is equal to calculated price while adding items
        Assert.assertEquals(actualCalculatedTotalPrice, expectedTotalPriceAtCheckoutPage);
        System.out.println("Displayed total price for items in checkout page = " + expectedTotalPriceAtCheckoutPage);
        System.out.println("total price for items which calculated = " + actualCalculatedTotalPrice);


        double displayedTaxesAtCheckoutPage = new P05_CheckoutOverviewPage(driver).returnTaxesAtCheckoutPage();
        double actualTotalPriceWithTaxes = actualCalculatedTotalPrice + displayedTaxesAtCheckoutPage;
        double expectedTotalPriceWithTaxes = new P05_CheckoutOverviewPage(driver).returnTotalPriceWithTaxesAtCheckoutPage();

        // assert in checkout page that total price displayed equal calculated total price with taxes
        Assert.assertEquals(actualTotalPriceWithTaxes, expectedTotalPriceWithTaxes);
        System.out.println("Displayed total price with taxes for items in checkout page = " + expectedTotalPriceWithTaxes);
        System.out.println("total price with taxes for items which calculated = " + actualTotalPriceWithTaxes);

        new P05_CheckoutOverviewPage(driver).clickOnFinishBtn();
        String actualMsgDisplayed = new P06_FinishPage(driver).returnDisplayedMessage();

        // TODO : Capture screenshot
        captureScreenshot(driver, "CompleteOder");

        Assert.assertEquals(actualMsgDisplayed, "THANK YOU FOR YOUR ORDER");
        System.out.println("actual msg is = " + actualMsgDisplayed);
    }
}
