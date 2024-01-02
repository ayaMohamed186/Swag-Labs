package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static pages.PageBase.longWait;
import static pages.PageBase.shortWait;
import static util.Utility.removeDollarSign;

public class P02_ProductsPage {
    WebDriver driver;

    public P02_ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By sideMenuBtn = By.xpath("(//button)[2]");
    private final By logoutBtn = By.xpath("//a[@id='logout_sidebar_link']");
    private final By sortProductsDropDown = By.xpath("//select[@class='product_sort_container']");
    private final By firstProductAtProducts = By.xpath("(//div[@class='inventory_item_price'])[1]");


    public P02_ProductsPage clickOnLogOutBtn() {
        try {
            shortWait(driver).until(ExpectedConditions.elementToBeClickable(this.logoutBtn));
        } catch (TimeoutException ex) {
            ex.printStackTrace();
            System.out.println("Error happened is " + ex.getMessage());
        }
        driver.findElement(this.logoutBtn).click();
        return this;
    }

    public P02_ProductsPage addItemsToCart(int index) {
        for (index = 1; index <= 6; index++) {
            try {
                shortWait(driver).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='inventory_list']/div[" + index + "]/div[@class='pricebar']/button[@class='btn_primary btn_inventory']")));
            } catch (TimeoutException ex) {
                ex.printStackTrace();
                System.out.println("Error happened is " + ex.getMessage());
            }
            WebElement addToCartBtn = driver.findElement(By.xpath("//div[@class='inventory_list']/div[" + index + "]/div[@class='pricebar']/button[@class='btn_primary btn_inventory']"));
            addToCartBtn.click();
        }
        return this;
    }

    public double calculatePriceOfAllItems(int index) {
        double totalPrice = 0.0;
        double priceNumber;
        for (index = 1; index <= 6; index++) {
            try {
                shortWait(driver).until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='inventory_item_price'])[" + index + "]")));
            } catch (TimeoutException ex) {
                ex.printStackTrace();
                System.out.println("Error happened is " + ex.getMessage());
            }

            WebElement itemPriceWith$Sign = driver.findElement(By.xpath("(//div[@class='inventory_item_price'])[" + index + "]"));
            String priceWithDollarSign = itemPriceWith$Sign.getText();
            String priceWithout$ = removeDollarSign(priceWithDollarSign);

            priceNumber = Double.parseDouble(priceWithout$);

            totalPrice += priceNumber;
        }
        return totalPrice;
    }

    public P02_ProductsPage chooseSortOption(int index) {
        shortWait(driver).until(ExpectedConditions.visibilityOfElementLocated(this.sortProductsDropDown));
        WebElement sortElement = driver.findElement(this.sortProductsDropDown);
        Select sort = new Select(sortElement);
        sort.selectByIndex(index);
        return this;
    }

    public P02_ProductsPage clickOnSideMenuBtn() {
        driver.findElement(this.sideMenuBtn).click();
        return this;
    }

    public double getSmallestPrice() {
        List<WebElement> priceElements = driver.findElements(By.xpath("(//div[@class='inventory_item_price'])"));

        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().trim();
            priceText = priceText.replaceAll("[^\\d.]", "");
            double price = Double.parseDouble(priceText);
            prices.add(price);
        }

        double smallestPrice = prices.get(0);
        for (int i = 1; i < prices.size(); i++) {
            if (prices.get(i) < smallestPrice) {
                smallestPrice = prices.get(i);
            }
        }

        System.out.println("The smallest price is: " + smallestPrice);
        return smallestPrice;
    }

    public boolean validateSortAscendingWork() {
        String firstProductItemPrice = driver.findElement(this.firstProductAtProducts).getText().trim().replaceAll("[^\\d.]", "");
        String smallestProductPrice = String.valueOf(getSmallestPrice());

        return firstProductItemPrice.equals(smallestProductPrice);
    }

}
