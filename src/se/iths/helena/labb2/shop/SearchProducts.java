package se.iths.helena.labb2.shop;

import se.iths.helena.labb2.InputHandler;
import se.iths.helena.labb2.Product;
import se.iths.helena.labb2.Products;

import java.util.List;

public class SearchProducts extends FindProducts {
    public SearchProducts(Products products, ShoppingCart cart) {
        super(products, cart);
        findProductsThroughSearching();
    }

    private void findProductsThroughSearching() {
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

}
