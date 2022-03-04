package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;
import training.selenium.Litecard.lib.BaseModule;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickMenuFastTest extends BaseModule {
    @BeforeEach
    public void startBrowserChrome() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void clickMenuFastTestDrive() {
        AuthAdmin("admin","admin");
        assertTrue(isTitlePresent("My Store"));

        List<WebElement> menu = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
        for(int i=1;i<=menu.size();i++) {
            String str = MessageFormat.format("ul#box-apps-menu > li:nth-child({0})",i);
            driver.findElement(By.cssSelector(str)).click();
            assertTrue(isElementPresent(By.cssSelector("h1")));

            List<WebElement> subMenu = driver.findElements(By.cssSelector(str + " li"));
            for(int j=1;j<=subMenu.size();j++) {
                String strSubMenu = MessageFormat.format(str + " li:nth-child({0})",j);
                driver.findElement(By.cssSelector(strSubMenu)).click();
                assertTrue(isElementPresent(By.cssSelector("h1")));
            }
        }
    }
}
