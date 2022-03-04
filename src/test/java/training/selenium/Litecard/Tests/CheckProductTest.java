package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.selenium.factory.WebDriverPool;
import training.selenium.Litecard.lib.ui.CheckCampaigns;

public class CheckProductTest extends CheckCampaigns {
    @Test
    public void testChrome() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());

        checkTitleAndPrice(driver);
        checkColorPrice(driver);
        checkSizeFontPrices(driver);
    }

    @Test
    public void testFirefox() {
        driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());

        checkTitleAndPrice(driver);
        checkColorPrice(driver);
        checkSizeFontPrices(driver);
    }

    @Test
    public void testEdge() {
        driver = WebDriverPool.DEFAULT.getDriver(new EdgeOptions());

        checkTitleAndPrice(driver);
        checkColorPrice(driver);
        checkSizeFontPrices(driver);
    }
}
