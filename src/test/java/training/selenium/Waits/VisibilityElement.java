package training.selenium.Waits;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/***
 * Проверка видимости элементов (только тех, которые постоянно присутствуют на странице
 * и имеют в свойствах style = display : none)
 * Пример: всплывающее окно или серый фон *
 */
public class VisibilityElement {
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
    public void doSomething()  {
        driver.get("http://localhost/litecart/en/");
        By wrap = By.id("fancybox-wrap");
        List<WebElement> ducks = driver.findElements(By.cssSelector(".middle > .content > .box li.product"));
        assertTrue(isElementPresent(wrap));                 //эл-т присутствует на странице, но невидимый
        ducks.get(0).findElement(By.cssSelector("a.fancybox")).click();

        WebElement element = driver.findElement(wrap);
        assertTrue(isElementPresent(wrap));                   //эл-т присутствует на странице, стал видимый
        wait.until(visibilityOf(element));                   //проверка видимости, ожидаем пока эл-т станет видимым

        //комбинация двух условий: проверка наличия эл-та на странице и проверка что он является видимым
        wait.until(visibilityOfAllElementsLocatedBy(wrap));

        //для списка (проверка, что все эл-ты видимые)
        //wait.until(visibilityOfAllElements(element));

        driver.findElement(By.id("fancybox-close")).click();
        wait.until(not(visibilityOf(element)));             //ожидаем пока эл-т станет невидимым

        //для списка (проверка, что все элементы стали невидимыми)
        //wait.until(invisibilityOfAllElements(element));
    }

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
