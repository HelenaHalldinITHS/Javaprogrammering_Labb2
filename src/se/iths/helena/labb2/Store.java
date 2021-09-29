package se.iths.helena.labb2;


import java.util.Scanner;

public class Store {
    private static Categories categories = new Categories();
    private static Products products = new Products();
    private static Inventory inventory = new Inventory();
    private static Scanner scanner = new Scanner(System.in);


    public static void run(){
        readFromFile();
        printWelcomeMessage();

        Category categoryOfChoice = makeUserChooseASubCategory(Category.highestCategory);

        System.out.println("Välj ett av följande: ");
        System.out.println("1. Visa alla varor inom denna kategori.");
        System.out.println("2. Visa denna kategorins sub kategorier.");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1 -> printAllProductsInCategory(categoryOfChoice);
            case 2 -> makeUserChooseASubCategory(categoryOfChoice);
        }
    }

    private static void printAllProductsInCategory(Category categoryOfChoice) {

//        //Skriv ut produkter för vald kategoris sub kategorier
//        List<Category> cList = categories.getSubCategories(categoryOfChoice);
//        cList.add(categoryOfChoice);
//
//        List<Product> pList;
//
//        for (Category category : cList) {
//            pList = products.findProductsByCategory(category);
//            pList.forEach(product -> System.out.println(product.name()));
//        }
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
        System.out.println("Välkommen!");
        System.out.println("Hitta varor genom att navigera bland kategorier");
    }


}



