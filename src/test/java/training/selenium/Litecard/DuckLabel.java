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
        List<WebElement> stikers;

        for (WebElement li : duckBloks) {
            List<WebElement> ducks = li.findElements(By.cssSelector("li.product.column.shadow.hover-light"));
            for(WebElement d : ducks) {
                stikers = d.findElements(By.cssSelector("div.sticker"));
                if(stikers.size() != 1) {
                    assertTrue(false,"The duck should have only one sticker.");
                }
            }
        }
    }
}
