package training.selenium.PageObjectsTestDrive;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Application {
    private WebDriverWait wait;
    private WebDriver driver;

    private final MainLitecardPage mainLitecardPage;
    private final BoxProductPage boxProductPage;
    private final CustomerCartPage customerCartPage;
    private final Page page;

    public Application() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        mainLitecardPage = new MainLitecardPage(driver);
        boxProductPage = new BoxProductPage(driver);
        customerCartPage = new CustomerCartPage(driver);
        page = new Page(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductsToCart(int countProducts) {
        mainLitecardPage.open();

        for (int i=0;i<countProducts;i++) {
            mainLitecardPage.clickProduct();
            boxProductPage.addToCart();
            mainLitecardPage.clickHome();
        }
    }

    public void GoToCheckout() {
        mainLitecardPage.clickCheckout();
        customerCartPage.stopAction();  //фиксируем продукт в корзине на первом
    }

    public void removeProductsFromCart() {
       while(!customerCartPage.getProducts().isEmpty()) {
           //пока продукт в корзине не последний удаляем его и проверяем, что он удален из таблицы
           customerCartPage.RemoveProductAndCheckTable(customerCartPage.getProducts().size());
       }
        if (customerCartPage.getProducts().isEmpty()) {
            //последний продукт не имеет плашки с перечнем продуктов. Для него таблицу не проверяем.
            customerCartPage.clickRemoveButton();
        }
    }

    public boolean CartIsEmpty() {
        return customerCartPage.isEmpty();
    }
}
