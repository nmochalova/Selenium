package training.selenium.AdminLitecard.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends Page{
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickByCategory() {
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
    }
}
