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

}