package se.iths.helena.labb2;


public class CategorySimpler {
    private static final Category highestCategory = new Category("Categories");
    private final String name;
    private Category higherCategory;

    public CategorySimpler(String name) {
        this.name = name;
    }

    public Category getHigherLevelCategory() {
        if (higherCategory == null)
            return highestCategory;
        else
            return higherCategory;
    }

    public String getName() {
        return name;
    }

}
