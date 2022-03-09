package training.selenium.Litecard.lib.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.BaseModule;

import java.util.HashMap;

public class CheckAccount extends BaseModule {
    public void NewAccount(HashMap<String,String> accountDetails) {
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys(accountDetails.get("firstname"));
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys(accountDetails.get("lastname"));
        driver.findElement(By.cssSelector("input[name=address1]")).sendKeys(accountDetails.get("address1"));
        driver.findElement(By.cssSelector("input[name=postcode]")).sendKeys(accountDetails.get("postcode"));
        driver.findElement(By.cssSelector("input[name=city]")).sendKeys(accountDetails.get("city"));

        selectCountry(accountDetails.get("country"),accountDetails.get("zone"));

        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(accountDetails.get("email"));
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys(accountDetails.get("phone"));
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(accountDetails.get("password"));
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(accountDetails.get("password"));
        driver.findElement(By.cssSelector("button[type=submit]")).click();
    }

    private void selectCountry(String country, String zone) {
        driver.findElement(By.cssSelector("span.select2")).click();
        isElementPresent(By.cssSelector(".select2-dropdown"));
        WebElement wel = driver.findElement(By.cssSelector(".select2-dropdown"));
        wel.findElement(By.cssSelector("input.select2-search__field")).sendKeys( country + Keys.ENTER);
        driver.findElement(By.cssSelector("select[name=zone_code]")).sendKeys(zone);
    }
}
