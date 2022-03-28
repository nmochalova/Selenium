package training.selenium.AdminLitecard.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.selenium.AdminLitecard.pages.AuthPage;
import training.selenium.AdminLitecard.pages.CatalogPage;
import training.selenium.AdminLitecard.pages.HomePage;
import training.selenium.AdminLitecard.pages.Page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Application {
    private WebDriverWait wait;
    private WebDriver driver;
    private final Page page;
    private final AuthPage authPage;
    private final HomePage homePage;
    private final CatalogPage catalogPage;

    public Application() {
        LoggingPreferences prefs = new LoggingPreferences(); //повышаем уровень логов, которые selenium должен забирать из браузера
        prefs.enable("browser", Level.ALL);
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.LOGGING_PREFS, prefs);
        driver = new ChromeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        authPage = new AuthPage(driver);
        homePage = new HomePage(driver);
        catalogPage = new CatalogPage(driver);
        page = new Page(driver);
    }

    //Извлечение лога браузера
    public void checkLogs(String name) {
        List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
        if (!logs.isEmpty()) {
            for (LogEntry l : logs) {
                System.err.println("Log browser for product " + name +  " >> " + l.getLevel() + l.getMessage());
            }
        }
    }

    public void quit() {
        driver.quit();
    }

    public void AuthAdmin(String username, String password)  {
        authPage.AuthPageOpen();
        authPage.loginSendKey(username);
        authPage.passwordSendKey(password);
        authPage.loginButtonClick();
        assertTrue(page.isTitlePresent("My Store"));
    }

    public ArrayList<String> getProductList() {
        homePage.clickByCategory();
        return catalogPage.selectProductsFromTable();
    }

    public void clickProduct(String name)  {
        catalogPage.clickProductByName(name);
        assertTrue(page.isTitlePresent("Edit Product: "+name+" | My Store"),"!!! Don't click !!!");
    }

    public void clickButtonCancel() {
        driver.findElement(By.cssSelector("button[name=cancel]")).click();
    }
}
