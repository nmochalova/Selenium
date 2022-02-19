package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class GetTextTestDrive extends BaseTestElements{
    //получение текста не видимого элемента (то что видит пользователь)
    @Test
    public void getTextTest() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("xxx");
        driver.findElement(By.name("login")).click();
        assertTrue(isElementPresent(By.cssSelector("#notices-wrapper")));
        Thread.sleep(20000);

        WebElement el = driver.findElement(By.cssSelector("#notices-wrapper"));

        if (el.isDisplayed()) {
            //если элемент видимый, то функция getText() вернет значение
                System.out.println("isDisplayed() is true");
                System.out.println("textContent : " + el.getAttribute("textContent"));
                System.out.println("getText() :  " + el.getText());
        } else {
            //если элемент не видимый, то функция getText() вернет null
            //тогда правильно пользоваться getAttribute("textContent"))
            System.out.println("isDisplayed() is false");
            System.out.println("textContent : " + el.getAttribute("textContent"));
            System.out.println("getText() :  " + el.getText());
            }
    }

    //получение текста видимого элемента  (то что видит пользователь)
    @Test
    public void getTextInvisible() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("xxx");
        driver.findElement(By.name("login")).click();

        assertTrue(isElementPresent(By.cssSelector(".notice")));
        WebElement el = driver.findElement(By.cssSelector(".notice"));

        if(el.isDisplayed()) {
            //если элемент видимый, то функция getText() вернет значение
            System.out.println("isDisplayed() is true");
            System.out.println("textContent : " + el.getAttribute("textContent"));
            System.out.println("getText() :  " + el.getText());
        } else {
            //если элемент видимый, то функция getText() вернет null
            //тогда правильно пользоваться getAttribute("textContent"))
            System.out.println("textContent : " + el.getAttribute("textContent"));
            System.out.println("getText() :  " + el.getText());
        }
    }
}
