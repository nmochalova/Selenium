package training.selenium.Waits;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

/***
 * Исчезновение элемента
 * Исчезновение - это не отсутствие! Отсутствие - значит элемента не было никогда на странице,
 * а исчезновение - элемент был найден по локатору, но потом исчез. Поэтому надо сначала найти элемент,
 * а потом ждать его исчезновения. Selenium после нахождения элемента на странице запоминает его ID, по
 * которому позже обращается для работы с элементом и возвращению его свойств. Именно по ID можно отслеживать
 * исчезновение элемента. Если эл-т по ID больше не найден, то возвращается исключение StaleElementReferenceException
 * Для работы с ним есть функция: ExpectedConditions.stalenessOf(element) Возвращает:
 * false, если элемент все еще прикреплен к DOM, true в противном случае.
 *
 * Стандартный прием нахождения эл-та, который имеет один локатор, но разный ID до обновления страницы и после:
 * (пример: таблица с элементами, которые пролистываются по-странично)
 * 1. Найти эл-т который должен исчезнуть
 * 2. Выполнить действие
 * 3. Подождать пока эл-т исчезнет
 * 4. Подождать пока появится новый эл-т
 */
public class DisappearElement {
    WebDriver driver;
    WebDriverWait wait;
    public final int WAIT = 10;

    @BeforeEach
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }

    @Test
    public void doSomething() {
        driver.get("http://paginationjs.com/");
        WebElement demo1 = driver.findElement(By.cssSelector("@demo1"));
        List<WebElement> items = demo1.findElements(By.cssSelector(".data-container ul li"));
        List<WebElement> pagination = demo1.findElements(By.cssSelector(".paginationjs-pages ul li"));

        pagination.get(2).click(); //перелистнуть страницу
        wait.until(stalenessOf(items.get(0))); //ждем исчезнование эл-та
        items = driver.findElements(By.cssSelector(".data-container ul li")); //перезапрашиваем эл-ты по тому же локатору
        Assert.assertTrue(items.get(0).getText().equals("11")); //доп.проверка, что по локатору ожидаемый текст
    }
    @Test
    public void exampleSecond() {
        driver.get("http://localhost/litecart/en/");
        WebElement element = driver.findElement(By.cssSelector("button[name=login]"));  //Найти эл-т который должен исчезнуть
        driver.navigate().refresh();                            // Выполнить действие
        wait.until(stalenessOf(element));                       //подождать пока элемент исчезнет
        element = driver.findElement(By.cssSelector("button[name=login]"));             //Подождать пока появится новый эл-т
        Assert.assertTrue(element.getText().equals("Login"));
    }
}
