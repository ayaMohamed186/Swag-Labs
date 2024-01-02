package testcase;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.P01_LoginPage;
import util.Utility;

import static util.Utility.captureScreenshot;


public class TC01_Login extends TestBase{
    String userName = Utility.getExcelData(0,0,"loginData");
    String password=Utility.getExcelData(1,0,"loginData");


    @Test(priority = 1 , description = " Login with register user data " )
    public void loginWithRegisteredUser_P() throws InterruptedException {
        new P01_LoginPage(driver).fillUsername(userName).fillPassword(password).clickLoginButton();

        //TODO: Capture ScreenShot
        captureScreenshot(driver,"LoginSystem");

        // Asserction
        Assert.assertTrue(new P01_LoginPage(driver).validateIfLoginSuccess()); // hard assert

        SoftAssert softAssertObj = new SoftAssert(); // soft assert
        softAssertObj.assertTrue(new P01_LoginPage(driver).validateIfLoginSuccess());
        softAssertObj.assertAll();
    }

}
