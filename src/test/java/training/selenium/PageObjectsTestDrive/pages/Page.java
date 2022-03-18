package training.selenium.PageObjectsTestDrive.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    //чтения таблицы, возвращаем массив WebElement (отсчет столбцов с 1)
    public ArrayList<WebElement> readTableByWebElement(By locator, int column, int count) {
        ArrayList<WebElement> listOfOneColumn = new  ArrayList<WebElement>();

        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i=1; i<=count;i++) {
            WebElement name = rows.get(i).findElement(By.xpath("./td["+column+"]"));
            listOfOneColumn.add(name);
        }
        return listOfOneColumn;
    }
}
