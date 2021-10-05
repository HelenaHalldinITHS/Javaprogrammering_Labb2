package se.iths.helena.labb2;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class ShoppingCart {
    private static final Scanner scanner = new Scanner(System.in);
    private Inventory shoppingCart;
    private Inventory storeInventory;
    private final List<Discountable> discounts = new ArrayList<>();

    public ShoppingCart(Inventory storeInventory) {
        this.storeInventory = storeInventory;
        shoppingCart = new Inventory();

        discounts.addAll(List.of(new firstLevelDiscount(), new secondLevelDiscount(), new thirdLevelDiscount()));
    }

    public void viewContent() {
        System.out.println();
        shoppingCart.showContent();
    }

    public void askUserIfProductShouldBeAdded(Product product) {
        System.out.println();
        System.out.println("VILL DU LÄGGA TILL DENNA VARA I DIN VARUKORG?");
        System.out.println("Skriv då JA");
        if (scanner.nextLine().toLowerCase(Locale.ROOT).equals("ja"))
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
        int amount;
        do {
            amount = Integer.parseInt(scanner.nextLine());
            if (amount > storeInventory.amountOfItemsInInventory(product))
                System.out.println("Det finns inte tillräckligt med varor i butiken, ange en lägre siffra");
            else
                break;
        } while (true);

        return amount;
    }

    private void moveProductFromStoreToCart(int amount, Product product) {
        shoppingCart.addItems(product, amount);
        storeInventory.removeItemsOfProduct(product, amount);
    }

    public void makePurchase() {
        System.out.println();
        System.out.println("GRATTIS! DU HAR GENOMFÖRT ETT KÖP");
        printReceipt();
        updateInventory();
        clearCart();

    }

    private void updateInventory() {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveInventory(storeInventory);
    }

    private void printReceipt() {
        System.out.println("DITT KVITTO: ");
        shoppingCart.showContent();
        System.out.printf("Pris med rabatt: %.2f ", getDiscountedPrice());
        System.out.println();
    }

    private double getDiscountedPrice() {
        double price = shoppingCart.totalPriceOfInventory();
        for (Discountable discount : discounts)
            price = discount.getDiscountedPrice(price);
        return price;
    }

    private void clearCart() {
        shoppingCart.clearInventory();
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