package training.selenium.AdminLitecard.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AuthPage extends Page{

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public void AuthPageOpen() {
        driver.get("http://localhost/litecart/admin/");
    }

    public void loginSendKey(String username) {
        driver.findElement(By.name("username")).sendKeys(username);
    }

    public void passwordSendKey(String password) {
        driver.findElement(By.name("password")).sendKeys(password);
    }

    public void loginButtonClick()  {
        driver.findElement(By.name("login")).click();
    }
}
