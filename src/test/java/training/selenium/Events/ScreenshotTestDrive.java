package training.selenium.Events;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import org.apache.commons.io.FileUtils;
import training.selenium.Litecard.lib.Utils;

import java.io.IOException;
import java.time.Duration;

public class ScreenshotTestDrive {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeEach
    public void start() {
        //если драйвер уже есть, то выходим
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }
        driver = new ChromeDriver();
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @Test
    public void doSomething() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        String path = captureScreen();
        System.out.println(path);
    }

    public String captureScreen() {
        String path;
        String fileImg = "\\src\\test\\java\\training\\selenium\\Events\\screen\\";
        String pathString = Utils.relativePath(fileImg);
        System.out.println(pathString);

        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = pathString + "\\" + source.getName();
            FileUtils.copyFile(source, new File(path));
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }

}
