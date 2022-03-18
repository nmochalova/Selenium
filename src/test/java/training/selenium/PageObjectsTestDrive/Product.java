package training.selenium.PageObjectsTestDrive;

public class Product {
    private int countProducts;

    public static Builder newEntity() {
        return new Product().new Builder();
    }

    public int getCountProducts() {
        return countProducts;
    }

    public class Builder {
        private Builder() {
        }

        public Builder withCountProducts(int countProducts) {
            Product.this.countProducts = countProducts;
            return this;
        }
        public Product build() {return Product.this; }
    }
}
