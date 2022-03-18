package training.selenium.PageObjectsTestDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class MainLitecardPage extends Page{
    public MainLitecardPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    public void cliclCheckout() {
        driver.findElement(By.xpath("//a[contains(.,'Checkout')]")).click();
    }

    public void clickProduct()  {
        WebElement firstProduct = driver.findElement(By.cssSelector("#box-most-popular li.product:first-child"));
        String title = firstProduct.findElement(By.cssSelector("div[class=name]")).getText();

        firstProduct.click();

        WebElement boxProduct = driver.findElement(By.cssSelector("#box-product "));
        String titleBoxProduct = boxProduct.findElement(By.tagName("h1")).getText();
        assertEquals(title,titleBoxProduct,"The title is different.");
    }

    public void clickHome() {
        driver.findElement(By.cssSelector("a i.fa[title=Home]")).click();
        isElementPresent(By.cssSelector("#box-most-popular"));
    }
}
