package se.iths.helena.labb2.shop;

@FunctionalInterface
public interface Discountable {
    double getDiscountedPrice(double price);
}
