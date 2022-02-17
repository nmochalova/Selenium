package training.selenium.ParallelTestsFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool; //build.gradle >> implementation 'ru.stqa.selenium:webdriver-factory:4.4'

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class NoInitWebDriverSample {
    @AfterAll
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    @Test
    public void test1() {
        doSomething();
    }

    @Test
    public void test2() {
        doSomething();
    }

    @Test
    public void test3() {
        doSomething();
    }

    private void doSomething() {
        WebDriver driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }
}
