package se.iths.helena.labb2;

import java.util.*;
import java.util.stream.Collectors;

public class Products implements Iterable<Product> {
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product can't be null");
        products.add(product);
    }

    public List<Long> getIds(){
        return products.stream().map(Product::id).toList();
    }

    public void initialiseProducts(Set<Product> productSet) {
        products = productSet;
    }


    public List<Product> findProductsInPriceRange(long min, long max) {
        return products.stream().filter(product -> product.price() >= min)
                .filter(product -> product.price() <= max)
                .collect(Collectors.toList());
    }

    public List<Product> findProductsByBrand(String brandSearch) {
        return products.stream()
                .filter(product -> product.brand().toLowerCase(Locale.ROOT).equals(brandSearch.toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public Optional<Product> getProductByName(String name) {
        return products.stream()
                .filter(product -> product.name().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT))).findAny();
    }

    public List<Product> getProductsByPartOfName(String searchWord) {
        return products.stream().filter(product -> product.name()
                .toLowerCase(Locale.ROOT).contains(searchWord.toLowerCase(Locale.ROOT))).collect(Collectors.toList());
    }

    public Optional<Product> findProductById(long id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findAny();
    }

    //returns a list of products of the chosen category - not lower lever
    public List<Product> findProductsByCategory(Category category) {
        return products.stream()
                .filter(product -> product.category().getName().equals(category.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }
}
