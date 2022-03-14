package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import training.selenium.Litecard.lib.ui.CheckProduct;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NewProductTest extends CheckProduct {
    //Задание 12. Cценарий добавления товара
    @Test
    public void newProduct() {
        int i = randomNumber(10000,99999);
        String category = "Apples"; //Будем добавлять в категорию Apples, если ее нет, то нужно создать.
        String name = "Apple_"+i;  //имя продукта
        String code = "a"+i;       //код продукта
        String shortDesc = "Яблоко - экологичный продукт красного цвета для лучшего вкуса и здоровья.";
        String fullDesc = "Сочный плод яблони, который употребляется в пищу в свежем и запеченном виде, служит сырьём в кулинарии и для приготовления напитков. Наибольшее распространение получила яблоня домашняя, реже выращивают яблоню сливолистную. Размер красных, зелёных или жёлтых шаровидных плодов 5-13 см в диаметре. Происходит из Центральной Азии, где до сих пор произрастает дикорастущий предок яблони домашней - яблоня Сиверса. На сегодняшний день существует множество сортов этого вида яблони, произрастающих в различных климатических условиях. По времени созревания отличают летние, осенние и зимние сорта, более поздние сорта отличаются хорошей стойкостью.";
        String fileImg = "\\src\\test\\java\\training\\selenium\\Litecard\\img\\red_apple.jpg";
        String purchasePrice = "54.0";
        Double priceUSD = 40.06;

        AuthAdmin("admin","admin");
        clickMainMenu(By.xpath("//li[contains(.,'Catalog')]"));
        assertTrue(checkCategory(category),"The category 'Apples' does not exist.");
        driver.findElement(By.xpath("//a[@class='button'][contains(.,'Add New Product')]")).click();
        isTitlePresent("Add New Product");

        fillingGeneral(category,name, code, fileImg);
        fillingInformation(shortDesc, fullDesc);
        fillingPrices(purchasePrice, priceUSD);
        saveProduct();

        System.out.println("Create the new product name: " + name);

        assertTrue(CheckProductAdded(category,name),"The product " + name + "has not been added.");
    }
}
