package ru.stqa.training.selenium.Capabilities;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

public class CapabilitiesFirefox {

   @Test
   public void runFirefoxNewChrome() {
       WebDriver driver = new FirefoxDriver();
       driver.quit();
   }

    //Запуск FireFox Nightly
    @Test
    public void runFirefoxNightly() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        WebDriver driver = new FirefoxDriver(options);
        driver.quit();
    }

    @Test
    public void runFirefoxProfile() {
        FirefoxProfile profile = new FirefoxProfile();
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        WebDriver driver = new FirefoxDriver(options);
        driver.quit();
    }

    //НЕ РАБОТАЕТ. УСТАРЕЛО
//    @Test
//    public void setOptionFirefox() {
//        FirefoxOptions options = new FirefoxOptions();
//        options.addPreference("network.proxy.type", 0);
//        WebDriver driver = new RemoteWebDriver(options);
//        driver.quit();
//    }


}
