package training.selenium.PageObjectsTestDrive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainLitecardPage extends Page{
    public MainLitecardPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get("http://localhost/litecart/en/");
    }

    public void clickProduct()  {
        WebElement firstProduct = driver.findElement(By.cssSelector("#box-most-popular li.product:first-child"));
        String title = firstProduct.findElement(By.cssSelector("div[class=name]")).getText();

        firstProduct.click();

        WebElement boxProduct = driver.findElement(By.cssSelector("#box-product "));
        String titleBoxProduct = boxProduct.findElement(By.tagName("h1")).getText();
        assertEquals(title,titleBoxProduct,"The title is different.");
    }

    public void clickCheckout() {
        driver.findElement(By.xpath("//a[contains(.,'Checkout')]")).click();
    }

    public void clickHome() {
        driver.findElement(By.cssSelector("a i.fa[title=Home]")).click();
        isElementPresent(By.cssSelector("#box-most-popular"));
    }
}
