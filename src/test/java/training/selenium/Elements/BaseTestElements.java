package training.selenium.Elements;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class BaseTestElements {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void logout() {
        driver.findElement(By.cssSelector("[title=Logout]")).click();
    }

    public void AuthAdmin(String username, String password)  {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void clickMainMenu(By locator) {
        isElementPresent(locator);
        driver.findElement(locator).click();
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
