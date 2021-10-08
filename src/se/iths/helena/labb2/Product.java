package se.iths.helena.labb2;

import java.util.Objects;

public final class Product {
    private final String name;
    private final int price;
    private final Category category;
    private final String brand;
    private final long id;
    private int amountInStore;

    public Product(String name, int price, Category category, String brand, long id, int amountInStore) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.id = id;
        this.amountInStore = amountInStore;
    }

    public void showInfo() {
        System.out.println();
        System.out.println("Namn: " + this.name());
        System.out.println("Id: " + this.id());
        System.out.println("Kategori: " + this.category().getName());
        System.out.println("Pris: " + this.price());
        System.out.println("Märke: " + this.brand());
        System.out.println("Antal i butik: " + this.amountInStore());
    }

    public void decreaseInventory(int amount) {
        this.amountInStore = this.amountInStore - amount;
    }

    public String name() {
        return name;
    }

    public int price() {
        return price;
    }

    public Category category() {
        return category;
    }

    public String brand() {
        return brand;
    }

    public long id() {
        return id;
    }

    public int amountInStore() {
        return amountInStore;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Product) obj;
        return Objects.equals(this.name, that.name) &&
                this.price == that.price &&
                Objects.equals(this.category, that.category) &&
                Objects.equals(this.brand, that.brand) &&
                this.id == that.id &&
                this.amountInStore == that.amountInStore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, brand, id, amountInStore);
    }

    @Override
    public String toString() {
        return "Product[" +
                "name=" + name + ", " +
                "price=" + price + ", " +
                "category=" + category + ", " +
                "brand=" + brand + ", " +
                "id=" + id + ", " +
                "amountInStore=" + amountInStore + ']';
    }
}
