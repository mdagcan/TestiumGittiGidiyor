package com.gittigidiyor.tests;

import org.junit.Test;

public class LoginTest extends TestBase{

    @Test
    public void test1() throws InterruptedException {
        homePage
                .addBasket()
                .addComp2Basket()
        ;

    }
}
