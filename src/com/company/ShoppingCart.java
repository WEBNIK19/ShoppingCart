package com.company;
import java.util.*;
import java.text.*;

public class ShoppingCart
{
    public static void main(String[] args)
    {
        ShoppingCart cart = new ShoppingCart();
        Item regular = new ItemRegular("Apple", 0.99, 5);
        cart.addItem(regular);
        Item second_free = new ItemSecondFree("Banana", 20.00, 4);
        cart.addItem(second_free);
        Item for_sale = new ItemSale("A long piece of toilet paper", 17.20, 1);
        cart.addItem(for_sale);
        Item many_regular = new ItemRegular("Nails", 2.00, 500);
        cart.addItem(many_regular);
        System.out.println(cart.toString());
    }

    public void addItem(Item item)
    {
        if (items.size() == 99)
            throw new IndexOutOfBoundsException("No more space in cart");
        items.add(item);
    }

    public Object getItem(){
        return items.get(items.size() - 1);
    }
/**
 * Formats shopping price.
 *
 */
public String toString()
{
    StringBuffer sb = new StringBuffer();
    if (items.size() == 0)
        return "No items.";
    double total = 0.00;
    sb.append(" # Item                  Price Quan. Discount Total\n");
    sb.append("---------------------------------------------------------\n");
    for (int i = 0; i < items.size(); i++) {
        Item item = (Item) items.get(i);
        double discount = calculateDiscount(item);
        double itemTotal = item.price * item.quantity * discount;
        appendPaddedRight(sb, String.valueOf(i + 1), 2);
        sb.append(" ");
        appendPaddedLeft(sb, item.title, 20);
        sb.append(" ");
        appendPaddedRight(sb, MONEY.format(item.price), 7);
        sb.append(" ");
        appendPaddedRight(sb, String.valueOf(item.quantity), 4);
        sb.append(" ");
        if (discount == 0)
            sb.append(" -");
        else {
            appendPaddedRight(sb, String.valueOf((1.0 - discount) * 100), 7);
            sb.append("%");
        }
        sb.append(" ");
        appendPaddedRight(sb, MONEY.format(itemTotal), 10);
        sb.append("\n");
        total += itemTotal;
    }
    sb.append("---------------------------------------------------------\n");
    appendPaddedRight(sb, String.valueOf(items.size()), 2);
    sb.append(" ");
    appendPaddedRight(sb, MONEY.format(total), 10);
    return sb.toString();
}

    public static double calculateDiscount(Item item)
    {
        return item.getDiscount();
    }

    // --- private section --------------------------------------------------
    private static final NumberFormat MONEY;
    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        MONEY = new DecimalFormat("$#.00", symbols);
    }
    /**
     * Adds to string buffer given string, padded with spaces.
     * @return " str".length() == width
     */
    private static void appendPaddedRight(StringBuffer sb, String str, int width)
    {
        for (int i = str.length(); i < width; i++)
            sb.append(" ");
        sb.append(str);
    }
    /**
     * Adds string to buffer, wills spaces to width.
     * If string is longer than width it is trimmed and ends with '...'
     */
    private static void appendPaddedLeft(StringBuffer sb, String str, int width)
    {
        if (str.length() > width) {
            sb.append(str.substring(0, width-3));
            sb.append("...");
        }
        else {
            sb.append(str);
            for (int i = str.length(); i < width; i++)
                sb.append(" ");
        }
    }

    /** Container for added items */
    private List items = new ArrayList();
}