package se.iths.helena.labb2;


public class CategorySimpler {
    private static final CategorySimpler highestCategory = new CategorySimpler("Categories");
    private final String name;
    private CategorySimpler higherCategory;

    public CategorySimpler(String name) {
        this.name = name;
    }

    public CategorySimpler(String name, CategorySimpler higherCategory) {
        this.name = name;
        this.higherCategory = higherCategory;
    }

    public CategorySimpler getHigherLevelCategory() {
        if (higherCategory == null)
            return highestCategory;
        else
            return higherCategory;
    }

    public String getName() {
        return name;
    }

    public void setHigherLevelCategory(CategorySimpler higherCategory) {
        this.higherCategory = higherCategory;
    }

}
