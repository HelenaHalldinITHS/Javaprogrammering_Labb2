package se.iths.helena.labb2;

import java.util.List;
import java.util.Scanner;

public class Controller {
    static Scanner scanner = new Scanner(System.in);
    private static final int END_APPLICATION = 0;
    private static final Categories categories = new Categories();
    private static final Products products = new Products();
    private static final List<Integer> validChoices = List.of(END_APPLICATION,1,2,3);

    public static void main(String[] args) {
        readFromCsvFile();
        initialise();

        while (true) {
            printMenu();
            int input = getInput();
            if (input == END_APPLICATION)
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
        int inputAsInt;
        while(true) {
            try {
                inputAsInt = Integer.parseInt(scanner.nextLine());
                if (!validChoices.contains(inputAsInt))
                    throw new IllegalArgumentException();
                return inputAsInt;
            } catch (IllegalArgumentException e) {
                System.out.println("Val ej giltigt, försök igen genom att skriva den siffra som motsvarar ditt val.");
            }
        }
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Hantera kategorier");
        System.out.println("2. Hantera produkter");
        System.out.println("3. Handla");
        System.out.println(END_APPLICATION + ". Avsluta");
    }

    private static void readFromCsvFile(){
        CsvReader csvR = new CsvReader();
        categories.initialiseCategories(csvR.readCategories());
        products.initialiseProducts(csvR.readProducts(categories));
    }

    private static void initialise() {
        CategoriesModifier.initialise(categories);
        ProductsModifier.initialise(categories, products);
        Shop.initialise(categories, products);
    }
}
