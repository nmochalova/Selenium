package training.selenium.Litecard;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.MessageFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickMenuFast extends Task6{
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
