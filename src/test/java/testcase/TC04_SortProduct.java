package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_ProductsPage;
import util.Utility;

import static util.Utility.captureScreenshot;

public class TC04_SortProduct extends TestBase {
    String userName = Utility.getExcelData(0,0,"loginData");
    String password=Utility.getExcelData(1,0,"loginData");
    int sortLowToHigh = 2;
    double smallestPriceOfProduct;
    @Test(priority = 1)
    public void sortProductsFromLowToHigh() throws InterruptedException {
        new P01_LoginPage(driver).fillUsername(userName).fillPassword(password).clickLoginButton();
        new P02_ProductsPage(driver).chooseSortOption(sortLowToHigh);

        //TODO: Capture ScreenShot
        captureScreenshot(driver,"SortAscending");

        Assert.assertTrue(new P02_ProductsPage(driver).validateSortAscendingWork());

    }
}
