package training.selenium.AdminLitecard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CatalogPage extends Page{
    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public  ArrayList<String> selectProductsFromTable() {
        ArrayList<String> products = new ArrayList<>();
        ArrayList<WebElement> tableProducts = readTableByWebElement(By.className("dataTable"),3);
        for(WebElement tabProd : tableProducts) {
           try {
               tabProd.findElement(By.tagName("img"));
               products.add(tabProd.getText());
           } catch (NoSuchElementException ignored) {}
        }
        return products;
    }

    public void clickProductByName(String name)  {
        driver.findElement(By.xpath("//table[@class='dataTable']//tr//td[3][contains(.,'"+name+"')]//a")).click();
    }
}
