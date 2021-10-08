package se.iths.helena.labb2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvReader {
    private static final String SEPARATOR = ";";
    private static final Pattern pattern = Pattern.compile(SEPARATOR); // separerar med semicolon för fil skriven i excell
    private static final String HOME_PATH = System.getProperty("user.home");

    public Set<Category> readCategories() {
        Path csvPath = Path.of(HOME_PATH, "Labb2", "category.csv");
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
        Path csvPath = Path.of(HOME_PATH, "Labb2", "product.csv");
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

    private static Product createProduct(String line, Categories categories) {
        String[] arr = pattern.split(line);

        return new Product(arr[0], Integer.parseInt(arr[1]), categories.getFromString(arr[2]),
                arr[3], Long.parseLong(arr[4]), Integer.parseInt(arr[5]));
    }
}
