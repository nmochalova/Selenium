package training.selenium.Litecard.lib.DataTable;

import org.openqa.selenium.WebElement;

public class DataTableWebElements {
    public WebElement columnOne;
    public WebElement columnTwo;

    public  DataTableWebElements (WebElement c1, WebElement c2) {
        columnOne = c1;
        columnTwo = c2;
    }
}
