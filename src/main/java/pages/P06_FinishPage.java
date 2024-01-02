package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;

public class P06_FinishPage {

    WebDriver driver;

    public P06_FinishPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By thankMassageDisplay = By.xpath("//h2[@class='complete-header']");

    public String returnDisplayedMessage(){
        longWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.thankMassageDisplay));
        String thanksMsg = driver.findElement(this.thankMassageDisplay).getText();
        return thanksMsg;
    }
}
