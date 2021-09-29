package se.iths.helena.labb2;

import java.util.Locale;
import java.util.Optional;
import java.util.Scanner;

//Class for modifying the Categories available in the store
public class CategoryFactory {
    private static Categories categories = new Categories();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        run();
    }

    public static void run(){
        readFromFile();
        while(true) {
            printMenu();
            int input = getIntInput();
            if (input == 3)
                break;
            runChoice(input);
        }

    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> addCategory();
            case 2 -> printAllCategories();
        }
    }

    private static void addCategory() {
        String name = getNameOfNewCategory();
        printQuestionAboutSubCategory();

        while (!inputCanBeProcessed(name, scanner.nextLine())) {
            System.out.println("Försök igen, jag förstå ej: ");
        }
    }

    private static boolean inputCanBeProcessed(String name, String input) {
        if (input.toLowerCase(Locale.ROOT).equals("nej"))
            return true;

        if (categories.contains(new Category(input))) {
            categories.get(input).ifPresent(category -> categories.addCategory(new Category(name, category)));
            return true;
        }
        return false;
    }

    private static void printQuestionAboutSubCategory() {
        System.out.println("Om kategorin ska vara en sub kategori," +
                " ange då namnet på den önskade super kategorin," +
                " annars skriv \"nej\": ");
    }

    private static String getNameOfNewCategory() {
        System.out.println("Ange namnet på önskad kategori: ");
        return scanner.nextLine();
    }

    private static int getIntInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void readFromFile() {
        //läs in sparade kategorier och lägg i "categories"
        categories.addCategory(new Category("Vin"));
        categories.addCategory(new Category("Öl"));
        categories.addCategory(new Category("Cider"));
        categories.addCategory(new Category("Sprit"));
        categories.addCategory(new Category("Rött vin", "Vin"));
    }

    private static void printMenu(){
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till kategorier");
        System.out.println("2. Se en överblick över alla kategorier");
        System.out.println("3. Avsluta");
    }

    private static void printAllCategories(){
        categories.forEach(category -> System.out.println(category.getName()));
    }

}
