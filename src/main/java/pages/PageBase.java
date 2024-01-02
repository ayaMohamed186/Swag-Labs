package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    public static WebDriverWait longWait(WebDriver driver) {
        return new WebDriverWait(driver, 25);
    }

    public static WebDriverWait shortWait(WebDriver driver) {
        return new WebDriverWait(driver, 10);
    }

    public static void fluentWaitHandling(WebDriver driver, String webElementXPATH) {
        FluentWait wait = new FluentWait(driver)
                .withTimeout(Duration.ofSeconds(50))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(Exception.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(webElementXPATH)));
    }

    // wait for page load
    public static void waitForPageLoad(WebDriver driver) {
        (new WebDriverWait(driver, 50)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                //System.out.println("Ready State: " + readyState);
                return (Boolean) readyState.equals("complete");
            }
        });
    }
}
