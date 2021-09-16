package se.iths.helena.labb2;

public class testingThroughPrints {

    public static void main(String[] args) {
        Category frukt = new Category("Frukt");
        Category meloner = new Category("Meloner");
        Category apples = new Category("Apples");


        frukt.addSubcategory(meloner);
        frukt.addSubcategory(apples);
        frukt.printSubcategories();

        System.out.println(apples.getHigherLevelCategory().getName());
        System.out.println(frukt.getHigherLevelCategory().getName());

        Product product = new Product("Royal gala", 20, apples, Brand.TEST,1111111);
        System.out.println(product.getCategory().getHigherLevelCategory().getName());

    }
}
