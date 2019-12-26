package com.company;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.*;
@RunWith(value = Parameterized.class)

public class DiscountTest {

    private String title;
    private float price;
    private int quantity;
    private int type;
    private String expected;
    private ShoppingCart cart;

    @Before
    public void initialize(){
        this.cart = new ShoppingCart();
    }

    public DiscountTest(int type, int quantity, String expected) {
        this.type = type;
        this.quantity = quantity;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index} : Type {0} : Quantity {1}")
    public static Iterable<Object[]> dataForTest(){
        return Arrays.asList(new Object[][]{
                {ShoppingCart.ITEM_REGULAR, 1, " # Item                  Price Quan. Discount Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Item                   $5.00    1  -      $5.00\n" +
                        "---------------------------------------------------------\n" +
                        " 1      $5.00"},
                {ShoppingCart.ITEM_FOR_SALE, 1, " # Item                  Price Quan. Discount Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Item                   $5.00    1      80%      $1.00\n" +
                        "---------------------------------------------------------\n" +
                        " 1      $1.00"},
                {ShoppingCart.ITEM_SECOND_FREE, 2, " # Item                  Price Quan. Discount Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Item                   $5.00    2      50%      $5.00\n" +
                        "---------------------------------------------------------\n" +
                        " 1      $5.00"},
                {ShoppingCart.ITEM_DISCOUNT, 10, " # Item                  Price Quan. Discount Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Item                   $5.00   10      20%     $40.00\n" +
                        "---------------------------------------------------------\n" +
                        " 1     $40.00"},
                {ShoppingCart.ITEM_REGULAR, 100, " # Item                  Price Quan. Discount Total\n" +
                        "---------------------------------------------------------\n" +
                        " 1 Item                   $5.00  100      10%    $450.00\n" +
                        "---------------------------------------------------------\n" +
                        " 1    $450.00"},
        });
    }

    @Test
    public void testToString() {
        cart.addItem("Item",5.00, quantity, type);
        assertEquals(expected, cart.toString());
    }
}