package training.selenium.PageObjectsTestDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CustomerCartPage extends Page{
    public CustomerCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickRemoveButton() {
        driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
    }

    public void RemoveProductAndCheckTable(int count) {
        //Найти эл-т который должен исчезнуть
        WebElement element = null;
        String titleProduct = driver.findElement(By.cssSelector("li.item:first-child strong")).getText();
        List<WebElement> trText = readTableByWebElement(By.cssSelector(".dataTable"),2,count);
        for(WebElement tr : trText) {
            if(tr.getText().equals(titleProduct)) {
                element = tr;
            }
        }
        // Выполнить действие
        clickRemoveButton();
        //подождать пока элемент исчезнет
        wait.until(stalenessOf(element));
    }

    public void stopAction() {
        driver.findElement(By.cssSelector(".shortcuts li:first-child")).click();
    }

    public List<WebElement> getProducts() {
        return  driver.findElements(By.cssSelector(".shortcut"));
    }
}
