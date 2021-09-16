package se.iths.helena.labb2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void addingASubcategoryToACategoryShouldBeAddedToList(){
        Category frukt = new Category("Frukt");
        Category meloner = new Category("Meloner");
        frukt.addSubcategory(meloner);

        assertEquals(meloner,frukt.getSubcategories().get(0));
    }

    @Test
    void HigherLevelCategoryOfFruitShouldBeCategories(){
        Category frukt = new Category("Frukt");
        assertEquals("Categories",frukt.getHigherLevelCategory().getName());
    }

    @Test
    void HigherLevelCategoryOfAppleShouldBeFrukt(){
        Category frukt = new Category("Frukt");
        Category apple = new Category("Äpple");
        frukt.addSubcategory(apple);
        assertEquals("Frukt",apple.getHigherLevelCategory().getName());
    }

}