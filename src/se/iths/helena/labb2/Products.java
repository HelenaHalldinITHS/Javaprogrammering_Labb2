package se.iths.helena.labb2;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the set of products in the store.
 * It can be looked at as an order list.
 * It does not hold any data about the amount of
 * a product available in the store.
 */

public class Products {
    private Set<Product> products;

    public Products(){
         products = new HashSet<>();
    }



}
