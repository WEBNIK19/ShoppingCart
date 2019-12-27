package com.company;

public class ItemSecondFree extends Item {
    public ItemSecondFree(String title, double price, int quantity) {
        super(title, price, quantity);
    }

    /**
     * For ITEM_SECOND_FREE discount is 50% if quantity > 1
     * For each full 100 items item gets additional 10%, but not more than 80% total
     * @return discount multiplier
     */
    @Override
    public double getDiscount() {
        double dis = 0;
        dis = (((quantity > 1) ? 1 : 0) * 0.5);
        dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
        return 1.0 - dis;
    }
}
