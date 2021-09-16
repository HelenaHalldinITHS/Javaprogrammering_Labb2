package se.iths.helena.labb2;

import java.util.HashMap;
import java.util.Map;

//Just mind dump....

public class Store {
    Map<Product, Integer> inventory = new HashMap<>(); //Borde kanske ha en annan representation...?

    public void addOneItem(Product product) {
        if (inventory.containsKey(product)) { //Kolla om produkten finns.
            inventory.replace(product, inventory.get(product) + 1); //öka antal av produkten
        } else {
            inventory.put(product, 1);
        }
    }

    public void addItems(Product product, Integer numberOfItems){
        if (inventory.containsKey(product)) { //Kolla om produkten finns.
            inventory.replace(product, inventory.get(product) + numberOfItems); //öka antal av produkten
        } else {
            inventory.put(product, numberOfItems);
        }
    }

    public void clearAllInventory(){
        inventory.clear();
    }

    public void clearInventory(Product product){
        if (inventory.containsKey(product)){
            inventory.replace(product, 0);
        }
    }

    public void removeOneItemOfProduct(Product product) {
        if (!inventory.containsKey(product) || inventory.get(product)==0)
            throw new IllegalArgumentException(); //There is no item in inventory
        else
            inventory.replace(product, inventory.get(product) - 1); //Minskar antal
    }

    public int itemsInStore(Product product){
        if (inventory.containsKey(product))
            return inventory.get(product);
        else
            return 0;
    }


}



