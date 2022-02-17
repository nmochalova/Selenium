package training.selenium.Capabilities;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class CapabilitiesIE {
    @Test
    public void runIE() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.requireWindowFocus();
        WebDriver driver = new InternetExplorerDriver(options);

        System.out.println(((HasCapabilities) driver).getCapabilities());

        driver.quit();
    }
}
