package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class Attributes extends BaseTestElements{
   //F12 - Elements - Properties Если в списке отсутствует свойство, то метод getAttribut вернт null

    @Test
    public void getAttributeTestDrive()  {
        //Кнопка логин. Получение свойств DOM-элементов и аттрибутов HTML-документа
        //Когда браузер загружает страницу, он «читает» (также говорят: «парсит») HTML и генерирует из него DOM-объекты.
        // Для узлов-элементов большинство стандартных HTML-атрибутов автоматически становятся свойствами DOM-объектов.
        //Но преобразование атрибута в свойство происходит не один-в-один! https://javascript.ru/tutorial/dom/attributes
        System.out.println("========= Кнопка Login ========");

        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.cssSelector("[name=login]"));

        System.out.println(login.getAttribute("value")); //атрибут HTML
        System.out.println(login.getAttribute("textContent")); //свойство DOM-объекта (свободный текст между открывающим и закрывающим тегами)
        System.out.println(login.getAttribute("outerHTML")); //исходный код эл-та с разметкой
   }

    @Test
    public void getAttributeValue() {
        //value - содержимое полей ввода.
        // Для полей ввода функция getText всегда возвращает пустую строку, видимо потому что аттрибут value тега input пустой
        //однако свойство value DOM-объекта содержит введенное значение, а Selenium возвращает именно значение свойства.
        System.out.println("========= Поле ввода ========");

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        WebElement name = driver.findElement(By.name("username"));

        System.out.println(name.getAttribute("outerHTML")); //в теге атрибут value пустой
        System.out.println(name.getAttribute("value"));     //в свойстве value есть введенное значение
    }

    @Test
    public void getAttributeHref() {
        //Поля с ссылками: href, src - нормализация адресов
        //Значение аттрибутов и свойств могут отличаться
        System.out.println("========= Ссылка Home ========");

        driver.get("http://localhost/litecart/en/");
        WebElement home = driver.findElement(By.cssSelector("#breadcrumbs a"));
        System.out.println(home.getAttribute("outerHTML")); //в теге атрибут href имеет относительный адрес
        System.out.println(home.getAttribute("href"));  //свойство href имеет полный адрес
    }

    @Test
    public void getAttributeBoolean() {
        //true или null - булевские атрибуты
        //selenium возвращает true, если эл-т присутствует и null - если отсутствует
        System.out.println("========= Выпадающий список ========");

        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.cssSelector(".fancybox-region")).click();
        assertTrue(isElementPresent(By.cssSelector("[name=currency_code] [value=USD]")));
        WebElement ch = driver.findElement(By.cssSelector("[name=currency_code] [value=USD]"));
        System.out.println(ch.getAttribute("outerHTML")); //в теге атрибут selected=selected
        System.out.println(ch.getAttribute("selected"));  //свойство selected=true
    }
}
