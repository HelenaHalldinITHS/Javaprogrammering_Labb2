package se.iths.helena.labb2.shop;

public class SecondLevelDiscount implements Discountable {
    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 250 && price < 500)
            return price * 0.8;
        return price;
    }
}
