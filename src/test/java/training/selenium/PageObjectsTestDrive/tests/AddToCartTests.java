package training.selenium.PageObjectsTestDrive.tests;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import training.selenium.PageObjectsTestDrive.model.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(DataProviderRunner.class)
public class AddToCartTests extends TestBase {

    @Test
    @UseDataProvider(value = "validCustomers", location = DataProviders.class)
    public void addToCart(Product product) {
        app.addProductsToCart(product);
        app.GoToCheckout();
        app.removeProductsFromCart();
        assertTrue(app.CartIsEmpty());
    }
}
