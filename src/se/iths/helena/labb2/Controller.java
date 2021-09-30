package se.iths.helena.labb2;

import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static Categories categories = new Categories();
    private static Products products = new Products();


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
            case 1 -> CategoriesModifier.run(categories);
            case 2 -> ProductsModifier.run(categories, products);
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
        //läs in sparade kategorier och lägg i "categories"
        categories.addCategory(new Category("Vin"));
        categories.addCategory(new Category("Öl"));
        categories.addCategory(new Category("Cider"));
        categories.addCategory(new Category("Sprit"));
        categories.addCategory(new Category("Rött vin", "Vin"));

        //läs in sparade produkter
        products.addProduct(new Product("Somersby hallon", 19, new Category("Vin"), "Somersby", 1));
        products.addProduct(new Product("Somersby jordgubb", 19, new Category("Vin"), "Somersby", 2));
        products.addProduct(new Product("Fat bastard", 89, new Category("Rött vin"), "someBrand", 3));
        products.addProduct(new Product("Some red wine", 99, new Category("Rött vin"), "someBrand", 4));
    }
}
