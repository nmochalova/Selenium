package training.selenium.Remote;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

/***
 * Необходимо на удаленном сервере установить Selenium Server
 * Также на сервере должны располагаться драйвера браузеров
 * Сервер поднимается по умолчанию на порт 4444, таймаут по-умолчанию 30 минут (время когда сервер сам завершает
 * сессию браузера, если к нему прекращаются команды)
 * Адрес: http://localhost:4444/ также доступен с локальной машины, по нему видны запущенные сессии,
 * а также есть возможность завершать.
 */
public class RemoteWebDriverTest {
    private WebDriver driver;

    @BeforeEach
    public void start() throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
    }
}
