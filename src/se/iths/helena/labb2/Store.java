package se.iths.helena.labb2;


import java.util.Scanner;

public class Store {
    private static Categories categories;
    private static Products products;
    private static Inventory inventory;
    private static final Scanner scanner = new Scanner(System.in);


    public static void run(){
        readFromFile();
        printWelcomeMessage();

        switch (Integer.parseInt(scanner.nextLine())) {
            case 1 -> runNavigateWithCategories();
            case 2 -> runNavigateWithSearchOrFilter();
        }
    }

    private static void runNavigateWithSearchOrFilter() {

    }

    private static void runNavigateWithCategories() {
        Category categoryOfChoice = Category.highestCategory;
        int choice = 2;

        while (true){
            if (choice == 1){
                printAllProductsInCategory(categoryOfChoice);
                makeUserChooseAProduct();
                break;
            }
            if (choice == 2)
                categoryOfChoice = makeUserChooseASubCategory(categoryOfChoice);

            choice = getChoice();
        }
    }

    private static void makeUserChooseAProduct() {
        System.out.println("Välj en av följande produkter genom att skriva dess namn: ");
        String input = scanner.nextLine();
        System.out.println();
        products.getProductByName(input).ifPresent(Store::showInfoOfProduct);
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

    private static int getChoice() {
        System.out.println("Välj ett av följande: ");
        System.out.println("1. Visa alla varor inom denna kategori.");
        System.out.println("2. Visa denna kategorins sub kategorier.");
        return Integer.parseInt(scanner.nextLine());
    }

    private static void printAllProductsInCategory(Category categoryOfChoice) {
        categories.getSubCategoriesInclusiveThisCategory(categoryOfChoice)
                .forEach(category -> products.findProductsByCategory(category)
                        .forEach(product -> System.out.println(product.name())));
    }

    private static Category makeUserChooseASubCategory(Category chosenCategory) {
        String choice;
        do {
            System.out.println("Välj en kategorierna genom att skriva dess namn: ");
            printSubCategories(chosenCategory);
            choice = scanner.nextLine();
        } while (categories.get(choice).isEmpty());

        return categories.getFromString(choice);
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

    private static void printSubCategories(Category higherLevelCategory) {
        if (higherLevelCategory.equals(Category.highestCategory))
            categories.getOneLevelOfSubCategories(Category.highestCategory).forEach(category -> System.out.println(category.getName()));
        else
            categories.getOneLevelOfSubCategories(higherLevelCategory).forEach(category -> System.out.println(category.getName()));

    }

    private static void printWelcomeMessage() {
        System.out.println("Välkommen, välj något av följande: ");
        System.out.println("1. Hitta varor genom att navigera bland kategorier");
        System.out.println("2. Hitta varor genom att söka eller filtrera");
    }


}



