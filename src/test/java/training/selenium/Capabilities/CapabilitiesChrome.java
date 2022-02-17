package training.selenium.Capabilities;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class CapabilitiesChrome {
    private WebDriver driver;

    @Test
    public void runChromeDriver() throws InterruptedException {
        //настройка сеанса браузера Chrome, которую поддерживает ChromeDriver
        //подробнее тут: https://chromedriver.chromium.org/capabilities
        //Возможности: блокировка поп-ап окон, установка директории загрузки
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        options.addArguments("start-fullscreen"); //запуск в полноэкранном режиме
        //больше настроек командной строки: https://peter.sh/experiments/chromium-command-line-switches/

        driver = new ChromeDriver(options);

        //Печать capabilities, с которым был запущен Chrome
        System.out.println(((HasCapabilities) driver).getCapabilities());

        Thread.sleep(2000);
        driver.quit();
    }
}
