package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    //https://barancev.github.io/how-to-set-datepicker-value/
    //как в Selenium выбрать дату в jQuery Datepicker?
    @Test
    public void DatepickTestDrive() throws InterruptedException {
        driver.get("https://jqueryui.com/datepicker/#default");
        String locator ="input#datepicker";

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe.demo-frame")));
        Thread.sleep(2000);
        SetDatePicker(driver, locator,"02/20/2002");
    }

    private void SetDatePicker(WebDriver driver, String locator, String strDate) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        isElementPresent(By.cssSelector(locator));

        System.out.println("$('"+ locator + "').datepicker('setDate', '" + strDate + "')");
        js.executeScript("$('"+ locator + "').datepicker('setDate', '" + strDate + "')");
        Thread.sleep(2000);
    }
}
