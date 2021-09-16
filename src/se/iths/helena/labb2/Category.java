package se.iths.helena.labb2;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> subcategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addSubcategory(Category category){
        subcategories.add(category);
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }
}
