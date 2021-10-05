package se.iths.helena.labb2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Inventory {
    private Map<Product, Integer> inventory = new HashMap<>();


    public void initialiseInventory(Map<Product,Integer> inventory){
        this.inventory = inventory;
    }

    public void addItems(Product product, int numberOfItems) {
        int totalItems = numberOfItems;
        if (inventory.containsKey(product))
            totalItems += inventory.get(product);
        inventory.put(product, totalItems);
    }

    public void removeItemsOfProduct(Product product, int amount) {
        if (!inventory.containsKey(product) && amount!=0)
            throw new IllegalArgumentException(); //There is no item in inventory
        else
            inventory.put(product, inventory.get(product) - amount); //Minskar antal
    }

    public int amountOfItemsInInventory(Product product) {
        if (inventory.containsKey(product))
            return inventory.get(product);
        else
            return 0;
    }


    public void showContent() {
        inventory.forEach((product, integer) -> System.out.println("Namn: " + product.name()
                + ", Antal: " + integer + ", Totalt pris: " + product.price() * integer));
        System.out.println("Belopp utan rabatt: " + totalPriceOfInventory());
    }

    public double totalPriceOfInventory() {
        AtomicInteger sum = new AtomicInteger();
        inventory.forEach((product, integer) -> sum.addAndGet(product.price() * integer));
        return sum.doubleValue();
    }

    public void clearInventory(){
        inventory.clear();
    }

    public List<String[]> getAsListOfStringArr() {
        String[] temp = new String[2];
        List<String[]> result = new ArrayList<>();
        inventory.forEach((product, integer) -> getStringArrayAndAddToResult(product,integer,temp,result));
        return result;
    }

    private void getStringArrayAndAddToResult(Product product, Integer integer, String[] temp, List<String[]> result ){
        temp[0] = product.name();
        temp[1] = String.valueOf(integer);
        result.add(temp.clone());
    }
}
