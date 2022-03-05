package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;
import training.selenium.Litecard.lib.BaseModule;

import java.time.Duration;

public class NewDuckTest extends BaseModule {
    @BeforeEach
    public void startBrowserChrome() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void newDuck() throws InterruptedException {
        AuthAdmin("admin","admin");
        clickMainMenu(By.xpath("//li[contains(.,'Catalog')]"));
        driver.findElement(By.xpath("//a[@class='button'][contains(.,'Add New Product')]")).click();
        isTitlePresent("Add New Product");

        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys("05.03.2022");
        Thread.sleep(2000);
    }
}
