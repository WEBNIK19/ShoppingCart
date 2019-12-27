package com.company;

public class ItemSale extends Item{
    public ItemSale(String title, double price, int quantity) {
        super(title, price, quantity);
    }

    /**
     * For ItemSale discount is 90%
     * @return discount multiplier
     */
    @Override
    public double getDiscount() {
        double dis = 0.9;
        return 1.0 - dis;
    }
}
