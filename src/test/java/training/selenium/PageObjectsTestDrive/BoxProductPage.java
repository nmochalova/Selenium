package training.selenium.PageObjectsTestDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.Utils;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class BoxProductPage extends Page{
    public BoxProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart(WebDriver driver) {
        WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));
        int countProductsBefore = Utils.getIntByTextOfWebElement(quantity);

        By locator = By.cssSelector("select[name^=option]");
        if (isElementPresent(locator)) {
            List<WebElement> listOption = driver.findElements(By.cssSelector("select[name^=option] option"));
            String optionSelected = listOption.get(1).getText();
            driver.findElement(locator).sendKeys(optionSelected);
        }
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();

        wait.until(textToBePresentInElement(quantity,Integer.toString(countProductsBefore+1)));
    }
}
