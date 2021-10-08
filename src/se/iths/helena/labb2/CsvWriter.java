package se.iths.helena.labb2;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//OBSOBS
//Filläsning och filskrivning använder try with för att stänga filerna automatiskt.
// try(FileWriter fileWriter = new FileWriter(path) ){}


public class CsvWriter {
    private static final String homeFolder = System.getProperty("user.home");
    private static final String SEPARATOR = ";";

    public void saveCategories(Categories categories) {
        Path path = Path.of(homeFolder, "Labb2", "category.csv");
        List<String[]> data = new ArrayList<>();
        String[] temp = new String[2];
        categories.forEach(category -> creatStringArrForCategory(temp, category, data));
        writeToCsvFile(data, path, "#category");
    }

    public void saveProducts(Products products) {
        Path path = Path.of(homeFolder, "Labb2", "product.csv");
        List<String[]> data = new ArrayList<>();
        String[] temp = new String[6];
        products.forEach(product -> creatStringArrForProduct(temp, product, data));
        writeToCsvFile(data, path, "#product");
    }

    private void creatStringArrForProduct(String[] temp, Product product, List<String[]> data) {
        temp[0] = product.name();
        temp[1] = String.valueOf(product.price());
        temp[2] = product.category().getName();
        temp[3] = product.brand();
        temp[4] = String.valueOf(product.id());
        temp[5] = String.valueOf(product.amountInStore());
        data.add(temp.clone());
    }

    private void creatStringArrForCategory(String[] temp, Category category, List<String[]> data) {
        temp[0] = category.getName();
        String nameOfHigherLevelCategory = category.getHigherLevelCategory().getName();
        if (nameOfHigherLevelCategory.equals("Categories"))
            temp[1] = "-";
        else
            temp[1] = nameOfHigherLevelCategory;
        data.add(temp.clone());
    }

    private void writeToCsvFile(List<String[]> data, Path path, String label) {
        List<String> strings = new ArrayList<>();
        strings.add(label);
        strings.addAll(getDataAsListOfStrings(data));

        try {
            Files.write(path, strings, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private List<String> getDataAsListOfStrings(List<String[]> dataLines) {
        return dataLines.stream()
                .map(this::convertToCSV)
                .collect(Collectors.toList());
    }

    public String convertToCSV(String[] data) {
        return String.join(SEPARATOR, data);
    }
}
