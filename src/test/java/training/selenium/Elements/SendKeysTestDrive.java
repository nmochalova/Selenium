package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SendKeysTestDrive extends BaseTestElements{
    @Test
    public void SendKeysAndEnter() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin" + Keys.TAB);
        driver.findElement(By.name("password")).sendKeys("admin" + Keys.ENTER);
        logout();
    }

   @Test
   public void SendKeysInFieldWithMask() {
       // если в поле есть маска -- надо перед вводом текста перейти в начало
       driver.get("http://localhost/litecart/admin/");
       driver.findElement(By.name("username")).sendKeys(Keys.HOME + "01.01.2001");
   }

    @Test
    public void Clear() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        //Нельзя использовать команду clear для очистки файловых полей ввода!
    }

    @Test
    public void ActionTestDrive() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        WebElement buttonLogin = driver.findElement(By.name("login"));

        //https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/interactions/Actions.html
        new Actions(driver)
                .moveToElement(buttonLogin)
                .click()
                .perform();

        logout();
    }
}
