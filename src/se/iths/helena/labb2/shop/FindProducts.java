package se.iths.helena.labb2.shop;

import se.iths.helena.labb2.InputHandler;
import se.iths.helena.labb2.Product;
import se.iths.helena.labb2.Products;

public class FindProducts {
    private static final int RETURN = 0;
    public Products products;
    public ShoppingCart cart;

    public FindProducts(Products products, ShoppingCart cart) {
        this.products = products;
        this.cart = cart;
    }

    public void runShopProductMenu() {
        printShopProductMenu();
        if (InputHandler.getIntegerInput() == RETURN)
            return;
        makeUserChooseAProduct();
    }

    private void makeUserChooseAProduct() {
        System.out.println();
        String input = InputHandler.getInput("Välj en produkt genom att skriva dess namn: ");
        System.out.println();
        products.getProductByName(input)
                .ifPresentOrElse(this::showInfoOfProduct, () -> System.out.println("Namnet du skrev matchar ingen produkt"));
    }

    private void showInfoOfProduct(Product product) {
        product.showInfo();
        cart.askUserIfProductShouldBeAdded(product);
    }

    private void printShopProductMenu() {
        System.out.println();
        System.out.println("Vad vill du göra nu?");
        System.out.println("1. Visa information om en produkt");
        System.out.println("0. Gå bakåt");
    }

}
