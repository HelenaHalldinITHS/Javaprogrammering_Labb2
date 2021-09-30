package se.iths.helena.labb2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents the set of products in the store.
 * It can be looked at as an order list.
 * It does not hold any data about the amount of
 * a product available in the store.
 */

public class Products implements Iterable<Product>{
    private Set<Product> products;

    public Products(){
         products = new HashSet<>();
    }

    public void addProduct(Product product){
        if (product == null)
            throw new IllegalArgumentException("Product can't be null");
            products.add(product);
    }

    public Set<Product> getAllProducts(){
        return Collections.unmodifiableSet(products);
    }

    public List<Product> findProductsInPriceRange(long min, long max){
        return products.stream().filter(product -> product.price() >= min)
                .filter(product -> product.price() <= max)
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByBrand(String brandSearch){
        return products.stream()
                .filter(product -> product.brand().toLowerCase(Locale.ROOT).equals(brandSearch.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductByName(String name){
        return products.stream()
                .filter(product -> product.name().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))).findAny();
    }

    public List<Product> getProductsByPartOfName(String searchWord){
        return products.stream().filter(product -> product.name()
                .toLowerCase(Locale.ROOT).contains(searchWord.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }

    public Optional<Product> findProductById(long id){
        return products.stream()
                .filter(product -> product.id() == id)
                .findAny();
    }

    public List<Product> getProducts(HashSet<Long> ids){
        return products.stream()
                .filter(product -> ids.contains(product.id()))
                .collect(Collectors.toList());
    }

    //returns a list of products of the chosen category - not lower lever
    public List<Product> findProductsByCategory(Category category){
        return products.stream()
                .filter(product -> product.category().getName().equals(category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
