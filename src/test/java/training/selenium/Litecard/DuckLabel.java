package training.selenium.Litecard;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuckLabel extends Task6{
    @Test
    public void DucksTest() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> duckBloks = driver.findElements(By.cssSelector(".middle > .content > .box"));

        for (WebElement li : duckBloks) {
            List<WebElement> ducks = li.findElements(By.cssSelector("li"));
            for(WebElement d : ducks) {
                assertTrue(isElementPresent(By.cssSelector("[class ^= sticker]")));
            }
        }
    }
}
