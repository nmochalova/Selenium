package training.selenium.Logging;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class ProxyTestDrive {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;
    public BrowserMobProxy proxy;
 //   public Proxy proxy;

    @BeforeEach
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            return;
        }
        // start the proxy
        proxy = new BrowserMobProxyServer();
        proxy.start(0);
        // get the Selenium proxy object
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
        // configure it as a desired capability
        ChromeOptions options = new ChromeOptions();
        options.setCapability(CapabilityType.PROXY, seleniumProxy);
        driver = new ChromeDriver(options);

//        ChromeOptions options = new ChromeOptions();
//        // Add the WebDriver proxy capability.
//        proxy = new Proxy();
//        proxy.setHttpProxy("myhttpproxy:3337");
//        options.setCapability("proxy", proxy);
//        driver = new ChromeDriver(options);

//        ChromeOptions options = new ChromeOptions();
//        proxy = new Proxy();
//        proxy.setHttpProxy("localhost:8888");
//        options.setCapability("proxy", proxy);
//        driver = new ChromeDriver(options);

        tlDriver.set(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));
    }

    @Test
    public void doSomething() {
        proxy.newHar();

        driver.get("http://www.google.com");

        Har har = proxy.endHar();
        har.getLog().getEntries().forEach(l -> System.out.println(l.getResponse().getStatus() + ":" + l.getRequest().getUrl()));
    }
}
