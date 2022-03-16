package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.BaseModule;

import java.util.ArrayList;
import java.util.List;

/***
 * Задание 14. Проверьте, что ссылки открываются в новом окне
 * 1) зайти в админку
 * 2) открыть пункт меню Countries (или страницу http://localhost/litecart/admin/?app=countries&doc=countries)
 * 3) открыть на редактирование какую-нибудь страну или начать создание новой
 * 4) возле некоторых полей есть ссылки с иконкой в виде квадратика со стрелкой
 * -- они ведут на внешние страницы и открываются в новом окне, именно это и нужно проверить.
 */
public class OpenNewWindowTest extends BaseModule {
    @Test
    public void EditCountry()  {
        AuthAdmin("admin","admin");
        clickMainMenu(By.xpath("//li[contains(.,'Countries')]"));
        isTitlePresent("Countries");
        ArrayList<WebElement> names = readTableByWebElement(By.cssSelector("table.dataTable"),5);
        names.get(0).findElement(By.tagName("a")).click(); //кликаем первую страну из списка
        isTitlePresent("Edit Country");

        List<WebElement> links = driver.findElements(By.cssSelector("form a[target=_blank]"));
        for(WebElement lk : links) {
            thereAndBack(lk);
        }

        driver.findElement(By.cssSelector("button[name=cancel]")).click();
        logout();
    }
}
