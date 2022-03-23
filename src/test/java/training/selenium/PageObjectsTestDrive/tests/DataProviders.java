package training.selenium.PageObjectsTestDrive.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import training.selenium.PageObjectsTestDrive.model.Product;

public class DataProviders {
    @DataProvider
    public static Object[][] validCustomers() {
        return new Object[][] {
                { Product.newEntity()
                        .withCountProducts(3).build() },
                { Product.newEntity()
                        .withCountProducts(4).build() },
                /* ... */
        };
    }
}