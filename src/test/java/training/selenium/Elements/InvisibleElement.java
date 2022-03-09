package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class InvisibleElement extends BaseTestElements{
    /***
     * Selenium врёт про видимость:
     * - Прозрачные элементы
     * - Элементы цвета фона
     * - Элементы, скрытые позади других элементов
     * - Элементы за левым или верхним краем экрана     *
     */
    @Test
    public void getAttributeBoolean() {
        driver.get("https://output.jsbin.com/saqoca/2");  //Страница с невидимыми элементами
        WebElement el = driver.findElement(By.cssSelector("#behind")); //этот элемент позади блока
        if (el.isDisplayed()) {
            System.out.println(el.getAttribute("textContent"));
        } else {
            System.out.println("Element is invisible");
        }
    }

    @Test
    public void clickDisplayedButton() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        WebElement button = driver.findElement(By.tagName("button"));
        if(button.isDisplayed()) {
            button.click();
        }
    }

    //Это пример выбора значения выпадющего списка при помощи JavaScript
    //Метод предназначен для демонстрации как это можно сделать если элемент select имеет opasity=0 (т.е. невидимый)
    @Test
    public void JavascriptForInvisibleElement() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.get("http://localhost/litecart/en/");
        clickMainMenu(By.xpath("//a[contains(.,'New customers click here')]"));
        isTitlePresent("Create Account");

        WebElement select = driver.findElement(By.cssSelector("select"));

        js.executeScript("arguments[0].selectedIndex = 3",select);
        js.executeScript("arguments[0].selectedIndex = 3; arguments[0].dispatchEvent(new Event('change'))", select);

        Thread.sleep(2000);
    }

    //Второй способ: сделать элемент видимым (т.е. opasity = 1)
    @Test
    public void opasityOnTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        driver.get("http://localhost/litecart/en/");
        clickMainMenu(By.xpath("//a[contains(.,'New customers click here')]"));
        isTitlePresent("Create Account");

        WebElement select = driver.findElement(By.cssSelector("select"));

        js.executeScript("arguments[0].style.opacity = 0",select); //сделать прозрачным
        Thread.sleep(2000);
        js.executeScript("arguments[0].style.opacity = 1",select); //сделать видимым
        Thread.sleep(2000);
    }

    @Test
    public void timeTest() {
        long start_time = System.currentTimeMillis();
        String str = String.valueOf(start_time);
        System.out.println(str);
        //https://www.guru99.com/execute-javascript-selenium-webdriver.html
    }

}
