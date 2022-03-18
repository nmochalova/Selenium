package training.selenium.PageObjectsTestDrive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTests extends TestBase{
    @Test
    public void addToCart() {
        Product product = Product.newEntity().withCountProducts(3).build();

        app.addProductsToCart(product.getCountProducts());
        app.GoToCheckout();
        app.removeProductsFromCart();
        assertTrue(app.CartIsEmpty());
    }
}
