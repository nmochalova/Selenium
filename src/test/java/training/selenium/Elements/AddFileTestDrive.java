package training.selenium.Elements;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddFileTestDrive extends BaseTestElements{
//Прикрепление файла в поле ввода для файлов
    @Test
    public void attachFileTest() throws InterruptedException {
        AuthAdmin("admin","admin");
        clickMainMenu(By.xpath("//li[contains(.,'Catalog')]"));
        driver.findElement(By.xpath("//a[@class='button'][contains(.,'Add New Product')]")).click();
        isTitlePresent("Add New Product");

        driver.findElement(By.cssSelector("input[type=file]")).sendKeys("C:\\temp\\image.png");
        Thread.sleep(2000);
    }

    //как в Selenium "прицепить" файл к невидимому полю ввода?
    // https://barancev.github.io/how-to-attach-file-to-invisible-field/
    @Test
    public void attachFileInvisible() {
        WebDriver driver = new FirefoxDriver();

        driver.get("http://blueimp.github.io/jQuery-File-Upload/basic.html"); //пример тестовый, ссылка не работает
        attachFile(driver, By.id("fileupload"), "C:\\temp\\image.png");

        driver.get("http://imgup.net/");
        attachFile(driver, By.id("image_image"), "C:\\temp\\image.png");

        driver.get("http://www.2imgs.com/");
        attachFile(driver, By.id("f_file"), "C:\\temp\\image.png");
    }

    //делаем поле ввода снова видимым
    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);
    }
}
