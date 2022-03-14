package training.selenium.Litecard.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.BaseModule;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class CheckBasket extends BaseModule {
    public void clickProductAndAddToCart(WebDriver driver, By locator)  {
        WebElement firstProduct = driver.findElement(locator);
        String title = firstProduct.findElement(By.cssSelector("div[class=name]")).getText();

        firstProduct.click();

        WebElement boxProduct = driver.findElement(By.cssSelector("#box-product "));
        String titleBoxProduct = boxProduct.findElement(By.tagName("h1")).getText();
        assertEquals(title,titleBoxProduct,"The title is different.");

        WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));
        int countProductsBefore = getIntByTextOfWebElement(quantity);
        addToCart(driver);
        wait.until(textToBePresentInElement(quantity,Integer.toString(countProductsBefore+1)));
    }

    public void addToCart(WebDriver driver) {
        By locator = By.cssSelector("select[name^=option]");
        if (isElementPresent(locator)) {
            List<WebElement> listOption = driver.findElements(By.cssSelector("select[name^=option] option"));
            String optionSelected = listOption.get(1).getText();
            driver.findElement(locator).sendKeys(optionSelected);
        }
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
    }

    public void removeProductsFromCart() {
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
    }

    public void checkTableAfterRemoveProduct(int count) {
        //Найти эл-т который должен исчезнуть
        WebElement element = null;
        String titleProduct = driver.findElement(By.cssSelector("li.item:first-child strong")).getText();
        List<WebElement> trText = readTableByWebElement(By.cssSelector(".dataTable"),2,count);
        for(WebElement tr : trText) {
            if(tr.getText().equals(titleProduct)) {
                element = tr;
            }
        }
        // Выполнить действие
        removeProductsFromCart();
        //подождать пока элемент исчезнет
        wait.until(stalenessOf(element));
    }

    public void stopAction() {
        driver.findElement(By.cssSelector(".shortcuts li:first-child")).click();
    }

}
