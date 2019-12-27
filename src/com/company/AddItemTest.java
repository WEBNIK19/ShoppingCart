package com.company;

import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AddItemTest {
    private static final String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    Random random;
    public String randStr(int from, int to){
        StringBuilder builder = new StringBuilder();
        int length = random.nextInt(to - from) + from;
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }
    @Before
    public void initialize(){
        this.random = new Random();
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void addItemTest(){
        System.out.println("* ShoppingCartTest: addItem()");
        ShoppingCart cart = new ShoppingCart();
        try {
            Item item = new ItemRegular("",5.00,1 + random.nextInt(999));
            cart.addItem(item);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal title"));
        }
        System.out.println("Short title illegal passed");
        try {
            Item item = new ItemSecondFree(randStr(1,32),0.00,1 + random.nextInt(999));
            cart.addItem(item);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal price"));
        }
        System.out.println("Low price illegal, correct title passed");
        try {
            Item item = new ItemDiscount(randStr(1,32),random.nextFloat() * 1000,0);
            cart.addItem(item);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal quantity"));
        }
        System.out.println("Low quantity illegal, correct price and title passed");
        try {
            Item item = new ItemSale(randStr(33,1024),random.nextFloat() * 1000,1 + random.nextInt(999));
            cart.addItem(item);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal title"));
        }
        System.out.println("Long title illegal passed");
        try {
            Item item = new ItemRegular(randStr(1,32),random.nextFloat() * 1000,1000 + random.nextInt(1000));
            cart.addItem(item);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal quantity"));
        }
        System.out.println("High quantity illegal, correct price and title passed");


        try {
            for(int i = 0; i < 100; i++){
                Item item = new ItemRegular(randStr(1,32),random.nextFloat() * 1000,1 + random.nextInt(999));
                cart.addItem(item);
            }
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertThat(e.getMessage(), is("No more space in cart"));
        }
        System.out.println("Overflow cart, correct price, title and quantity passed");

    }


}