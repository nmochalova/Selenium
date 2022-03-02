package training.selenium.Litecard;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.time.Duration;
import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class BaseModule {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @AfterAll
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    public void AuthAdmin(String username, String password)  {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void logout() {
        driver.findElement(By.cssSelector("[title=Logout]")).click();
    }

    public void clickMainMenu(By locator) {
        isElementPresent(locator);
        driver.findElement(locator).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            wait.until(presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean isTitlePresent(String title) {
        try {
            wait.until(titleIs(title));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
    //Процедура, которая проверяет сортировку по алфавиту
    public boolean checkSort(ArrayList<String> listNames) {
        List<String> listNamesSort = (ArrayList<String>) listNames.clone();

        Collections.sort(listNamesSort);

        for (int i=0; i<listNames.size();i++) {
            if (!listNames.get(i).equals(listNamesSort.get(i))) {
                return false;
            }
        }
        return true;
    }

    //чтения таблицы (способ 1) при помощи findElements с указанием номера одного столбца (отсчет столбцов с 1)
    public ArrayList<String> readTableOneColumn(By locator, int column) {
        ArrayList<String> listOfOneColumn = new  ArrayList<String>();

        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        for(int i=1; i<rows.size()-1;i++) {
            String name = rows.get(i).findElement(By.xpath("./td["+column+"]")).getText();
            listOfOneColumn.add(name);
        }
        return listOfOneColumn;
    }

    //чтения таблицы (способ 2) при помощи findElement с указанием номеров двух столбцов (отсчет столбцов с 0)
    public List<DataTableTwoColumns> readTableTwoColumns(By locator, int columnOne, int columnTwo) {
        List<DataTableTwoColumns> listOfTwoColumns = new ArrayList<>();

        WebElement table = driver.findElement(locator);
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        String name;
        String zones;
        List<WebElement> cells;

        for(int i=1; i<rows.size()-1;i++) {
            cells = rows.get(i).findElements(By.tagName("td"));
            name = cells.get(columnOne).getText();
            zones = cells.get(columnTwo).getText();
            listOfTwoColumns.add(new DataTableTwoColumns(name,zones));
        }
        return listOfTwoColumns;
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
}
