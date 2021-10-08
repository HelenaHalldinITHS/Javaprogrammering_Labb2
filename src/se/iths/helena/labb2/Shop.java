package se.iths.helena.labb2;

import java.util.List;

public class Shop {
    private static Categories categories;
    private static Products products;
    private static ShoppingCart cart;

    private static final int RETURN = 0;
    private static final List<Integer> VALID_CHOICES = List.of(RETURN, 1, 2, 3, 4, 5);

    public static void initialise(Categories categoryFromController, Products productsFromController) {
        categories = categoryFromController;
        products = productsFromController;
        cart = new ShoppingCart(products);
    }

    public static void run() {
        while (true) {
            printMainMenu();
            int mainMenuChoice = getMainMenuChoice();
            if (mainMenuChoice == RETURN)
                break;
            runMainMenuChoice(mainMenuChoice);
        }
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
        String searchWord = InputHandler.getInput("Sök genom att skriva in hela, eller delar av produktens namn");
        List<Product> productsFromSearch = products.getProductsByPartOfName(searchWord);

        System.out.println();
        if (productsFromSearch.isEmpty())
            System.out.println("Din sökning gav inga resultat");
        else {
            System.out.println("Följande produkter hittades: ");
            productsFromSearch.forEach(product -> System.out.println(product.name()));
        }
        runShopProductMenu();
    }

    private static void runShopProductMenu() {
        printShopProductMenu();
        if (InputHandler.getIntegerInput() == RETURN)
            return;
        makeUserChooseAProduct();
    }

    private static void printShopProductMenu() {
        System.out.println();
        System.out.println("Vad vill du göra nu?");
        System.out.println("1. Visa information om en produkt");
        System.out.println("0. Gå bakåt");
    }

    private static void findProductsUsingFilter() {
        System.out.println();
        System.out.println("Hitta produkter genom att filtrera. Välj ett av följande alternativ: ");
        System.out.println("1. Filtrera på pris");
        System.out.println("2. Filtrera på varumärke");
        System.out.println("3. Filtrera på kategori");

        runFilterMenuChoice(InputHandler.getIntegerInput(1, 3));
        runShopProductMenu();
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
        String categoryOfChoice = InputHandler.getInput("Ange den kategori du vill filtrera på");
        categories.get(categoryOfChoice)
                .ifPresentOrElse(Shop::printAllProductsInCategory, () -> System.out.println("Kategorin du skrev finns ej."));
    }

    private static void findProductsUsingCategories() {
        Category categoryOfChoice = Category.highestCategory;
        int choice = 2;

        while (true) {
            categoryOfChoice = runCategoryChoice(categoryOfChoice, choice);
            if (choice == 1)
                break;
            choice = getCategoryNavigationChoice();
            if (choice == 2 && categories.getSubCategories(categoryOfChoice).isEmpty()) {
                System.out.println();
                System.out.println("Denna kategori har inga sub kategorier.");
                choice = 1;
            }
        }
        runShopProductMenu();
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
        return InputHandler.getIntegerInput();
    }

    private static void printAllProductsInCategory(Category categoryOfChoice) {
        System.out.println();
        System.out.println("Följande produkter finns i angiven kategori: ");
        categories.getSubCategoriesInclusiveThisCategory(categoryOfChoice)
                .forEach(category -> products.findProductsByCategory(category)
                        .forEach(product -> System.out.println(product.name())));
    }

    private static Category makeUserChooseASubCategory(Category chosenCategory) {
        System.out.println();
        System.out.println("Välj en kategorierna genom att skriva dess namn: ");
        categories.getOneLevelOfSubCategories(chosenCategory)
                .forEach(category -> System.out.println(category.getName()));

        String choice;
        while (true) {
            choice = InputHandler.getInput();
            if (categories.get(choice).isPresent())
                return categories.getFromString(choice);
            System.out.println("Kategorin du angav finns ej, försök igen");
        }
    }

    private static void filterByBrand() {
        System.out.println();
        String brand = InputHandler.getInput("Ange det märke du vill filtrera på:");
        printFilterResult(products.findProductsByBrand(brand));
    }

    private static void filterByPrice() {
        System.out.println();
        long min = InputHandler.getLongInput("Ange önskad lägsta prisgräns: ");
        System.out.println();
        long max = InputHandler.getLongInput("Ange önskad högsta prisgräns: ");
        printFilterResult(products.findProductsInPriceRange(min, max));
    }

    private static void printFilterResult(List<Product> filterResult) {
        System.out.println();
        if (filterResult.isEmpty())
            System.out.println("Din filtrering gav inga resultat");
        else
            filterResult.stream()
                    .map(Product::name)
                    .forEach(System.out::println);
    }

    private static int getMainMenuChoice() {
        return InputHandler.getIntegerInput(VALID_CHOICES);
    }

    private static void makeUserChooseAProduct() {
        System.out.println();
        String input = InputHandler.getInput("Välj en produkt genom att skriva dess namn: ");
        System.out.println();
        products.getProductByName(input)
                .ifPresentOrElse(Shop::showInfoOfProduct, () -> System.out.println("Namnet du skrev matchar ingen produkt"));
    }

    private static void showInfoOfProduct(Product product) {
        product.showInfo();
        cart.askUserIfProductShouldBeAdded(product);
    }

}
