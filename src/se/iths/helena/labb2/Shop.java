package se.iths.helena.labb2;

import java.util.List;
import java.util.Scanner;

public class Shop {
    private static Categories categories;
    private static Products products;
    private static final Scanner scanner = new Scanner(System.in);
    private static ShoppingCart cart;

    public static void initialise(Categories categoryFromController, Products productsFromController){
        categories = categoryFromController;
        products = productsFromController;
        cart = new ShoppingCart(products);
    }

    public static void run() {
        while (true) {
            printMainMenu();
            int mainMenuChoice = getMainMenuChoice();
            if (mainMenuChoice == 0)
                break;

            runMainMenuChoice(mainMenuChoice);
            if (mainMenuChoice == 4 || mainMenuChoice ==  5)
                continue;

            printContinueMenu();
            if (Integer.parseInt(scanner.nextLine()) == 0)
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
        product.showInfo();
        cart.askUserIfProductShouldBeAdded(product);
    }

    private static void printContinueMenu() {
        System.out.println();
        System.out.println("Vad vill du göra nu?");
        System.out.println("1. Visa information om en produkt");
        System.out.println("0. Gå bakåt");
    }

    private static void runMainMenuChoice(int choice) {
        switch (choice) {
            case 1 -> findProductsUsingCategories();
            case 2 -> findProductsUsingFilter();
            case 3 -> findProductsThroughSearching();
            case 4 -> cart.viewContent();
            case 5 -> cart.makePurchase();
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

    private static void findProductsUsingCategories() {
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
        System.out.println("4. Visa innehåll i kundvagn");
        System.out.println("5. Genomför ett köp");
        System.out.println("0. Gå bakåt");
    }


}
