package se.iths.helena.labb2;

@FunctionalInterface
public interface Discountable {
    double getDiscountedPrice(int fullPrice);
}
