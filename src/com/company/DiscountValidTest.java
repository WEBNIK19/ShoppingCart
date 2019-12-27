package com.company;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Random;
import java.util.Arrays;


import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)

public class DiscountValidTest {

    private Item item;
    private double expected;


    public DiscountValidTest(Item item) {
        Random r = new Random();
        item.setQuantity(r.nextInt(99) + 1);
        this.item = item;
        this.expected = getExpected(item);
    }

    public static double getExpected(Item item){
        double dis = 0;

        if(item instanceof ItemRegular)
                dis = (dis + (item.getQuantity() / 100) * 0.1 - 0.8 < 0) ? (dis + (item.getQuantity() / 100) * 0.1) :  0.8;

        if(item instanceof ItemSecondFree){
            dis = (((item.getQuantity() > 1) ? 1 : 0) * 0.5);
            dis = (dis + (item.getQuantity() / 100) * 0.1 - 0.8 < 0) ? (dis + (item.getQuantity() / 100) * 0.1) :  0.8;
        }

        if(item instanceof ItemDiscount){
            dis = (0.1 + ( item.getQuantity() / 10) * 0.1 - 0.5 < 0) ? (0.1 + ( item.getQuantity() / 10) * 0.1) : 0.5;
            dis = (dis + (item.getQuantity() / 100) * 0.1 - 0.8 < 0) ? (dis + (item.getQuantity() / 100) * 0.1) :  0.8;
        }

        if(item instanceof ItemSale)
                dis = 0.9;

        return 1.0 - dis;
    }
    @Parameterized.Parameters(name = "{index} : Type {0} : Quantity {1}")
    public static Iterable<Item[]> dataForTest(){
        return Arrays.asList(new Item[][]{
                {new ItemRegular("Item",5.00,1)},
                {new ItemSale("Item",5.00,1)},
                {new ItemSecondFree("Item",5.00,1)},
                {new ItemDiscount("Item",5.00,1)},
                {new ItemRegular("Item",5.00,1)},
        });
    }

    @Test
    public void testDiscount() {
        double discount = item.getDiscount();
        int quantity = item.getQuantity();
        assertTrue("Expected "+ expected + " but was got " + discount + " quantity: " + quantity,Math.abs(expected - discount) < 0.001);
    }
}