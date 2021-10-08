package se.iths.helena.labb2.shop;

public class FirstLevelDiscount implements Discountable {
    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 100 && price < 250)
            return price * 0.9;
        return price;
    }
}
