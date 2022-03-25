package training.selenium.AdminLitecard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    public ArrayList<WebElement> readTableByWebElement(By locator, int column) {
        ArrayList<WebElement> listOfOneColumn = new  ArrayList<WebElement>();

        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i=1; i<rows.size()-1;i++) {
            WebElement name = rows.get(i).findElement(By.xpath("./td["+column+"]"));
            listOfOneColumn.add(name);
        }
        return listOfOneColumn;
    }

    public boolean isTitlePresent(String title) {
        try {
            wait.until(titleIs(title));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
}
