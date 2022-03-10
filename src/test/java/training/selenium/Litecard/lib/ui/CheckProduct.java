package training.selenium.Litecard.lib.ui;

import org.openqa.selenium.By;
import training.selenium.Litecard.lib.BaseModule;
import org.openqa.selenium.NoSuchElementException;
import training.selenium.Litecard.lib.DataTable.DataTableWebElements;

import java.util.ArrayList;
import java.util.List;

public class CheckProduct extends BaseModule {
    //Проверка категории на существование
    public boolean checkCategory(String category) {
        try {
            driver.findElement(By.xpath("//a[contains(.,'" + category +"')]"));
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public boolean CheckProductAdded(String category, String name) {
        driver.findElement(By.xpath("//a[contains(.,'" + category +"')]")).click();
        ArrayList<String> names = readTableOneColumn(By.cssSelector(".dataTable"),3);
        for (int i=0;i<names.size();i++) {
            if(names.get(i).equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void fillingGeneral(String category, String name, String code, String fileImg) {
        driver.findElement(By.xpath("//a[contains(.,'General')]")).click();
        driver.findElement(By.cssSelector("label:nth-of-type(1) input")).click();
        driver.findElement(By.cssSelector("input[name*=name]")).sendKeys(name);
        driver.findElement(By.cssSelector("input[name=code]")).sendKeys(code);

        clickCategory(category);

        driver.findElement(By.cssSelector("input[name=quantity]")).clear();
        driver.findElement(By.cssSelector("input[name=quantity]")).sendKeys("5");

        String pathString = relativePath(fileImg);
        driver.findElement(By.cssSelector("input[type=file]")).sendKeys(pathString);

        driver.findElement(By.cssSelector("input[name=date_valid_from]")).sendKeys(currentDate());
    }

    public void fillingInformation(String shortDesc, String fullDesc) {
        driver.findElement(By.xpath("//a[contains(.,'Information')]")).click();
        driver.findElement(By.cssSelector("select[name=manufacturer_id]")).sendKeys("ACME Corp.");
        driver.findElement(By.cssSelector("input[name^=short_description]")).sendKeys(shortDesc);
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys(fullDesc);
    }

    public void fillingPrices(String purchasePrice, Double priceUSD) {
        driver.findElement(By.xpath("//a[contains(.,'Prices')]")).click();

        driver.findElement(By.cssSelector("input[name=purchase_price]")).clear();
        driver.findElement(By.cssSelector("input[name=purchase_price]")).sendKeys(purchasePrice);

        String priceUSDStr = String.valueOf(priceUSD);
        driver.findElement(By.cssSelector("#tab-prices table:nth-of-type(3) tr:nth-of-type(2) input[name^=prices]")).sendKeys(priceUSDStr);
    }

    public void saveProduct() {
        driver.findElement(By.cssSelector("button[name=save]")).click();
        isElementPresent(By.xpath("//div[@class='notice success'][contains(.,'Changes were successfully saved')]"));
    }

    private void clickCategory(String category) {
        String tableCategories = "#tab-general > table > tbody > tr:nth-of-type(4) table";
        List<DataTableWebElements> columnsOfCategory;
        columnsOfCategory = readTableReturnTableWebElements(By.cssSelector(tableCategories),0,1);

        for (DataTableWebElements ttc : columnsOfCategory) {
            if (ttc.columnTwo.getText().equals("")) {
                ttc.columnOne.findElement(By.tagName("input")).click();
            }
            if(ttc.columnTwo.getText().equals(category)) {
                ttc.columnOne.findElement(By.tagName("input")).click();
            }
        }
    }
}
