package training.selenium.Logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogsBrowser {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @Test
    public void doSomething() {
        driver.get("http://www.google.com");
        //Информация о том, какие уровни логирования поддерживает браузер
        System.out.println(driver.manage().logs().getAvailableLogTypes());

        //Извлечение лога браузера (1)
        for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
            System.out.println(l);
        }
        //Извлечение лога браузера (2)
        driver.manage().logs().get("browser").forEach(l ->System.out.println(l));
    }
}
