package training.selenium.PageObjectsTestDrive.tests;

import org.junit.jupiter.api.Test;
import training.selenium.PageObjectsTestDrive.model.Product;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTests extends TestBase   {
    @Test
    public void addToCart() {
        Product product = Product.newEntity().withCountProducts(3).build();

        app.addProductsToCart(product);
        app.GoToCheckout();
        app.removeProductsFromCart();
        assertTrue(app.CartIsEmpty());
    }
}
