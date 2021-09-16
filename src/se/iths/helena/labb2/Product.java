package se.iths.helena.labb2;

public class Product {
    private String name;
    private int price;
    private Category category;
    private Brand brand;
    private int BarCode;

    public Product(String name, int price, Category category, Brand brand, int barCode) {
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getBarCode() {
        return BarCode;
    }

    public void setBarCode(int barCode) {
        BarCode = barCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", brand=" + brand +
                ", BarCode=" + BarCode +
                '}';
    }
}
