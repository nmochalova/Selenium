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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

/***
 * Неявные ожидания. Настройка, задается на уровне построения драйвера.
 * Ожидание пока появится элемент. Проверки осуществляются внутри браузера.
 * Интервал - 100 млсек, т.е. 10 проверок в секунду
 * Риск: если есть проверки отсутствия элемента - команда будет зависать. Придется переключать настройку
 * в зависимости от того ожидается ли эл-т или нет.
 * Отсутствия ожиданий может быть одной из причин появления исключения NoSouchElementExeption (эл-т не найден)

 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
 WebElement element = driver.findElement(By.name("q"));
 */

//Неявные ожидания
public class ImplicitWait {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());

        //настройка неявных ожиданий
        //представляет собой цикл, который запрашивает эл-мент 10 раз в секунду в течении заданного таймаута
        //результат: или эл-т найден, или истек таймаут
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        assertTrue(isElementPresent(By.cssSelector(".g")));
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
}
