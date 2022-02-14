package ru.stqa.training.selenium.Cookies;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

public class Cookies {
    //IE - при перезапуске сохраняет куки, а Chrome и Firefox - нет.
    @Test
    public void setCookies() {
        //инициализируем драйвер
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");

        //устанавливаем параметр куки name=value
        driver.manage().addCookie(new Cookie("admin", "admin"));

        //получаем кук по имени
        Cookie testCookie = driver.manage().getCookieNamed("admin");
        System.out.println("Установка куки testCookie:" + testCookie);

        //получаем все куки
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie c:cookies) {
            System.out.println(c.getName() + " = " + c.getValue());
        }

        //удаляем кук по имени
        driver.manage().deleteCookieNamed("admin");
        testCookie = driver.manage().getCookieNamed("admin");
        System.out.println("После удаления testCookie:" + testCookie);

        //удаляем все куки
        driver.manage().deleteAllCookies(); //удаление куки только для текущего домена
        cookies = driver.manage().getCookies();
        System.out.println("После удаления всех кук");
        for(Cookie c:cookies) {
            System.out.println(c.getName() + " = " + c.getValue());
        }

        driver.quit();
    }
}
