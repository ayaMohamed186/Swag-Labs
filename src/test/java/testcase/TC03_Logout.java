package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.P01_LoginPage;
import pages.P02_ProductsPage;
import util.Utility;

import static util.Utility.captureScreenshot;

public class TC03_Logout extends TestBase{

    String userName = Utility.getExcelData(0,0,"loginData");
    String password=Utility.getExcelData(1,0,"loginData");

    @Test(priority = 1)
    public void logoutSystem(){
        new P01_LoginPage(driver).fillUsername(userName).fillPassword(password).clickLoginButton();
        new P02_ProductsPage(driver).clickOnSideMenuBtn().clickOnLogOutBtn();

        // TODO : Capture screenshot
        captureScreenshot(driver, "userLoggedOut");

        Assert.assertTrue(new P01_LoginPage(driver).validateIfUserLoggedOut());
    }
}
