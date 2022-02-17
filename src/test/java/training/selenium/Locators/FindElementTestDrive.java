package training.selenium.Locators;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindElementTestDrive {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");

        assertTrue(isElementPresent(By.name("username")));
        assertTrue(areElementsPresent(By.name("username")));

        //Ожидание, что эл-т отсутствует
        //NoSuchElementException
        assertFalse(isElementPresent(By.name("XXX")));
        assertFalse(areElementsPresent(By.name("XXX")));

        //Неверный локатор
        //InvalidSelectorException
        assertFalse(isElementPresent(By.xpath("//*[div")));
    }

    //Должна быть подключена библиотека jquery
    @Test
    public void SearchJavaScript () {
        List<WebElement> links = (List<WebElement>) ((JavascriptExecutor) driver)
                .executeScript("return $('td:contains(Username)')");


    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    //Проверка наличия элемента при помощи findElement
    boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (InvalidSelectorException ex) {
            throw ex;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    //Проверка наличия элемента в более короткой записи при помощи findElements
    boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0; //возвращает пустой список, если эл-т не найден
    }

    //Пример #1 чтения таблицы при помощи findElements
    private HashMap<String,String> readTableFirst() {
        HashMap<String,String> nameAndEmail = new HashMap<String,String>();

        WebElement table = driver.findElement(By.id("users"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String name;
        String email;
        for(WebElement we : rows) {
            name = we.findElement(By.xpath("./td[1]")).getText();
            email = we.findElement(By.xpath("./td[2]")).getText();
            nameAndEmail.put(email,name); //расчитанно на уникальные емейлы, иначе перепишутся имена
        }
        return nameAndEmail;
    }

    //Пример #2 чтения таблицы при помощи findElements
    private List<DataTable> readTableSecond() {
        List<DataTable> nameAndEmail = new ArrayList<>();

        WebElement table = driver.findElement(By.id("users"));
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String name;
        String email;
        List<WebElement> cells;

        for(WebElement we : rows) {
            cells = we.findElements(By.tagName("td"));
            name = cells.get(0).getText();
            email = cells.get(1).getText();
            nameAndEmail.add(new DataTable(name,email));
        }
        return nameAndEmail;
    }
}
/*
    //Примеры css-локаторов
    WebElement form1 = driver.findElement(By.id("login-form"));
    WebElement form2 = driver.findElement(By.tagName("form"));
    WebElement form3 = driver.findElement(By.className("login"));
    WebElement form4 = driver.findElement(By.cssSelector("form.login"));
    WebElement form5 = driver.findElement(By.cssSelector("#doc-template"));

    WebElement field1 = driver.findElement(By.name("username"));
    WebElement field2 = driver.findElement(By.xpath("//input[@name='username']"));
    WebElement link = driver.findElement(By.linkText("Logout"));
    List<WebElement> links = driver.findElements(By.tagName("a"));
*/

/*** css-локаторы:
 * WebElement form1 = driver.findElement(By.cssSelector("#doc-template"));
 * WebElement form1 = driver.findElement(By.id("doc-template"));
 *
 * WebElement form5 = driver.findElement(By.cssSelector(".username"));
 * WebElement form5 = driver.findElement(By.name("username"));
 *
 * $$("input[name=username]")
 * $$("input[name=password]")
 * $$("input[type=checkbox]")
 * $$(":checked")
 * $$("button[name=login]")
 * $$("div.notice.errors")
 *
 * $$("li#app- > a[href *= 'app=appearance']")
 * $$("#doc-template")
 * $$("h1")
 * $$("#doc-logotype")
 * $$("li#app- > a[href *= 'app=catalog']")
 * $$("#doc-catalog")
 * $$("#doc-product_groups")
 * $$("#doc-option_groups")
 * $$("#doc-manufacturers")
 * $$("#doc-suppliers")
 * $$("#doc-delivery_statuses")
 * $$("#doc-sold_out_statuses")
 * $$("#doc-quantity_units")
 * $$("#doc-csv")
 * $$("li#app- > a[href *= 'app=countries']")
 * $$("li#app- > a[href *= 'app=currencies']")
 * $$("li#app- > a[href *= 'app=customers']")
 * $$("#doc-customers")
 * $$("#doc-csv")
 * $$("#doc-newsletter")
 * $$("li#app- > a[href *= 'app=geo_zones']")
 * $$("li#app- > a[href *= 'app=languages']")
 * $$("#doc-languages")
 * $$("#doc-storage_encoding")
 * $$("li#app- > a[href *= 'app=modules']")
 * $$("#doc-jobs")
 * $$("#doc-customer")
 * $$("#doc-chipping")
 * $$("#doc-payment")
 * $$("#doc-order_total")
 * $$("#doc-order_success")
 * $$("#doc-order_action")
 * $$("li#app- > a[href *= 'app=orders']")
 * $$("#doc-orders")
 * $$("#doc-order_statuses")
 * $$("li#app- > a[href *= 'app=pages']")
 * $$("li#app- > a[href *= 'app=reports']")
 * $$("#doc-monthly_sales")
 * $$("#doc-most_sold_products")
 * $$("#doc-most_shopping_customers")
 * $$("li#app- > a[href *= 'app=settings']")
 * $$("#doc-store_info")
 * $$("#doc-defaults")
 * $$("#doc-general")
 * $$("#doc-listings")
 * $$("#doc-images")
 * $$("#doc-checkout")
 * $$("#doc-advanced")
 * $$("#doc-security")
 * $$("li#app- > a[href *= 'app=slides']")
 * $$("li#app- > a[href *= 'app=tax']")
 * $$("#doc-tax_classes")
 * $$("#doc-tax_rates")
 * $$("li#app- > a[href *= 'app=translations']")
 * $$("#doc-search")
 * $$("#doc-scan")
 * $$("#doc-csv")
 * $$("li#app- > a[href *= 'app=users']")
 * $$("li#app- > a[href *= 'app=vqmods']")
 * $$("#doc-vqmods")
 *
 * $$("li.product.column.shadow.hover-light div.sticker")
 */