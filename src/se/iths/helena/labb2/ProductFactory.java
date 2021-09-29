package se.iths.helena.labb2;

import java.util.Optional;
import java.util.Scanner;

public class ProductFactory {
    private static Categories categories = new Categories();
    private static Products products = new Products();
    private static Scanner scanner = new Scanner(System.in);


    public static void run() {
        readFromFile();
        while (true) {
            printMenu();
            int input = getIntInput();
            if (input == 3)
                break;
            runChoice(input);
        }

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

    private static void printMenu() {
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till nya produkter");
        System.out.println("2. Se en överblick över alla produkter");
        System.out.println("4. Avsluta");
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
        long id = Long.parseLong(scanner.nextLine());
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
        while (true){
            optionalCategory = categories.get(scanner.nextLine());

            if (optionalCategory.isPresent()) {
                category = optionalCategory.get();
                break;
            }

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
        products.forEach(System.out::println);
    }

}
