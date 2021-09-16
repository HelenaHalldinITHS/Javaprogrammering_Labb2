package se.iths.helena.labb2;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Category> subcategorys = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

}
