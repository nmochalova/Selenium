package training.selenium.Events;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.logging.*;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.decorators.Decorated;
import org.openqa.selenium.support.decorators.WebDriverDecorator;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.selenium.Litecard.lib.Utils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyWebDriverDecorator {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriver original;
    public WebDriverWait wait;
    public Logger logger = Logger.getLogger("MyLog");
    public FileHandler fh;

    @Test
    public void test(){
        logging();
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnK")).click();
        driver.quit();
    }

    public class LoggingDecorator extends WebDriverDecorator {
        @Override
        public void beforeCall(Decorated<?> target, Method method, Object[] args) {
            if (args != null)
            for (Object a : args) {
                if (a != null)  logger.info(method.getName() + " -> " + a);
            }
        }

        @Override
        public void afterCall(Decorated<?> target, Method method, Object[] args, Object res) {
            if (args != null)
                for (Object a : args) {
                    if (a != null)  logger.info(method.getName() + " -> " + a + " found");
                }
        }

        @Override
        public Object onError(Decorated<?> target, Method method, Object[] args, InvocationTargetException e) throws Throwable {
            String path;
            String fileImg = "\\src\\test\\java\\training\\selenium\\Events\\screen\\";
            String pathString = Utils.relativePath(fileImg);
            System.out.println(pathString);

            try {
                File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                path = pathString + "\\" + source.getName();
                FileUtils.copyFile(source, new File(path));
            }
            catch(IOException ex) {
                path = "Failed to capture screenshot: " + ex.getMessage();
            }
            logger.info(path);
            return super.onError(target, method, args, e);
        }
    }

    @BeforeEach
    public void start() throws IOException {
        //если драйвер уже есть, то выходим
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }
        //иначе создаем новый
        original = new ChromeDriver();
        driver = new LoggingDecorator().decorate(original);

        tlDriver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //останов драйвера в конце теста
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    public void logging() {
        try {
            String fileImg = "\\src\\test\\java\\training\\selenium\\Events\\log\\";
            String pathString = Utils.relativePath(fileImg);
            String path = pathString + "\\MyLogFile.log";
            fh = new FileHandler(path);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
