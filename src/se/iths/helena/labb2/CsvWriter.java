package se.iths.helena.labb2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class CsvWriter {
    private static final String homeFolder = System.getProperty("user.home");
    private static final String SEPARATOR = ";";

    public void saveCategories(Categories categories) {
        Path path = Path.of(homeFolder, "Labb2", "category.csv");
        List<String> strings = new ArrayList<>();
        strings.add("#category");
        categories.forEach(category -> csvRow(category, strings));

        writeToCSVFile(path, strings);
    }

    public void saveProducts(Products products){
        Path path = Path.of(homeFolder, "Labb2", "product.csv");
        List<String> strings = new ArrayList<>();
        strings.add("#products");
        products.forEach(product -> csvRow(product, strings));

        writeToCSVFile(path, strings);
    }

    private void writeToCSVFile(Path path, List<String> strings) {
        try {
            Files.write(path, strings, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void csvRow(Product product, List<String> strings){
        StringBuilder stringBuilder = new StringBuilder();
        strings.add(stringBuilder.append(product.name())
                .append(SEPARATOR)
                .append(product.price())
                .append(SEPARATOR)
                .append(product.category().getName())
                .append(SEPARATOR)
                .append(product.brand())
                .append(SEPARATOR)
                .append(product.id())
                .append(SEPARATOR)
                .append(product.amountInStore())
                .toString());
    }

    private void csvRow(Category category, List<String> strings){
        StringBuilder stringBuilder = new StringBuilder();
        String nameOfHigherLevelCategory = category.getHigherLevelCategory().getName();

        stringBuilder.append(category.getName())
                .append(SEPARATOR);

        if (nameOfHigherLevelCategory.equals("Categories"))
             stringBuilder.append("-");
        else
            stringBuilder.append(nameOfHigherLevelCategory);

        strings.add(stringBuilder.toString());

    }

}
