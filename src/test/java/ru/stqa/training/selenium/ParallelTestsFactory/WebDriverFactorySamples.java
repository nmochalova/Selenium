package ru.stqa.training.selenium.ParallelTestsFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool; //build.gradle >> implementation 'ru.stqa.selenium:webdriver-factory:4.4'

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class WebDriverFactorySamples {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void startBrowser() {
        //https://github.com/barancev/webdriver-factory
        driver = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterAll
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

    public void doSomething() {
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        wait.until(titleIs("webdriver - Поиск в Google"));
    }
}
