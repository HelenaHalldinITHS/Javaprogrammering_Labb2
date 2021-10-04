package se.iths.helena.labb2;

import java.util.Locale;
import java.util.Scanner;

public class ShoppingCart {
    private static final Scanner scanner = new Scanner(System.in);
    private Inventory shoppingCart;
    private Inventory storeInventory;
    private Discountable price = noDiscount.getInstance();

    public ShoppingCart(Inventory storeInventory){
        this.storeInventory = storeInventory;
        shoppingCart = new Inventory();
    }

    public void viewContent(){
        shoppingCart.showContent();
    }

    public void askUserIfProductShouldBeAdded(Product product){
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
        updateDiscountLevel();
    }

    private void updateDiscountLevel() {
        int totalPrice = shoppingCart.totalPriceOfInventory();
        if (totalPrice >= 500)
            price = thirdLevelDiscount.getInstance();
        else if (totalPrice >= 250)
            price = secondLevelDiscount.getInstance();
        else if (totalPrice >= 100)
            price = firstLevelDiscount.getInstance();
        else
            price = noDiscount.getInstance();
    }

    private int getAmountToAddToCart(Product product) {
        int amount;
        do{
            amount = Integer.parseInt(scanner.nextLine());
            if (amount > storeInventory.amountOfItemsInInventory(product))
                System.out.println("Det finns inte tillräckligt med varor i butiken, ange en lägre siffra");
            else
                break;
        } while (true);

        return amount;
    }

    private void moveProductFromStoreToCart(int amount, Product product) {
        shoppingCart.addItems(product,amount);
        storeInventory.removeItemsOfProduct(product, amount);
    }

    public void makePurchase(){
        System.out.println();
        System.out.println("GRATTIS! DU HAR GENOMFÖRT ETT KÖP");
        System.out.println("DITT KVITTO: ");
        shoppingCart.showContent();
        double discountedPrice = price.getDiscountedPrice(shoppingCart.totalPriceOfInventory());
        System.out.printf("Pris med rabatt: %.2f ", discountedPrice);
        System.out.println();
        clearCart();
    }

    private void clearCart() {
        shoppingCart.clearInventory();
    }

}

class noDiscount implements Discountable {
    private static final noDiscount instance = new noDiscount();
    private noDiscount(){}

    public static noDiscount getInstance(){
        return instance;
    }

    @Override
    public double getDiscountedPrice(int fullPrice) {
        return fullPrice;
    }


}

class firstLevelDiscount implements Discountable {
    private static final firstLevelDiscount instance = new firstLevelDiscount();
    private firstLevelDiscount(){}

    public static firstLevelDiscount getInstance(){
        return instance;
    }

    @Override
    public double getDiscountedPrice(int fullPrice) {
        return fullPrice * 0.9;
    }
}

class secondLevelDiscount implements Discountable {
    private static final secondLevelDiscount instance = new secondLevelDiscount();
    private secondLevelDiscount(){}

    public static secondLevelDiscount getInstance(){
        return instance;
    }

    @Override
    public double getDiscountedPrice(int fullPrice) {
        return fullPrice * 0.8;
    }
}

class thirdLevelDiscount implements Discountable {
    private static final thirdLevelDiscount instance = new thirdLevelDiscount();
    private thirdLevelDiscount(){}

    public static thirdLevelDiscount getInstance(){
        return instance;
    }

    @Override
    public double getDiscountedPrice(int fullPrice) {
        return fullPrice * 0.7;
    }
}