package se.iths.helena.labb2;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ShoppingCart {
    private Map<Product,Integer> shoppingCart;
    private final List<Discountable> discounts = new ArrayList<>();
    private final Products products;

    public ShoppingCart(Products products) {
        this.products = products;
        shoppingCart = new HashMap<>();
        discounts.addAll(List.of(new firstLevelDiscount(), new secondLevelDiscount(), new thirdLevelDiscount()));
    }

    public void viewContent() {
        System.out.println();
        shoppingCart.forEach((product, integer) -> System.out.println("Namn: " + product.name()
                + ", Antal: " + integer + ", Totalt pris: " + product.price() * integer));
        System.out.println("Belopp utan rabatt: " + totalPrice());
    }

    public double totalPrice() {
        AtomicInteger sum = new AtomicInteger();
        shoppingCart.forEach((product, integer) -> sum.addAndGet(product.price() * integer));
        return sum.doubleValue();
    }

    public void askUserIfProductShouldBeAdded(Product product) {
        System.out.println();
        System.out.println("VILL DU LÄGGA TILL DENNA VARA I DIN VARUKORG?");
        System.out.println("Skriv då JA");
        if (InputHandler.getInput().toLowerCase(Locale.ROOT).equals("ja"))
            addToCart(product);
    }

    private void addToCart(Product product) {
        System.out.println();
        System.out.println("Du har valt produkten: " + product.name());
        System.out.println("Hur många av denna produkt vill du addera till din varukorg? ");

        int amount = getAmountToAddToCart(product);
        moveProductFromStoreToCart(amount, product);
    }

    private int getAmountToAddToCart(Product product) {
        return InputHandler.getIntegerInput(0,product.amountInStore(), "Det finns inte tillräckligt med varor i butiken, ange en lägre siffra: ");
    }

    private void moveProductFromStoreToCart(int amount, Product product) {
        if (shoppingCart.containsKey(product))
            shoppingCart.put(product, shoppingCart.get(product)+amount);
        else
            shoppingCart.put(product, amount);

        product.decreaseInventory(amount);
    }

    public void makePurchase() {
        System.out.println();
        System.out.println("GRATTIS! DU HAR GENOMFÖRT ETT KÖP");
        printReceipt();
        saveUpdatedInventory();
        clearCart();
    }

    private void saveUpdatedInventory() {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveProducts(products);
    }

    private void printReceipt() {
        System.out.println("DITT KVITTO: ");
        viewContent();
        System.out.printf("Pris med rabatt: %.2f ", getDiscountedPrice());
        System.out.println();
    }

    private double getDiscountedPrice() {
        double price = totalPrice();
        for (Discountable discount : discounts)
            price = discount.getDiscountedPrice(price);
        return price;
    }

    private void clearCart() {
        shoppingCart = new HashMap<>();
    }

}

class firstLevelDiscount implements Discountable {
    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 100 && price < 250)
            return price * 0.9;
        return price;
    }
}

class secondLevelDiscount implements Discountable {
    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 250 && price < 500)
            return price * 0.8;
        return price;
    }
}

class thirdLevelDiscount implements Discountable {

    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 500)
            return price * 0.7;
        return price;
    }
}