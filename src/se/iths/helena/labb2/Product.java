package se.iths.helena.labb2;

public class Product {
    private String name;
    private int price;
    private Category category;
    private String brand;
    private int BarCode;

    public Product(String name, int price, Category category, String brand, int barCode) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        BarCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getBarCode() {
        return BarCode;
    }

    public void setBarCode(int barCode) {
        BarCode = barCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        if (BarCode != product.BarCode) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (category != null ? !category.equals(product.category) : product.category != null) return false;
        return brand != null ? brand.equals(product.brand) : product.brand == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + BarCode;
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand='" + brand + '\'' +
                ", BarCode=" + BarCode +
                '}';
    }
}
