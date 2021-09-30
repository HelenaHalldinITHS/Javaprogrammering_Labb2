package se.iths.helena.labb2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private Map<Product, Integer> inventory = new HashMap<>();

    public void addOneItem(Product product) {
        int totalItems = 1;
        if (inventory.containsKey(product))
            totalItems += inventory.get(product);
        inventory.put(product, totalItems);
    }

    public void addItems(Product product, int numberOfItems) {
        int totalItems = numberOfItems;
        if (inventory.containsKey(product))
            totalItems += inventory.get(product);
        inventory.put(product, totalItems);
    }

    public void removeOneItemOfProduct(Product product) {
        if (!inventory.containsKey(product) || inventory.get(product) == 0)
            throw new IllegalArgumentException(); //There is no item in inventory
        else
            inventory.replace(product, inventory.get(product) - 1); //Minskar antal
    }

    public void removeItemsOfProduct(Product product, int amount) {
        if (!inventory.containsKey(product))
            throw new IllegalArgumentException(); //There is no item in inventory
        else
            inventory.replace(product, inventory.get(product) - amount); //Minskar antal
    }

    public int amountOfItemsInInventory(Product product) {
        if (inventory.containsKey(product))
            return inventory.get(product);
        else
            return 0;
    }


    public void showContent() {
        System.out.println();
        inventory.forEach((product, integer) -> System.out.println("Namn: " + product.name()
                + ", Antal: " + integer + ", Totalt pris: " + product.price() * integer));
        System.out.println("TOTALT BELOPP: " + totalPriceOfInventory());
    }

    public int totalPriceOfInventory() {
        AtomicInteger sum = new AtomicInteger();
        inventory.forEach((product, integer) -> sum.addAndGet(product.price() * integer));
        return sum.get();
    }
}
