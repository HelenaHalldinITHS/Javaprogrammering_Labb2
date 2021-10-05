package se.iths.helena.labb2;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

//Class for modifying the Categories available in the store
public class CategoriesModifier {
    private static final Scanner scanner = new Scanner(System.in);
    private static Categories categories;

    public static void initialise(Categories categoryFromController){
        categories = categoryFromController;
    }

    public static void run(){
        while(true) {
            printMenu();
            int input = getIntInput();
            if (input == 0)
                break;
            runChoice(input);
        }

    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> addCategory();
            case 2 -> printAllCategories();
            case 3 -> printSubCategories();
        }
    }

    private static void printSubCategories() {
        System.out.println();
        System.out.println("Ange den kategori vars sub kategorier du vill se: ");
        String name = scanner.nextLine();
        categories.get(name).ifPresent(category -> categories.getSubCategories(category)
                .forEach( category1 -> System.out.println(category1.getName())));
    }

    private static void addCategory() {
        String name = getNameOfNewCategory();
        printQuestionAboutSubCategory();

        while (!inputCanBeProcessed(name, scanner.nextLine())) {
            System.out.println("Försök igen, jag förstå ej: ");
        }

        save();
    }

    private static void save() {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveCategories(categories);
    }

    private static boolean inputCanBeProcessed(String name, String input) {
        if (input.toLowerCase(Locale.ROOT).equals("nej")) {
            categories.addCategory(new Category(name));
            return true;
        }
        if (categories.contains(new Category(input))) {
            categories.get(input).ifPresent(category -> categories.addCategory(new Category(name, category)));
            return true;
        }
        return false;
    }

    private static void printQuestionAboutSubCategory() {
        System.out.println();
        System.out.println("Om kategorin ska vara en sub kategori," +
                " ange då namnet på den önskade super kategorin," +
                " annars skriv \"nej\": ");
    }

    private static String getNameOfNewCategory() {
        System.out.println();
        System.out.println("Ange namnet på önskad kategori: ");
        return scanner.nextLine();
    }

    private static int getIntInput() {
        return Integer.parseInt(scanner.nextLine());
    }


    private static void printMenu(){
        System.out.println();
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till kategorier");
        System.out.println("2. Se en överblick över alla kategorier");
        System.out.println("3. Se kategoris sub kategorier");
        System.out.println("0. Gå bakåt");
    }

    public static void printAllCategories(){
        System.out.println();
        categories.forEach(category -> System.out.println(category.getName()));
    }

}
