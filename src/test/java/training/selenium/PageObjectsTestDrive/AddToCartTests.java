package training.selenium.PageObjectsTestDrive;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddToCartTests extends TestBase{
    @Test
    public void addToCart() {
        app.addProductsToCart(3);
        app.GoToCheckout();
        app.removeProductsFromCart();
        assertTrue(app.CartIsEmpty());
    }
}
