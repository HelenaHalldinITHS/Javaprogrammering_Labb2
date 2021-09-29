package se.iths.helena.labb2;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Product, Integer> inventory = new HashMap<>();

    public void addOneItem(Product product) {
        int totalItems = 1;
        if (inventory.containsKey(product))
           totalItems += inventory.get(product);
        inventory.put(product, totalItems);
    }

    public void addItems(Product product, int numberOfItems){
        int totalItems = numberOfItems;
        if (inventory.containsKey(product))
            totalItems += inventory.get(product);
        inventory.put(product, totalItems);
    }

    public void removeOneItemOfProduct(Product product) {
        if (!inventory.containsKey(product) || inventory.get(product)==0)
            throw new IllegalArgumentException(); //There is no item in inventory
        else
            inventory.replace(product, inventory.get(product) - 1); //Minskar antal
    }

    public int amountOfItemsInStore(Product product){
        return inventory.getOrDefault(product, 0);
    }


}
