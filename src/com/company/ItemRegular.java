package com.company;

public class ItemRegular extends Item{
    public ItemRegular(String title, double price, int quantity) {
        super(title, price, quantity);
    }

    /**
     * For each full 100 items item gets additional 10%, but not more than 80% total
     * @return discount multiplier
     */
    @Override
    public double getDiscount() {
        double dis = 0;
        dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
        return 1.0 - dis;
    }
}
