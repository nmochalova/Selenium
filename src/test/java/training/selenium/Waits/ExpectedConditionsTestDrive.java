package training.selenium.Waits;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

//Другие условия ожидания
// https://www.selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/ExpectedConditions.html
public class ExpectedConditionsTestDrive {
    WebDriver driver;
    WebDriverWait wait;
    public final int WAIT = 10;
    @Test
    public void doSomething()  {
        ////Условия ожидания для браузера
        //ожидание пока заголовок страницы станет нужным (вариант проверки, что нужная страница загрузилась)
        wait.until(titleIs("Webdriver - Поиск в Google"));

        //ожидание пока адрес страницы станет нужным (вариант проверки, что нужная страница загрузилась)
        wait.until(urlContains("login.php"));

        //ожидание диалогового окна
        wait.until(alertIsPresent());

        //ожидание появления окон в заданном количестве
        wait.until(numberOfWindowsToBe(2));

        ////Проверка свойств элементов
        //Проверка исчезновения элемента
        WebElement element = driver.findElement(By.name("q"));
        wait.until(stalenessOf(element));

        //Проверка видимости элемента
        wait.until(visibilityOf(element));

        //Ожидание и проверка, что элементу присвоен или наоборот отобран какой-то класс (например, определенный стиль - красный цвет)
        wait.until(attributeContains(element,"class","visible"));

        //Ожидание пока у элемента появится нужный текст
        wait.until(textToBePresentInElement(element,"ОК"));

        //Ожидание выделения элемента (selected)
        wait.until(elementToBeSelected(element));

        //Ожидается, что элемент видимый и не дизейбл
        wait.until(elementToBeClickable(element));
    }

    @BeforeEach
    public void startBrowser() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        wait = new WebDriverWait(driver, Duration.ofSeconds(100));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
