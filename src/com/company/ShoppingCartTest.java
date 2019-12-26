package com.company;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShoppingCartTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void mainTest() {

    }

    @Test
    public void addItemTest(){
        System.out.println("* ShoppingCartTest: addItem()");
        ShoppingCart cart = new ShoppingCart();
        try {
            cart.addItem("",5.00,1, ShoppingCart.ITEM_REGULAR);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal title"));
        }
        System.out.println("Short title illegal passed");
        try {
            cart.addItem("A",0.00,1, ShoppingCart.ITEM_DISCOUNT);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal price"));
        }
        System.out.println("Low price illegal, correct title passed");
        try {
            cart.addItem("A",999.99,0, ShoppingCart.ITEM_SECOND_FREE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal quantity"));
        }
        System.out.println("Low quantity illegal, correct price and title passed");
        try {
            cart.addItem("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",5.00,0, ShoppingCart.ITEM_FOR_SALE);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal title"));
        }
        System.out.println("Long title illegal passed");
        try {
            cart.addItem("Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",1000.00,1,1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal price"));
        }
        System.out.println("High price illegal, correct title passed");
        try {
            cart.addItem("A",0.01,1001,1);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal quantity"));
        }
        System.out.println("High quantity illegal, correct price and title passed");
        try {
            cart.addItem("A",0.01,1000,5);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal type"));
        }
        System.out.println("Type illegal passed");
        try {
            cart.addItem("A",0.01,1000,-5);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), is("Illegal type"));
        }
        System.out.println("Type illegal passed");
        try {
            for(int i = 0; i <= 500;i++){
                cart.addItem("Aaaaa",5.00,1000,1);
                cart.addItem("Aaaaa",5.00,1,1);
            }
            fail("Expected an IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            assertThat(e.getMessage(), is("No more space in cart"));
        }
        System.out.println("Overflow cart, correct price, title and quantity passed");

    }

    @Test
    public void ToStringTest() {

    }

}