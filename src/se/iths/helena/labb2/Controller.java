package se.iths.helena.labb2;

import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static Categories categories = new Categories();
    private static Products products = new Products();


    public static void main(String[] args) {
        readFromCsvFile();
        while (true) {
            printMenu();
            int input = getInput();
            if (input == 0)
                break;
            runChoice(input);
        }
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
        System.out.println("1. Hantera kategorier");
        System.out.println("2. Hantera produkter");
        System.out.println("3. Handla");
        System.out.println("0. Avsluta");
    }


    private static void readFromCsvFile(){
        CsvReader csvR = new CsvReader();

        //Categories:
        categories.initialiseCategories(csvR.readCategories());

        //Products:
        products.initialiseProducts(csvR.readProducts(categories));

        CategoriesModifier.initialise(categories);
        ProductsModifier.initialise(categories, products);
        Shop.initialise(categories, products);
    }
}
