package se.iths.helena.labb2;

import java.util.List;
import java.util.Scanner;

public class Shop {
    private static Categories categories = new Categories();
    private static Products products = new Products();
    private static Inventory inventory = new Inventory();
    private static final Scanner scanner = new Scanner(System.in);

    public static void run() {
        readFromFile();

        while (true) {
            printMainMenu();
            int mainMenuChoice = getMainMenuChoice();
            if (mainMenuChoice == 0)
                break;
            runMainMenuChoice(mainMenuChoice);

            printContinueMenu();
            int continueChoice = Integer.parseInt(scanner.nextLine());
            if (continueChoice == 0)
                continue;

            makeUserChooseAProduct();
        }


    }

    private static void makeUserChooseAProduct() {
        System.out.println();
        System.out.println("Välj en produkt genom att skriva dess namn: ");
        String input = scanner.nextLine();
        System.out.println();
        products.getProductByName(input).ifPresentOrElse(Shop::showInfoOfProduct, () -> System.out.println("Namnet du skrev matchar ingen produkt"));
    }

    private static void showInfoOfProduct(Product product) {
        System.out.println("Namn: " + product.name());
        System.out.println("Id: " + product.id());
        System.out.println("Kategori: " + product.category().getName());
        System.out.println("Pris: " + product.price());
        System.out.println("Märke: " + product.brand());
        System.out.println("Antal i butiken: " + inventory.amountOfItemsInStore(product));
        System.out.println();
    }

    private static void printContinueMenu() {
        System.out.println();
        System.out.println("Vad vill du göra nu?");
        System.out.println("1. Visa information om en produkt");
        System.out.println("0. Gå bakåt");
    }

    private static void runMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> findProductsUsingCategories22();
            case 2 -> findProductsUsingFilter();
            case 3 -> findProductsThroughSearching();
        }
    }

    private static void findProductsThroughSearching() {
        System.out.println();
        System.out.println("Sök genom att skriva in hela, eller delar av produktens namn");
        String searchWord = scanner.nextLine();
        List<Product> productsFromSearch = products.getProductsByPartOfName(searchWord);

        System.out.println();
        if (productsFromSearch.isEmpty())
            System.out.println("Din sökning gav inga resultat");
        else {
            System.out.println("Följande produkter hittades: ");
            productsFromSearch.forEach(product -> System.out.println(product.name()));
        }
    }

    private static void findProductsUsingFilter() {
        printFilterMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        runFilterMenuChoice(choice);
    }

    private static void runFilterMenuChoice(int choice) {
        switch (choice) {
            case 1 -> filterByPrice();
            case 2 -> filterByBrand();
            case 3 -> filterByCategory();
        }
    }

    private static void filterByCategory() {
        System.out.println();
        System.out.println("Ange den kategori du vill filtrera på");
        String categoryOfChoice = scanner.nextLine();
        categories.get(categoryOfChoice).ifPresentOrElse(Shop::printAllProductsInCategory, () -> System.out.println("Kategorin du skrev finns ej."));
    }

    private static void findProductsUsingCategories22() {
        Category categoryOfChoice = Category.highestCategory;
        int choice = 2;

        while (true) {
            categoryOfChoice = runCategoryChoice(categoryOfChoice, choice);
            if (choice == 1) break;
            choice = getCategoryNavigationChoice();
            if (choice == 2 && areNoSubCategories(categoryOfChoice)) break;
        }
    }

    private static boolean areNoSubCategories(Category categoryOfChoice) {
        if (categories.getSubCategories(categoryOfChoice).isEmpty()) {
            System.out.println();
            System.out.println("Denna kategori har inga sub kategorier, följande varor finns i kategorin: ");
            printAllProductsInCategory(categoryOfChoice);
            return true;
        }
        return false;
    }

    private static Category runCategoryChoice(Category categoryOfChoice, int choice) {
        switch (choice) {
            case 2 -> categoryOfChoice = makeUserChooseASubCategory(categoryOfChoice);
            case 1 -> printAllProductsInCategory(categoryOfChoice);
        }
        return categoryOfChoice;
    }

    private static int getCategoryNavigationChoice() {
        System.out.println();
        System.out.println("Välj ett av följande: ");
        System.out.println("1. Visa alla varor inom denna kategori.");
        System.out.println("2. Visa denna kategorins sub kategorier.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printAllProductsInCategory(Category categoryOfChoice) {
        System.out.println();
        categories.getSubCategoriesInclusiveThisCategory(categoryOfChoice)
                .forEach(category -> products.findProductsByCategory(category)
                        .forEach(product -> System.out.println(product.name())));
    }

    private static Category makeUserChooseASubCategory(Category chosenCategory) {
        String choice;
        do {
            System.out.println();
            System.out.println("Välj en kategorierna genom att skriva dess namn: ");
            categories.getOneLevelOfSubCategories(chosenCategory).forEach(category -> System.out.println(category.getName()));
            choice = scanner.nextLine();
        } while (categories.get(choice).isEmpty());

        return categories.getFromString(choice);
    }

    private static void filterByBrand() {
        System.out.println();
        System.out.println("Ange det märke du vill filtrera på: ");
        String brand = scanner.nextLine();

        printFilterResult(products.findProductsByBrand(brand));

    }

    private static void filterByPrice() {
        System.out.println();
        System.out.println("Ange önskad lägsta prisgräns: ");
        long min = Long.parseLong(scanner.nextLine());

        System.out.println();
        System.out.println("Ange önskad högsta prisgräns: ");
        long max = Long.parseLong(scanner.nextLine());

        printFilterResult(products.findProductsInPriceRange(min, max));

    }

    private static void printFilterResult(List<Product> filterResult) {
        System.out.println();
        if (filterResult.isEmpty())
            System.out.println("Din filtrering gav inga resultat");
        else
            filterResult.forEach(product -> System.out.println(product.name()));
    }

    private static void printFilterMenu() {
        System.out.println();
        System.out.println("Hitta produkter genom att filtrera. Välj ett av följande alternativ: ");
        System.out.println("1. Filtrera på pris");
        System.out.println("2. Filtrera på varumärke");
        System.out.println("3. Filtrera på kategori");
    }

    private static int getMainMenuChoice() {
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("Välkommen, välj något av följande: ");
        System.out.println("1. Hitta varor genom att navigera bland kategorier");
        System.out.println("2. Hitta varor genom att filtrera");
        System.out.println("3. Hitta varor genom att söka");
        System.out.println("0. Gå bakåt");
    }

    private static void readFromFile() {
        //läs in sparade kategorier och lägg i "categories"
        categories.addCategory(new Category("Vin"));
        categories.addCategory(new Category("Öl"));
        categories.addCategory(new Category("Cider"));
        categories.addCategory(new Category("Sprit"));
        categories.addCategory(new Category("Rött vin", "Vin"));

        //läs in sparade produkter
        products.addProduct(new Product("Somersby hallon", 19, new Category("Cider"), "Somersby", 1));
        products.addProduct(new Product("Somersby jordgubb", 19, new Category("Cider"), "Somersby", 2));
        products.addProduct(new Product("Fat bastard", 89, new Category("Rött vin", "Vin"), "someBrand", 3));
        products.addProduct(new Product("Some red wine", 99, new Category("Rött vin", "Vin"), "someBrand", 4));

        //läs in inventory
        inventory.addItems(new Product("Somersby hallon", 19, new Category("Cider"), "Somersby", 1), 3);
        inventory.addItems(new Product("Somersby jordgubb", 19, new Category("Cider"), "Somersby", 2), 4);
        inventory.addItems(new Product("Fat bastard", 89, new Category("Rött vin", "Vin"), "someBrand", 3), 1);
        inventory.addItems(new Product("Some red wine", 99, new Category("Rött vin", "Vin"), "someBrand", 4), 10);

    }


}
