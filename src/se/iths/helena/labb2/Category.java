package se.iths.helena.labb2;


public class Category {
    private static final Category highestCategory = new Category("Categories");
    private final String name;
    private Category higherCategory;

    public Category(String name) {
        this.name = name;
        higherCategory = highestCategory;
    }

    public Category(String name, Category higherCategory) {
        this.name = name;
        this.higherCategory = higherCategory;
    }

    public Category(String name, String higherCategory) {
        this.name = name;
        this.higherCategory = get(higherCategory);
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
    public Category get(String nameOfCategory) {
        return new Category(nameOfCategory);
    }

    public void setHigherLevelCategory(Category higherCategory) {
        this.higherCategory = higherCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return higherCategory != null ? higherCategory.equals(category.higherCategory) : category.higherCategory == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (higherCategory != null ? higherCategory.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", higherCategory=" + higherCategory +
                '}';
    }
}
