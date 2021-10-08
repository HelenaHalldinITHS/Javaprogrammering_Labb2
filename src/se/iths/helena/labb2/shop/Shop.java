package se.iths.helena.labb2.shop;

import se.iths.helena.labb2.*;

import java.util.List;

public class Shop {
    private static Categories categories;
    private static Products products;
    private static ShoppingCart cart;

    private static final int RETURN = 0;
    private static final List<Integer> VALID_CHOICES = List.of(RETURN, 1, 2, 3, 4, 5);

    public static void initialise(Categories categoryFromController, Products productsFromController) {
        categories = categoryFromController;
        products = productsFromController;
        cart = new ShoppingCart(products);
    }

    public static void run() {
        while (true) {
            printMainMenu();
            int mainMenuChoice = getMainMenuChoice();
            if (mainMenuChoice == RETURN)
                break;
            runMainMenuChoice(mainMenuChoice);
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Välkommen, välj något av följande: ");
        System.out.println("1. Hitta varor genom att navigera bland kategorier");
        System.out.println("2. Hitta varor genom att filtrera");
        System.out.println("3. Hitta varor genom att söka");
        System.out.println("4. Visa innehåll i kundvagn");
        System.out.println("5. Genomför ett köp");
        System.out.println("0. Gå bakåt");

    }

    private static void runMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> new NavigateThroughCategories(products, cart, categories);
            case 2 -> new FilterProducts(products, cart, categories);
            case 3 -> new SearchProducts(products, cart);
            case 4 -> cart.viewContent();
            case 5 -> cart.makePurchase();
        }
    }

    private static int getMainMenuChoice() {
        return InputHandler.getIntegerInput(VALID_CHOICES);
    }


}

