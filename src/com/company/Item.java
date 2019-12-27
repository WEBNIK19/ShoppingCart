package com.company;

/** item info */
public abstract class Item
{
    String title;
    double price;
    int quantity;


    public Item(String title, double price, int quantity) {
        if (title == null || title.length() == 0 || title.length() > 32)
            throw new IllegalArgumentException("Illegal title");
        if (price < 0.01 || price >= 1000.00)
            throw new IllegalArgumentException("Illegal price");
        if (quantity <= 0 || quantity > 1000)
            throw new IllegalArgumentException("Illegal quantity");
        this.title = title;
        this.price = price;
        this.quantity = quantity;
    }

    public abstract double getDiscount();

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
