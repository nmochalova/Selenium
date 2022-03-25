package training.selenium.AdminLitecard.tests;
import org.junit.Test;

import java.util.ArrayList;

public class CatalogTest extends TestBase {
    @Test
    public void checkLoggingTest() {
        app.AuthAdmin("admin","admin");
        ArrayList<String> products = app.getProductList();
        for (String pr : products) {
            app.clickProduct(pr);
            app.checkLogs(pr);
            app.clickButtonCancel();
        }
    }
}
