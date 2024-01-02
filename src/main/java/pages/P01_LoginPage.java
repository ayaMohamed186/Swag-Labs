package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static pages.PageBase.shortWait;

public class P01_LoginPage {
    WebDriver driver;

    public P01_LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By userName = By.xpath("//input[@type='text']");
    private final By password = By.xpath("//input[@type='password']");
    private final By loginButton = By.xpath("//input[@type=\"submit\"]");
    private final By productTitle=By.xpath("//div[@class=\"product_label\"]");
    private final By loginCredentialsText = By.xpath("//div[@id='login_credentials']/h4");

    public boolean validateIfUserLoggedOut(){
        return driver.findElement(this.loginCredentialsText).isDisplayed();
    }
    public P01_LoginPage fillUsername(String username) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.userName));
        driver.findElement(this.userName).sendKeys(username);
        return this;
    }

    public P01_LoginPage fillPassword(String password) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.password));
        driver.findElement(this.password).sendKeys(password);
        return this;
    }

    public P01_LoginPage clickLoginButton() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.loginButton));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.loginButton).click();
        return this;
    }
    public boolean validateIfLoginSuccess(){
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.productTitle));
        return driver.findElement(productTitle).getText().equals("Products");
    }

}
