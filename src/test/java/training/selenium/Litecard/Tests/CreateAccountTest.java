package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.BaseModule;
import training.selenium.Litecard.lib.ui.CheckAccount;

import java.text.MessageFormat;
import java.util.HashMap;

public class CreateAccountTest extends CheckAccount {
    @Test
    public void createNewAccount() {
        driver.get("http://localhost/litecart/en/");
        clickMainMenu(By.xpath("//a[contains(.,'New customers click here')]"));
        isTitlePresent("Create Account");

        HashMap<String,String> accountDetails = new HashMap<String,String>();
        accountDetails.put("firstname","test");
        accountDetails.put("lastname", "test");
        accountDetails.put("address1","test");
        accountDetails.put("postcode", "11111");
        accountDetails.put("city","test");
        accountDetails.put("phone", "+79031111111");
        accountDetails.put("password", "123456");
        accountDetails.put("country","United States");
        accountDetails.put("zone","Colorado");

        long start_time = System.currentTimeMillis();
        String email = MessageFormat.format("a_{0,number,###############}@mail.ru",start_time);
        accountDetails.put("email", email);

        NewAccount(accountDetails);
        System.out.println("email / password: " + email + " / " +  accountDetails.get("password"));

        logoutLitecard();
        loginLitecard(email, accountDetails.get("password"));
        logoutLitecard();
    }
}
