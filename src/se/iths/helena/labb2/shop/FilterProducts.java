package se.iths.helena.labb2.shop;

import se.iths.helena.labb2.*;

import java.util.List;

public class FilterProducts extends FindProducts {
    private Categories categories;

    public FilterProducts(Products products, ShoppingCart cart, Categories categories) {
        super(products, cart);
        this.categories = categories;
        findProductsUsingFilter();
    }

    private void findProductsUsingFilter() {
        System.out.println();
        System.out.println("Hitta produkter genom att filtrera. Välj ett av följande alternativ: ");
        System.out.println("1. Filtrera på pris");
        System.out.println("2. Filtrera på varumärke");
        System.out.println("3. Filtrera på kategori");

        runFilterMenuChoice(InputHandler.getIntegerInput(1, 3));
        runShopProductMenu();
    }

    private void runFilterMenuChoice(int choice) {
        switch (choice) {
            case 1 -> filterByPrice();
            case 2 -> filterByBrand();
            case 3 -> filterByCategory();
        }
    }

    private void filterByBrand() {
        System.out.println();
        String brand = InputHandler.getInput("Ange det märke du vill filtrera på:");
        printFilterResult(products.findProductsByBrand(brand));
    }

    private void filterByPrice() {
        System.out.println();
        long min = InputHandler.getLongInput("Ange önskad lägsta prisgräns: ");
        System.out.println();
        long max = InputHandler.getLongInput("Ange önskad högsta prisgräns: ");
        printFilterResult(products.findProductsInPriceRange(min, max));
    }

    private void filterByCategory() {
        System.out.println();
        String categoryOfChoice = InputHandler.getInput("Ange den kategori du vill filtrera på");
        categories.get(categoryOfChoice)
                .ifPresentOrElse(this::printAllProductsInCategory, () -> System.out.println("Kategorin du skrev finns ej."));
    }

    private void printAllProductsInCategory(Category categoryOfChoice) {
        System.out.println();
        System.out.println("Följande produkter finns i angiven kategori: ");
        categories.getSubCategoriesInclusiveThisCategory(categoryOfChoice)
                .forEach(category -> products.findProductsByCategory(category)
                        .forEach(product -> System.out.println(product.name())));
    }

    private void printFilterResult(List<Product> filterResult) {
        System.out.println();
        if (filterResult.isEmpty())
            System.out.println("Din filtrering gav inga resultat");
        else
            filterResult.stream()
                    .map(Product::name)
                    .forEach(System.out::println);
    }


}
