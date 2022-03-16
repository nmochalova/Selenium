package training.selenium.Windows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseWin {
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
}
