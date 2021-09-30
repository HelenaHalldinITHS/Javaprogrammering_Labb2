package se.iths.helena.labb2;

import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static Categories categories = new Categories();
    private static Products products = new Products();
    private static Inventory inventory = new Inventory();


    public static void main(String[] args) {
        readFromFile();
        while (true) {
            printMenu();
            int input = getInput();
            if (input == 0)
                break;
            runChoice(input);
        }
        saveToFile();
    }

    private static void saveToFile() {
        //implement later
    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> CategoriesModifier.run();
            case 2 -> ProductsModifier.run();
            case 3 -> Shop.run();
        }
    }

    private static int getInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till kategorier");
        System.out.println("2. Lägg till/ändra produkter");
        System.out.println("3. Handla");
        System.out.println("0. Avsluta");
    }


    private static void readFromFile() {
        //Kategorier:
        Category wine = new Category("Vin");
        Category cider = new Category("Cider");
        Category beer = new Category("Öl");
        Category boose = new Category("Sprit");
        Category redWine = new Category("Rött vin", "Vin");

        //läs in sparade kategorier och lägg i "categories"
        categories.addCategory(wine);
        categories.addCategory(beer);
        categories.addCategory(cider);
        categories.addCategory(boose);
        categories.addCategory(redWine);

        //läs in sparade produkter
        Product sbH = new Product("Somersby hallon", 19, cider, "Somersby", 1);
        Product sbJ = new Product("Somersby jordgubb", 19, cider, "Somersby", 2);
        Product FB = new Product("Fat bastard", 89, redWine, "someBrand", 3);
        Product SRW = new Product("Some red wine", 99, redWine, "someBrand", 4);

        products.addProduct(sbH);
        products.addProduct(sbJ);
        products.addProduct(FB);
        products.addProduct(SRW);

        //läs in inventory
        inventory.addItems(sbH, 3);
        inventory.addItems(sbJ, 4);
        inventory.addItems(FB, 1);
        inventory.addItems(SRW, 10);

        //Inizalise classes
        CategoriesModifier.initialise(categories);
        ProductsModifier.initialise(categories, products);
        Shop.initialise(categories, products, inventory);

    }
}
