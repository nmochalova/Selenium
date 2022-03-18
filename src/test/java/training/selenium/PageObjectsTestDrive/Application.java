package training.selenium.PageObjectsTestDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.selenium.Litecard.lib.Utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Application {
    private WebDriverWait wait;
    private WebDriver driver;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public void quit() {
        driver.quit();
    }

    public void removeProductsFromCart() {
        while(!isPresent()) {
            List<WebElement> products = driver.findElements(By.cssSelector(".shortcut"));
            if (products.isEmpty()) {
                //отдельная ветка, потому что последний продукт не имеет плашки с перечнем продуктов
                clickRemoveButton();
            } else {
                RemoveProductAndCheckTable(products.size());
            }
        }
    }

    public void addProductsToCart(int countProducts) {
        driver.get("http://localhost/litecart/en/");

        for (int i=0;i<countProducts;i++) {
            String firstProduct = "#box-most-popular li.product:first-child";
            clickProductAndAddToCart(driver, By.cssSelector(firstProduct));
            clickHome();
        }
    }

    public boolean isPresent() {
        return isElementPresent(By.tagName("em"));
    }

    public void clickHome() {
        driver.findElement(By.cssSelector("a i.fa[title=Home]")).click();
        isElementPresent(By.cssSelector("#box-most-popular"));
    }

    public void clickCheckout() {
        driver.findElement(By.xpath("//a[contains(.,'Checkout')]")).click();
        stopAction();
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void clickProductAndAddToCart(WebDriver driver, By locator)  {
        WebElement firstProduct = driver.findElement(locator);
        String title = firstProduct.findElement(By.cssSelector("div[class=name]")).getText();

        firstProduct.click();

        WebElement boxProduct = driver.findElement(By.cssSelector("#box-product "));
        String titleBoxProduct = boxProduct.findElement(By.tagName("h1")).getText();
        assertEquals(title,titleBoxProduct,"The title is different.");

        WebElement quantity = driver.findElement(By.cssSelector("span.quantity"));
        int countProductsBefore = Utils.getIntByTextOfWebElement(quantity);
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

    public void clickRemoveButton() {
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
    }

    public void RemoveProductAndCheckTable(int count) {
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
        clickRemoveButton();
        //подождать пока элемент исчезнет
        wait.until(stalenessOf(element));
    }

    public void stopAction() {
        driver.findElement(By.cssSelector(".shortcuts li:first-child")).click();
    }

    //чтения таблицы, возвращаем массив WebElement (отсчет столбцов с 1)
    public ArrayList<WebElement> readTableByWebElement(By locator, int column, int count) {
        ArrayList<WebElement> listOfOneColumn = new  ArrayList<WebElement>();

        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i=1; i<=count;i++) {
            WebElement name = rows.get(i).findElement(By.xpath("./td["+column+"]"));
            listOfOneColumn.add(name);
        }
        return listOfOneColumn;
    }
}
