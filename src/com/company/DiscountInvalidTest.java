package com.company;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;
@RunWith(value = Parameterized.class)
public class DiscountInvalidTest {
    private int quantity;
    private int type;
    private double expected;
    private ShoppingCart cart;
    @Before
    public void initialize(){
        this.cart = new ShoppingCart();
    }

    public DiscountInvalidTest(int type) {
        Random r = new Random();
        this.type = type;
        this.quantity = r.nextInt(99) + 1;
        this.expected = getExpected(type,quantity);
    }

    public static double getExpected(int type, int quantity){
        double dis = 0;
        Random r = new Random();
        double rand = 0.0;
        switch (type) {
            case ShoppingCart.ITEM_REGULAR:
                dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
                break;
            case ShoppingCart.ITEM_SECOND_FREE:
                dis = (((quantity > 1) ? 1 : 0) * 0.5);
                dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
                break;
            case ShoppingCart.ITEM_DISCOUNT:
                dis = (0.1 + ( quantity / 10) * 0.1 - 0.5 < 0) ? (0.1 + ( quantity / 10) * 0.1) : 0.5;
                dis = (dis + (quantity / 100) * 0.1 - 0.8 < 0) ? (dis + (quantity / 100) * 0.1) :  0.8;
                break;
            case ShoppingCart.ITEM_FOR_SALE:
                dis = 0.9;
        }

        do{
            rand = r.nextFloat();
        }while(Math.abs(rand - dis) < 0.001);

        return 1.0 - rand;
    }
    @Parameterized.Parameters(name = "{index} : Type {0} : Quantity {1}")
    public static Iterable<Object[]> dataForTest(){
        return Arrays.asList(new Object[][]{
                {ShoppingCart.ITEM_REGULAR},
                {ShoppingCart.ITEM_FOR_SALE},
                {ShoppingCart.ITEM_SECOND_FREE},
                {ShoppingCart.ITEM_DISCOUNT},
                {ShoppingCart.ITEM_REGULAR},
        });
    }

    @Test
    public void calculateDiscount() {
        cart.addItem("Item",5.00, quantity, type);
        double discount = ShoppingCart.calculateDiscount((ShoppingCart.Item)cart.getItem());
        assertFalse("Expected "+ expected + " but was got " + discount + " quantity: " + quantity,Math.abs(expected - discount) < 0.001);
    }
}