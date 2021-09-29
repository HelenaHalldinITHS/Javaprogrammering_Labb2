package se.iths.helena.labb2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

public class Categories implements Iterable<Category> {
    private Set<Category> categories;

    public Categories(){
        categories = new HashSet<>();
    }

    public void addCategory(Category category){
        if (category==null)
            throw new IllegalArgumentException();

        categories.add(category);
    }

    public boolean contains(Category category){
        if (category == null)
                return false;
        return categories.contains(category);
    }

    public Optional<Category> get(String name){
        return categories.stream()
                .filter(category -> category.getName().equals(name))
                .findAny();
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
