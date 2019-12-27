package com.company;

public class ItemDiscount extends Item {
    public ItemDiscount(String title, double price, int quantity) {
        super(title, price, quantity);
    }

    /**
     * For ITEM_DISCOUNT discount is 10% + 10% for each full 10 items, but not more than 50% total
     * For each full 100 items item gets additional 10%, but not more than 80% total
     * @return discount multiplier
     */
    @Override
    public double getDiscount() {
        double dis = 0;
        dis = (0.1 + ( quantity / 10) * 0.1 - 0.5 < 0) ? (0.1 + ( quantity / 10) * 0.1) : 0.5;
        dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
        return 1.0 - dis;
    }
}
