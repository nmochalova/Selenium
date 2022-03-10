package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import training.selenium.Litecard.lib.BaseModule;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClickAllMenuTest extends BaseModule {
    @Test
    public void clickMenu() {
        AuthAdmin("admin","admin");
        assertTrue(isTitlePresent("My Store"));

        /////// Appearence ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Appearence')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Template')]")));

        clickMainMenu(By.cssSelector("#doc-template"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Template')]")));

        clickMainMenu(By.cssSelector("#doc-logotype"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Logotype')]")));

        /////// Catalog ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Catalog')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Catalog')]")));

        clickMainMenu(By.cssSelector("#doc-catalog"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Catalog')]")));

        clickMainMenu(By.cssSelector("#doc-product_groups"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Product Groups')]")));

        clickMainMenu(By.cssSelector("#doc-option_groups"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Option Groups')]")));

        clickMainMenu(By.cssSelector("#doc-manufacturers"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Manufacturers')]")));

        clickMainMenu(By.cssSelector("#doc-suppliers"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Suppliers')]")));

        clickMainMenu(By.cssSelector("#doc-delivery_statuses"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Delivery Statuses')]")));

        clickMainMenu(By.cssSelector("#doc-sold_out_statuses"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Sold Out Statuses')]")));

        clickMainMenu(By.cssSelector("#doc-quantity_units"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Quantity Units')]")));

        clickMainMenu(By.cssSelector("#doc-csv"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'CSV Import/Export')]")));

        /////// Countries ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Countries')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Countries')]")));

        /////// Currencies ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Currencies')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Currencies')]")));

        /////// Customers ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Customers')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Customers')]")));

        clickMainMenu(By.cssSelector("#doc-customers"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Customers')]")));

        clickMainMenu(By.cssSelector("#doc-csv"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'CSV Import/Export')]")));

        clickMainMenu(By.cssSelector("#doc-newsletter"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Newsletter')]")));

        /////// Geo Zones ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Geo Zones')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Geo Zones')]")));

        /////// Languages ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Languages')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Languages')]")));

        clickMainMenu(By.cssSelector("#doc-languages"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Languages')]")));

        clickMainMenu(By.cssSelector("#doc-storage_encoding"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Storage Encoding')]")));

        /////// Modules ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Modules')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Job Modules')]")));

        clickMainMenu(By.cssSelector("#doc-jobs"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Job Modules')]")));

        clickMainMenu(By.cssSelector("#doc-customer"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Customer Modules')]")));

        clickMainMenu(By.cssSelector("#doc-shipping"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Shipping Modules')]")));

        clickMainMenu(By.cssSelector("#doc-payment"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Payment Modules')]")));

        clickMainMenu(By.cssSelector("#doc-order_total"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Order Total Modules')]")));

        clickMainMenu(By.cssSelector("#doc-order_success"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Order Success Modules')]")));

        clickMainMenu(By.cssSelector("#doc-order_action"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Order Action Modules')]")));

        /////// Orders ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Orders')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Orders')]")));

        clickMainMenu(By.cssSelector("#doc-orders"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Orders')]")));

        clickMainMenu(By.cssSelector("#doc-order_statuses"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Order Statuses')]")));

        /////// Pages ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Pages')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Pages')]")));

        /////// Reports ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Reports')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Monthly Sales')]")));

        clickMainMenu(By.cssSelector("#doc-monthly_sales"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Monthly Sales')]")));

        clickMainMenu(By.cssSelector("#doc-most_sold_products"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Most Sold Products')]")));

        clickMainMenu(By.cssSelector("#doc-most_shopping_customers"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Most Shopping Customers')]")));

        /////// Settings ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Settings')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-store_info"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-defaults"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-general"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-listings"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-images"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-checkout"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-advanced"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        clickMainMenu(By.cssSelector("#doc-security"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Settings')]")));

        /////// Slides ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Slides')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Slides')]")));

        /////// Tax ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Tax')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Tax Classes')]")));

        clickMainMenu(By.cssSelector("#doc-tax_classes"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Tax Classes')]")));

        clickMainMenu(By.cssSelector("#doc-tax_rates"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Tax Rates')]")));

        /////// Translations ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Translations')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Search Translations')]")));

        clickMainMenu(By.cssSelector("#doc-search"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Search Translations')]")));

        clickMainMenu(By.cssSelector("#doc-scan"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Scan Files For Translations')]")));

        clickMainMenu(By.cssSelector("#doc-csv"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'CSV Import/Export')]")));

        /////// Users ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'Users')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'Users')]")));

        /////// vQmods ///////
        clickMainMenu(By.xpath("//*[@class='name'][contains(.,'vQmods')]"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'vQmods')]")));

        clickMainMenu(By.cssSelector("#doc-vqmods"));
        assertTrue(isElementPresent(By.xpath("//h1[contains(.,'vQmods')]")));
    }
}
