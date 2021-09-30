package se.iths.helena.labb2;

import java.util.Locale;
import java.util.Scanner;

public class ShoppingCartController {
    private static Scanner scanner = new Scanner(System.in);
    private Inventory shoppingCart;
    private Inventory storeInventory;

    public ShoppingCartController(Inventory storeInventory){
        this.storeInventory = storeInventory;
        shoppingCart = new Inventory();
    }

    public void viewContent(){
        shoppingCart.showContent();
    }

    public void shop(Product product){
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

    public void makesPurchases(){
        System.out.println();
        System.out.println("GRATTIS! DU HAR GENOMFÖRT ETT KÖP");
        System.out.println("DITT KVITTO: ");
        shoppingCart.showContent();
    }
}
