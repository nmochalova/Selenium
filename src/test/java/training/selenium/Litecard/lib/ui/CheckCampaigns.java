package training.selenium.Litecard.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.BaseModule;
import training.selenium.Litecard.lib.MultiBrowsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckCampaigns extends MultiBrowsers {
    private final String MAIN_PAGE_PRODUCT = "#box-campaigns li.product:first-child ";
    private final  String PRODUCT_CARD = "#box-product ";

   //10.а) и б) На главной странице и на странице товара совпадают текст названия товара и цены (обычная и акционная)
     public void checkTitleAndPrice(WebDriver driver) {
        driver.get("http://localhost/litecart/en/");
        WebElement duck = driver.findElement(By.cssSelector(MAIN_PAGE_PRODUCT));
        String title = duck.findElement(By.cssSelector("div[class=name]")).getText();
        String regularPrice = duck.findElement(By.cssSelector(".regular-price")).getText();
        String campaignPrice = duck.findElement(By.cssSelector(".campaign-price")).getText();

        duck.click();

        WebElement boxProduct = driver.findElement(By.cssSelector(PRODUCT_CARD));
        String titleBoxProduct = boxProduct.findElement(By.tagName("h1")).getText();
        String regularPriceBoxProduct = boxProduct.findElement(By.cssSelector(".regular-price")).getText();
        String campaignPriceBoxProduct = boxProduct.findElement(By.cssSelector(".campaign-price")).getText();

        assertEquals(title,titleBoxProduct,"The title is different.");
        assertEquals(regularPrice,regularPriceBoxProduct,"The regular price is different.");
        assertEquals(campaignPrice,campaignPriceBoxProduct,"The campaign price is different.");
    }

    //10 в) обычная цена зачёркнутая и серая (одинаковые значения для каналов R, G и B)
    // г) акционная жирная и красная (каналы G и B имеют нулевые значения)
    //(цвета надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)
    public void checkColorPrice(WebDriver driver) {
        driver.get("http://localhost/litecart/en/");

        assertTrue(checkColorRegularPrice(By.cssSelector(MAIN_PAGE_PRODUCT + ".regular-price")));
        assertTrue(checkColorCampaignPrice(By.cssSelector(MAIN_PAGE_PRODUCT + ".campaign-price")));

        driver.findElement(By.cssSelector(MAIN_PAGE_PRODUCT)).click();

        assertTrue(checkColorRegularPrice(By.cssSelector(PRODUCT_CARD + ".regular-price")));
        assertTrue(checkColorCampaignPrice(By.cssSelector(PRODUCT_CARD + ".campaign-price")));
    }

    //10 д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
    public void checkSizeFontPrices(WebDriver driver) {
        driver.get("http://localhost/litecart/en/");
        By mainRegularPrice = By.cssSelector(MAIN_PAGE_PRODUCT + ".regular-price");
        By mainCampaignPrice = By.cssSelector(MAIN_PAGE_PRODUCT + ".campaign-price");

        assertTrue(checkSizePrices(mainRegularPrice, mainCampaignPrice));

        driver.findElement(By.cssSelector(MAIN_PAGE_PRODUCT)).click();

        By productCardRegularPrice = By.cssSelector(PRODUCT_CARD + ".regular-price");
        By productCardCampaignPrice = By.cssSelector(PRODUCT_CARD + ".campaign-price");

        assertTrue(checkSizePrices(productCardRegularPrice, productCardCampaignPrice));
    }
}
