package se.iths.helena.labb2;

import java.util.List;
import java.util.Locale;

//Class for modifying the Categories available in the store
public class CategoriesModifier {
    private static Categories categories;
    private static final int RETURN = 0;
    private static final List<Integer> VALID_CHOICES = List.of(RETURN,1,2,3);

    public static void initialise(Categories categoryFromController){
        categories = categoryFromController;
    }

    public static void run(){
        while(true) {
            printMenu();
            int input = getInput();
            if (input == RETURN)
                break;
            runChoice(input);
        }

    }

    public static void printAllCategories(){
        System.out.println();
        categories.forEach(category -> System.out.println(category.getName()));
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
        String name = InputHandler.getInput("Ange den kategori vars sub kategorier du vill se: ");

        System.out.println();
        categories.get(name).ifPresent(category -> categories.getSubCategories(category)
                .forEach( category1 -> System.out.println(category1.getName())));
    }

    private static void addCategory() {
        String name = getNameOfNewCategory();
        printQuestionAboutSubCategory();
        processInput(name, InputHandler.getInput());
        save();
    }

    private static void save() {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveCategories(categories);
    }

    private static void processInput(String name, String input) {
        while (true) {
            if (input.toLowerCase(Locale.ROOT).equals("nej")) {
                categories.addCategory(new Category(name));
                return;
            }
            if (categories.contains(new Category(input))) {
                categories.get(input).ifPresent(category -> categories.addCategory(new Category(name, category)));
                return;
            }
            System.out.println("Försök igen, jag förstå ej: ");
        }
    }

    private static void printQuestionAboutSubCategory() {
        System.out.println();
        System.out.println("Om kategorin ska vara en sub kategori," +
                " ange då namnet på den önskade super kategorin," +
                " annars skriv \"nej\": ");
    }

    private static String getNameOfNewCategory() {
        System.out.println();
        return InputHandler.getInput("Ange namnet på önskad kategori: ");
    }

    private static int getInput () {
        return InputHandler.getInput(VALID_CHOICES);
    }

    private static void printMenu(){
        System.out.println();
        System.out.println("Vad vill du göra?: ");
        System.out.println("1. Lägg till kategorier");
        System.out.println("2. Se en överblick över alla kategorier");
        System.out.println("3. Se kategoris sub kategorier");
        System.out.println("0. Gå bakåt");
    }



}
