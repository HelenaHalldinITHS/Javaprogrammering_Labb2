package se.iths.helena.labb2;

import java.util.*;
import java.util.stream.Collectors;

public class Categories implements Iterable<Category> {
    private Set<Category> categories;

    public Categories() {
        categories = new HashSet<>();
    }

    public void addCategory(Category category) {
        if (category == null)
            throw new IllegalArgumentException();

        categories.add(category);
    }

    public boolean contains(Category category) {
        if (category == null)
            return false;
        return categories.contains(category);
    }

    public Optional<Category> get(String name) {
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findAny();
    }

    public List<Category> getAllCategories() {
        //returnera en variant som inte får modifieras!!
        return List.copyOf(categories);
    }

    public List<Category> getSubCategories(Category highestCategory) {
        return categories.stream()
                .filter(category -> isSubCategory(highestCategory, category))
                .collect(Collectors.toList());
    }

    private boolean isSubCategory(Category highestCategory, Category category) {
        Category temp = category;
        do{
            temp = temp.getHigherLevelCategory();
            if (highestCategory.equals(temp))
                return true;
        } while (!temp.equals(Category.highestCategory));
        return false;
    }


    @Override
    public Iterator<Category> iterator() {
        return categories.iterator();
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categories=" + categories +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Categories that = (Categories) o;

        return categories != null ? categories.equals(that.categories) : that.categories == null;
    }

    @Override
    public int hashCode() {
        return categories != null ? categories.hashCode() : 0;
    }
}
