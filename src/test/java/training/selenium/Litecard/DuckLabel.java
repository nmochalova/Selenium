package training.selenium.Litecard;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DuckLabel extends BaseModule {
    @Test
    public void DucksTest() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> stickers;

        List<WebElement> ducks = driver.findElements(By.cssSelector(".middle > .content > .box li.product"));
        for(WebElement d : ducks) {
            stickers = d.findElements(By.cssSelector("div.sticker"));
            assertEquals(1, stickers.size());
        }
    }
}
