package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import training.selenium.Litecard.lib.BaseModule;

public class NewDuckTest extends BaseModule {
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
