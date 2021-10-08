package se.iths.helena.labb2.shop;

import se.iths.helena.labb2.Categories;
import se.iths.helena.labb2.Category;
import se.iths.helena.labb2.InputHandler;
import se.iths.helena.labb2.Products;

public class NavigateThroughCategories extends FindProducts {
    Categories categories;

    public NavigateThroughCategories(Products products, ShoppingCart cart, Categories categories) {
        super(products, cart);
        this.categories = categories;

        findProductsUsingCategories();
    }

    public void findProductsUsingCategories() {
        Category categoryOfChoice = Category.highestCategory;
        int choice = 2;

        while (true) {
            categoryOfChoice = runCategoryChoice(categoryOfChoice, choice);
            if (choice == 1)
                break;
            choice = getCategoryNavigationChoice();
            if (choice == 2 && this.categories.getSubCategories(categoryOfChoice).isEmpty()) {
                System.out.println();
                System.out.println("Denna kategori har inga sub kategorier.");
                choice = 1;
            }
        }
        runShopProductMenu();
    }


    private Category runCategoryChoice(Category categoryOfChoice, int choice) {
        switch (choice) {
            case 2 -> categoryOfChoice = makeUserChooseASubCategory(categoryOfChoice);
            case 1 -> printAllProductsInCategory(categoryOfChoice);
        }
        return categoryOfChoice;
    }

    private Category makeUserChooseASubCategory(Category chosenCategory) {
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

    private void printAllProductsInCategory(Category categoryOfChoice) {
        System.out.println();
        System.out.println("Följande produkter finns i angiven kategori: ");
        categories.getSubCategoriesInclusiveThisCategory(categoryOfChoice)
                .forEach(category -> products.findProductsByCategory(category)
                        .forEach(product -> System.out.println(product.name())));
    }

    private int getCategoryNavigationChoice() {
        System.out.println();
        System.out.println("Välj ett av följande: ");
        System.out.println("1. Visa alla varor inom denna kategori.");
        System.out.println("2. Visa denna kategorins sub kategorier.");
        return InputHandler.getIntegerInput();
    }
}
