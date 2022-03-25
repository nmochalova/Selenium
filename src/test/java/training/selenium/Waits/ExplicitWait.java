package training.selenium.Waits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
// явное ожидание появления элемента
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/***
 * Явные ожидания. Настройка, задается непосредственно при поиске элемента (на стороне клиента).
 * Явные ожидания позволяют проверять любые условия (нужный элемент будет иметь нужный текст,
 * подождать пока эл-т исчезнет/появится, подождать пока изменится кол-во эл-тов или пока все они не появятся)
 * Отсутствия ожиданий может быть одной из причин появления исключения TimeoutException
 * Риск: много сетевых запросов (если браузер в облаках и много ждем, то повлияет на производительность)

 import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
   ...
 WebdriverWait wait=new WebDriverWait(driver,10);
 WebElement element=wait.until(presenceOfElementLocated(By.name("q")));
 */

public class ExplicitWait {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        //если драйвер уже есть, то выходим
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }
        //иначе создаем новый
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //останов драйвера в конце теста
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driver.quit();
                    driver = null;
                }));
    }

    @Test
    public void doSomething() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        assertTrue(isElementPresentFirst(By.cssSelector(".g")));
        assertTrue(isElementPresentSecond(By.cssSelector(".g")));
    }

    @Test
    public void test() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        assertTrue(isTitlePresent("My Store"));

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        assertTrue(isTitlePresent("Catalog | My Store"));
    }

    //Явные ожидания с помощью лямбда-выражений
    boolean isElementPresentFirst(By locator) {
        /*В wait.until() передается предикат либо функция, если завершается успешно (т.е. эл-т найден),
        то ожидание завершается,если исключение, либо false, - то ожидание эл-та продолжается.
        В течении какого времени ждет? Это указывается при инициализации wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); -  10 секунд
        Интервал между повторениями - полсекунды */
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            return true;
        } catch (TimeoutException ex) { //импортировать из org.openqa.selenium
            return false;
        }
    }

    //Явные ожидания с помощью presenceOfElementLocated
    boolean isElementPresentSecond(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) { //импортировать из org.openqa.selenium
            return false;
        }
    }

    public boolean isTitlePresent(String title) {
        try {
            wait.until(titleIs(title));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
}