package se.iths.helena.labb2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader {
    private static final Pattern pattern = Pattern.compile(";"); // separerar med semicolon för fil skriven i excell

    public Set<Category> readCategories() {
        String homePath = System.getProperty("user.home");
        Path csvPath = Path.of(homePath, "Labb2", "category.csv");
        Set<Category> categorySet = Set.of();

        try (Stream<String> lines = Files.lines(csvPath)) {
            categorySet = lines.skip(1)
                    .map(CsvReader::createCategory)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return categorySet;
    }

    private static Category createCategory(String line) {
        String[] arr = pattern.split(line);

        if (arr[1].equals("-")) {
            return new Category(arr[0]);
        } else
            return new Category(arr[0], new Category(arr[1]));
    }

    public Set<Product> readProducts(Categories categories) {
        String homePath = System.getProperty("user.home");
        Path csvPath = Path.of(homePath, "Labb2", "product.csv");
        Set<Product> productSet = Set.of();

        try (Stream<String> lines = Files.lines(csvPath)) {
            productSet = lines.skip(1)
                    .map(line -> createProduct(line, categories))
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productSet;
    }

    private static Product createProduct(String line, Categories categories){
        String[] arr = pattern.split(line);

        return new Product(arr[0], Integer.parseInt(arr[1]), categories.getFromString(arr[2]),
                arr[3], Long.parseLong(arr[4]));
    }

    public Map<Product, Integer> readInventory(Products products) {
        String homePath = System.getProperty("user.home");
        Path csvPath = Path.of(homePath, "Labb2", "inventory.csv");
        List<ProductStock> inventoryList = new ArrayList<>();

        try (Stream<String> lines = Files.lines(csvPath)) {
            inventoryList = lines.skip(1)
                    .map(line -> createInventory(line, products))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return makeInventoryListToMap(inventoryList);
    }

    private Map<Product, Integer> makeInventoryListToMap(List<ProductStock> inventoryList) {
        Map<Product, Integer> inventory = new HashMap<>();
        inventoryList.forEach(productStock -> inventory.put(productStock.product(), productStock.amount()));
        return inventory;
    }

    private ProductStock createInventory(String line, Products products) {
        String[] arr = pattern.split(line);
        return new ProductStock(products.getProductByName(arr[0]).orElseThrow(), Integer.parseInt(arr[1]));
    }
}
record ProductStock (Product product, int amount){};
