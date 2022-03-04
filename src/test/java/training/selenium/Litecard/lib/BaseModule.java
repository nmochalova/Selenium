package training.selenium.Litecard.lib;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;
import training.selenium.Litecard.lib.DataTable.DataTableTwoColumns;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class BaseModule {
    public WebDriver driver;
    public WebDriverWait wait;

//    @BeforeEach
//    public void startBrowserChrome() {
//        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
//        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//    }

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

    public boolean checkColorRegularPrice(By locator) {
        Color priceRegularColor = Color.fromString(driver.findElement(locator).getCssValue("color"));
        int r = priceRegularColor.getColor().getRed();
        int g = priceRegularColor.getColor().getGreen();
        int b = priceRegularColor.getColor().getBlue();
        if (r==g && g==b) {
            assertTrue(checkCrossedOutFont(locator),"This font not crossed out.");
            return true;
        } else {
            System.out.println("rgb("+r+","+g+","+b+")");
            return false;
        }
    }

    public boolean checkCrossedOutFont(By locator) {
        WebElement croosedOutFont = driver.findElement(locator);
        if (croosedOutFont.getTagName().equals("s")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkColorCampaignPrice(By locator) {
        Color priceCampaignColor = Color.fromString(driver.findElement(locator).getCssValue("color"));
        int r = priceCampaignColor.getColor().getRed();
        int g = priceCampaignColor.getColor().getGreen();
        int b = priceCampaignColor.getColor().getBlue();
        if (g==0 && b==0) {
            assertTrue(checkStrongFont(locator),"This font not strong.");
            return true;
        } else {
            System.out.println("rgb("+r+","+g+","+b+")");
            return false;
        }
    }

    public boolean checkStrongFont(By locator) {
        WebElement strongFont = driver.findElement(locator);
        if (strongFont.getTagName().equals("strong")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSizePrices(By locatorRegularPrice, By locatorCampaignPrice) {
        WebElement RegularPrice = driver.findElement(locatorRegularPrice);
        WebElement CampaignPrice = driver.findElement(locatorCampaignPrice);

        Dimension sizeRegularPrice = RegularPrice.getSize();
        Dimension sizeCampaignPrice = CampaignPrice.getSize();

        if (sizeRegularPrice.getHeight() < sizeCampaignPrice.getHeight()) { //акционная цена должна быть крупнее, чем обычная
            return true;
        } else {
           return false;
        }
    }
}
