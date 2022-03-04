package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;
import training.selenium.Litecard.lib.BaseModule;
import training.selenium.Litecard.lib.DataTable.DataTableTwoColumns;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortCountriesTest extends BaseModule {
    @BeforeEach
    public void startBrowserChrome() {
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    //8 а) на странице Countries проверяет, что страны расположены в алфавитном порядке
    @Test
    public void TestSortCountries() {
        AuthAdmin("admin","admin");
        clickMainMenu(By.xpath("//li[contains(.,'Countries')]"));
        isTitlePresent("Countries");

        ArrayList<String> name = readTableOneColumn(By.cssSelector(".dataTable"),5);
        assertTrue(checkSort(name),"Alphabetical countries sorting is broken");

        logout();
    }

    //8 б) для стран, у которых количество зон отлично от нуля - открывает и проверяет, что геозоны расположены в алфавитном порядке
    @Test
    public void TestSortZones() {
        AuthAdmin("admin", "admin");
        clickMainMenu(By.xpath("//li[contains(.,'Countries')]"));
        isTitlePresent("Countries");

        List<DataTableTwoColumns> TwoColumnsList = readTableTwoColumns(By.cssSelector(".dataTable"), 4, 5);

        ArrayList<String> countries = new ArrayList<String>();
        for (DataTableTwoColumns str : TwoColumnsList) {
            if (Integer.parseInt(str.zones) > 0) {
                countries.add(str.name); //список стран, у которых кол-во зон отлично от нуля
            }
        }
        for (String ctr : countries) {
            clickMainMenu(By.xpath("//td/a[contains(.,'" + ctr + "')]"));
            isTitlePresent("Edit Country");
            ArrayList<String> name = readTableOneColumn(By.cssSelector("#table-zones"), 3);
            assertTrue(checkSort(name), "Alphabetical zones sorting is broken");
            clickMainMenu(By.xpath("//li[contains(.,'Countries')]"));
            isTitlePresent("Countries");
        }
        logout();
    }

    //9. (первый вариант решения) На странице Geo Zones тест заходит в каждую из стран и проверяет, что зоны расположены в алфавитном порядке.
    @Test
    public void testSortGeoZonesFirst() {
        AuthAdmin("admin", "admin");
        clickMainMenu(By.xpath("//li[contains(.,'Geo Zones')]"));
        isTitlePresent("Geo Zones");

        ArrayList<String> countries = readTableOneColumn(By.cssSelector(".dataTable"),3);

        for (String ctr : countries) {
            clickMainMenu(By.xpath("//td/a[contains(.,'" + ctr + "')]"));
            isTitlePresent("Edit Geo Zone");
            ArrayList<String> zoneSelected = new ArrayList<String>();

            List<WebElement> zone =
                    driver.findElements(By.cssSelector("#table-zones td:nth-child(3) option[selected=selected]"));
            for (WebElement wel : zone) {
                zoneSelected.add(wel.getAttribute("textContent"));
            }

            assertTrue(checkSort(zoneSelected), "Alphabetical zones sorting is broken for " + ctr);
            clickMainMenu(By.xpath("//li[contains(.,'Geo Zones')]"));
            isTitlePresent("Geo Zones");
        }
        logout();
    }

    //9. (второй вариант решения) На странице Geo Zones тест заходит в каждую из стран и проверяет, что зоны расположены в алфавитном порядке.
    @Test
    public void testSortGeoZonesSecond() {
        AuthAdmin("admin", "admin");
        clickMainMenu(By.xpath("//li[contains(.,'Geo Zones')]"));
        isTitlePresent("Geo Zones");

        ArrayList<String> countries = readTableOneColumn(By.cssSelector(".dataTable"),3);

        for (String ctr : countries) {
            clickMainMenu(By.xpath("//td/a[contains(.,'" + ctr + "')]"));
            isTitlePresent("Edit Geo Zone");

            ArrayList<WebElement> zone = readTableByWebElement(By.cssSelector("#table-zones"), 3);
            ArrayList<String> zoneSelected = new ArrayList<String>();

            for (WebElement wel : zone) {
                List<WebElement> options = wel.findElements(By.tagName("option"));
                for (WebElement opt : options) {
                    if (opt.getAttribute("selected") != null) {
                        zoneSelected.add(opt.getAttribute("textContent")); //формируем массив зон с аттрибутом selected
                    }
                }
            }
            assertTrue(checkSort(zoneSelected), "Alphabetical zones sorting is broken for " + ctr);
            clickMainMenu(By.xpath("//li[contains(.,'Geo Zones')]"));
            isTitlePresent("Geo Zones");
        }
        logout();
    }
}
