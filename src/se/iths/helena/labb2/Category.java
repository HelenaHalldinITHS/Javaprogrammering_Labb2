package se.iths.helena.labb2;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a Category in a hierarchy.
 * A Category can have both sub categories (as many as the user wants) and one higher level category.
 */
public class Category {

    private static Category highestCategory = new Category("Categories");
    private String name;
    private Category higherCategory;
    private List<Category> subcategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addSubcategory(Category category){
        category.higherCategory = this;
        subcategories.add(category);
    }

    public Category getHigherLevelCategory() {
        if (higherCategory == null)
            return highestCategory;
        else
            return higherCategory;
    }

    public void printSubcategories(){
        for (Category subcategory : subcategories) {
            System.out.println(subcategory.name);
        }
    }

    public String getName() {
        return name;
    }

    public List<Category> getSubcategories() {
        return subcategories;
    }

}
