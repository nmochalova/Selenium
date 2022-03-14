package training.selenium.Waits;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

/***
 * Неявные ожидания. Настройка, задается на уровне построения драйвера (на стороне браузера).
 * Ожидание пока появится элемент (ожидания появления в DOM). Проверки осуществляются внутри браузера автоматически.
 * Интервал - 100 млсек, т.е. 10 проверок в секунду
 * Риск: если есть проверки отсутствия элемента - команда будет зависать. Придется переключать настройку
 * в зависимости от того ожидается ли эл-т или нет.
 * Отсутствия ожиданий может быть одной из причин появления исключения NoSouchElementExeption (эл-т не найден)
 * Плюс: один сетевой запрос (оптимизировано)
 * В настоящий момент в Selenium не реализован механизм того как можно узнать текущее состояние ожиданий - вкл или выкл
 * поэтому рекомендуют создать переменную для хранения этого значения.

 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 WebElement element = driver.findElement(By.name("q"));
 */

//Неявные ожидания
public class ImplicitWait {
    WebDriver driver;
    WebDriverWait wait;
    public final int WAIT = 10; //переменная для хранения задержки, в течении которой ожидаем эл-т (в секундах)

    @BeforeEach
    public void startBrowser() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());

        //настройка неявных ожиданий
        //представляет собой цикл, который запрашивает эл-мент 10 раз в секунду в течении заданного таймаута
        //результат: или эл-т найден, или истек таймаут
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //неявное ожидание с укаpанием кол-ва секунд
    }

    @AfterAll
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    @Test
    public void doSomething() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();

        //Проверка наличия элемента (с ожиданием)
        assertTrue(isElementPresent(By.cssSelector(".g")));

        //Проверка наличие списка элемента (с включением ожидания)
        assertTrue(isListElementsisPresent(driver,By.name("q")));

        //Проверка отсуствия элемента (с отключенными ожиданиями)
        assertFalse(isListElementsisNotPresent(driver,By.name("q")));

        //Альтернативная проверка отсутствия эл-та - это проверка наличия другого эл-та, который не д.б. одновременно с первым
        //например кнопки logout в кейсе с авторизацией
        assertTrue(isElementPresent(By.id("logout_link")));
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
    //Проверка, что список не пустой
    boolean isListElementsisPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT)); //вкл.неявных ожиданий (timeout=10)
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); //выкл.неявных ожиданий (timeout=0)
        }
    }
    //Проверка отсутствия элементов в списке
    private boolean isListElementsisNotPresent(WebDriver driver, By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); //выкл.неявных ожиданий (timeout=0)
            return driver.findElements(locator).size() > 0;
        } finally {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT)); //вкл.неявных ожиданий (timeout=10)
        }
    }


}
