package ru.stqa.training.selenium.Cookies;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

//Тест демонстрирует отсутствие изолированности сессий между разными браузерами IE
//и наоборот - наличие изолированности сессий и куки для разных браузеров Chrome и FireFox
public class CookiesForBrowsers {
    //НЕ РАБОТАЕТ!
    @Test
    public void manyBrowsersIE() {
        InternetExplorerOptions o1 = new InternetExplorerOptions();
        o1.ignoreZoomSettings();
        WebDriver i1 = new InternetExplorerDriver(o1);

        InternetExplorerOptions o2 = new InternetExplorerOptions();
        o2.ignoreZoomSettings();
        WebDriver i2 = new InternetExplorerDriver(o2);

        i1.get("https://www.google.com/");
        i2.get("https://www.google.com/");

        System.out.println("+++++ " + i1.manage().getCookies());
        System.out.println("+++++ " + i2.manage().getCookies());

        i1.manage().deleteAllCookies();

        //все куки будут удалены. Сессии не изолированы друг от друга
        System.out.println("----- " + i1.manage().getCookies());
        System.out.println("----- " + i2.manage().getCookies());

        i1.quit();
        i2.quit();
    }


    @Test
    public void manyBrowsersIElocal() {
        InternetExplorerOptions o1 = new InternetExplorerOptions();
        o1.ignoreZoomSettings();
        WebDriver i1 = new InternetExplorerDriver(o1);

        InternetExplorerOptions o2 = new InternetExplorerOptions();
        o2.ignoreZoomSettings();
        WebDriver i2 = new InternetExplorerDriver(o2);

        i1.get("http://localhost/litecart/");
        i2.get("http://localhost/litecart/");

        i1.manage().addCookie(new Cookie("cookie_1", "cookie_1"));
        i1.manage().addCookie(new Cookie("cookie_2", "cookie_2"));

        System.out.println("+++++ " + i1.manage().getCookies());
        System.out.println("+++++ " + i2.manage().getCookies());

        i1.manage().deleteAllCookies();

        //все куки будут удалены. Сессии не изолированы друг от друга
        System.out.println("----- " + i1.manage().getCookies());
        System.out.println("----- " + i2.manage().getCookies());

        i1.quit();
        i2.quit();
    }

    @Test
    public void manyBrowsersChrome() {
        WebDriver i1 = new ChromeDriver();
        WebDriver i2 = new ChromeDriver();

        i1.get("https://www.google.com/");
        i2.get("https://www.google.com/");

        System.out.println("+++++ " + i1.manage().getCookies());
        System.out.println("+++++ " + i2.manage().getCookies());

        i1.manage().deleteAllCookies();

        //должна быть удалена только первая кука. Сессии изолированны друг от друга.
        System.out.println("----- " + i1.manage().getCookies());
        System.out.println("----- " + i2.manage().getCookies());

        i1.quit();
        i2.quit();
    }

    @Test
    public void manyBrowsersFirefox() {
        WebDriver i1 = new FirefoxDriver();
        WebDriver i2 = new FirefoxDriver();

        i1.get("https://www.google.com/");
        i2.get("https://www.google.com/");

        System.out.println("+++++ " + i1.manage().getCookies());
        System.out.println("+++++ " + i2.manage().getCookies());

        i1.manage().deleteAllCookies();

        //должна быть удалена только первая кука. Сессии изолированны друг от друга.
        System.out.println("----- " + i1.manage().getCookies());
        System.out.println("----- " + i2.manage().getCookies());

        i1.quit();
        i2.quit();
    }
}
