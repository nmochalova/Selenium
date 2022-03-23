package training.selenium.Events;

import org.junit.jupiter.api.Test;

import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.decorators.Decorated;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;

public class MyWebDriverDecoratorTestDriver {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriver original;
    public WebDriverWait wait;

    public class LoggingDecorator extends WebDriverDecorator {
     //   final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getName());
        @Override
        public void beforeCall(Decorated<?> target, Method method, Object[] args) {
         //   logger.debug("before {}.{}({})", target, method, args);
        }
        @Override
        public void afterCall(Decorated<?> target, Method method, Object[] args, Object res) {
         //   logger.debug("after {}.{}({}) => {}", target, method, args, res);
        }
        @Override
        public Object onError(Decorated<?> target, Method method, Object[] args, InvocationTargetException e) throws Throwable {

            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screen = new File("screen-"+System.currentTimeMillis()+".png");
//            try {
//               Files.copy(tempFile, screen);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            System.out.println(screen);

            return super.onError(target, method, args, e);
        }
    }

    @Test
    public void test(){
        original = new FirefoxDriver();
        driver = new LoggingDecorator().decorate(original);

        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        driver.quit();
    }
}
