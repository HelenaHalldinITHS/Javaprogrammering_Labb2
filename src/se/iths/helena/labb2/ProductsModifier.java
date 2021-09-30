package se.iths.helena.labb2;

import java.util.Optional;
import java.util.Scanner;

public class ProductsModifier {
    private static Categories categories;
    private static Products products;
    private static final Scanner scanner = new Scanner(System.in);

    public static void initialise(Categories categoryFromController, Products productsFromController){
        categories = categoryFromController;
        products = productsFromController;
    }

    public static void run() {
        while (true) {
            printMenu();
            int input = getIntInput();
            if (input == 0)
                break;
            runChoice(input);
        }

    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till nya produkter");
        System.out.println("2. Se en överblick över alla produkter");
        System.out.println("0. Gå bakåt");
    }

    private static int getIntInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> addProduct();
            case 2 -> printAllProducts();
        }
    }

    private static void addProduct() {
        String name = getNameFromUser();
        int price = getPriceFromUser();
        Category category = getCategoryFromUser();
        String brand = getBrandFromUser();
        long id = getIdFromUser();

        products.addProduct(new Product(name, price, category, brand, id));
        saveToFile();
    }

    private static void saveToFile() {
        //Spara ändringar
    }

    private static long getIdFromUser() {
        System.out.println("Ange produktens id: ");
        long id;
        do {
            id = Long.parseLong(scanner.nextLine());
            if (products.findProductById(id).isPresent())
                System.out.println("Detta id är upptaget, försök igen: ");
            else
                break;
        } while (true);
        return id;
    }

    private static String getBrandFromUser() {
        System.out.println("Ange märke: ");
        return scanner.nextLine();
    }

    private static Category getCategoryFromUser() {
        System.out.println("Ange produktens Kategori: ");
        Category category;
        Optional<Category> optionalCategory;

        while (true) {
            optionalCategory = categories.get(scanner.nextLine());

            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
                break;
            } else
                System.out.println("Kategorin du angett finns ej, försök igen: ");
        }
        return category;
    }

    private static int getPriceFromUser() {
        System.out.println("Ange produktens pris: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private static String getNameFromUser() {
        System.out.println("Ange produktens namn: ");
        return scanner.nextLine();
    }

    private static void printAllProducts() {
        products.forEach(Product::showInfo);
    }

}
