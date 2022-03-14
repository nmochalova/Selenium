package training.selenium.Litecard.Tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import training.selenium.Litecard.lib.ui.CheckBasket;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToBasketTest extends CheckBasket {
    /***
     * Задание 13. Сделайте сценарий работы с корзиной
     * Сделайте сценарий для добавления товаров в корзину и удаления товаров из корзины.
     *
     * 1) открыть главную страницу
     * 2) открыть первый товар из списка
     * 2) добавить его в корзину (при этом может случайно добавиться товар, который там уже есть, ничего страшного)
     * 3) подождать, пока счётчик товаров в корзине обновится
     * 4) вернуться на главную страницу, повторить предыдущие шаги ещё два раза, чтобы в общей сложности в корзине было 3 единицы товара
     * 5) открыть корзину (в правом верхнем углу кликнуть по ссылке Checkout)
     * 6) удалить все товары из корзины один за другим, после каждого удаления подождать, пока внизу обновится таблица
     */
    @Test
    public void CheckBasket() {
        driver.get("http://localhost/litecart/en/");

        for (int i=0;i<3;i++) {
            String firstProduct = "#box-most-popular li.product:first-child";
            clickProductAndAddToCart(driver, By.cssSelector(firstProduct));
            clickHome();
        }

        clickCheckout();
        stopAction();

        while(!isPresent()) {
            List<WebElement> products = driver.findElements(By.cssSelector(".shortcut"));
            if (products.isEmpty()) {
                //отдельная ветка, потому что последний продукт не имеет плашки с перечнем продуктов
                removeProductsFromCart();
                assertTrue(isPresent());
            } else {
                checkTableAfterRemoveProduct(products.size());
            }
        }
    }

    private boolean isPresent() {
        return isElementPresent(By.tagName("em"));
    }
}
