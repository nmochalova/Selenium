package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CalendarTestDrive extends BaseTestElements {
    @Test
    public void executeScriptTestDrive(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        //Creating the JavascriptExecutor interface object by Type casting
        JavascriptExecutor js = (JavascriptExecutor)driver;
        //Button Element
        WebElement button =driver.findElement(By.name("login"));
        //Get return value from script
        String text = (String) js.executeScript("return arguments[0].innerText", button);
        System.out.println(text);
        //Executing JavaScript to click on element
        js.executeScript("arguments[0].click();", button);
    }

    @Test
    public void DatepickTestDrive() {
        driver.get("https://jqueryui.com/datepicker/#default");
        isElementPresent(By.cssSelector("input#datepicker"));
        SetDatePicker(driver, By.cssSelector("input#datepicker"),"02/20/2002");
    }

    private void SetDatePicker(WebDriver driver, By locator, String strDate)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement datePicker = driver.findElement(locator);
        js.executeScript("$('{0}').datepicker('setDate', '{1}')", strDate);
    }
}
