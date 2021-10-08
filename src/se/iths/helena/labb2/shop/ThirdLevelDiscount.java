package se.iths.helena.labb2.shop;

public class ThirdLevelDiscount implements Discountable {

    @Override
    public double getDiscountedPrice(double price) {
        if (price >= 500)
            return price * 0.7;
        return price;
    }
}
