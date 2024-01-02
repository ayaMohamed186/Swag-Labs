package testcase;

import com.github.javafaker.Faker;
import common.MyScreenRecorder;
import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static drivers.DriverHolder.setDriver;

public class TestBase {
    WebDriver driver;

    @BeforeSuite
    public void defineSuite() throws Exception {
        MyScreenRecorder.startRecording("E2E Swag Labs");
    }

    @Parameters("browser")

    @BeforeTest
    public void setupDriver(String browser) {
        driver = DriverFactory.getNewInstance(browser);
        setDriver(driver);

        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        Thread.currentThread().interrupt(); // after driver close , close opened thread which open in each run
    }

    @AfterSuite
    public void endSuite() throws Exception {
        MyScreenRecorder.stopRecording();
    }
}
