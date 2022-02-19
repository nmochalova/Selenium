package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
}
