package se.iths.helena.labb2;

import java.util.List;

public class ProductsModifier {
    private static Categories categories;
    private static Products products;
    private static final int RETURN = 0;
    private static final List<Integer> VALID_CHOICES = List.of(RETURN, 1, 2);

    public static void initialise(Categories categoryFromController, Products productsFromController) {
        categories = categoryFromController;
        products = productsFromController;
    }

    public static void run() {
        while (true) {
            printMenu();
            int input = getInput();
            if (input == RETURN)
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

    private static int getInput() {
        return InputHandler.getIntegerInput(VALID_CHOICES);
    }

    private static void runChoice(int input) {
        switch (input) {
            case 1 -> addProduct();
            case 2 -> printAllProducts();
        }
    }

    private static void addProduct() {
        String name = InputHandler.getInput("Ange produktens namn: ");
        int price = InputHandler.getIntegerInput("Ange produktens pris: ");
        Category category = getCategoryFromUser();
        String brand = InputHandler.getInput("Ange märke: ");
        long id = getIdFromUser();
        int amount = InputHandler.getIntegerInput("Ange antal i butiken: ");

        products.addProduct(new Product(name, price, category, brand, id, amount));
        save();
    }

    public static void save() {
        CsvWriter csvWriter = new CsvWriter();
        csvWriter.saveProducts(products);
    }

    private static long getIdFromUser() {
        long id;
        System.out.println("Ange produktens id: ");
        while (true) {
            try {
                id = Long.parseLong(InputHandler.getInput());
                if (products.findProductById(id).isPresent())
                    throw new IllegalArgumentException();
                return id;
            } catch (IllegalArgumentException e) {
                System.out.println("Det id du anger är inte giltigt, försök igen:");
                printTakenIds();
            }
        }
    }

    private static void printTakenIds() {
        System.out.print("Följande id är upptagna: ");
        products.getIds().forEach(l -> System.out.print(l + " "));
        System.out.println();
    }

    private static Category getCategoryFromUser() {
        System.out.println("Ange produktens Kategori: ");
        while (true) {
            try {
                return categories.get(InputHandler.getInput()).orElseThrow();
            } catch (Exception e) {
                System.out.println("Kategorin du angett finns ej, försök igen: ");
            }
        }
    }

    private static void printAllProducts() {
        products.forEach(Product::showInfo);
    }

}
