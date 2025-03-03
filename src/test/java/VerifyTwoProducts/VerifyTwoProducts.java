package VerifyTwoProducts;

import Base.BaseSteps;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyTwoProducts extends BaseSteps {
    @Test (priority = 1)
    public void verifyTwoProductsPurchasedSuccessfully() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        LaptopPage laptopPage = new LaptopPage(driver);
        MonitorPage monitorPage = new MonitorPage(driver);
        CartPage cartPage = new CartPage(driver);
        Thread.sleep(2000);
        //Login
        homePage.onClickLoginLink();
        loginPage.insertLoginUsername("Eizaldint");
        loginPage.insertLoginPassword("11413");
        loginPage.onClickLoginButton();
        String expectedResults = loginPage.verifyLogin();
        String actualResults = "Welcome Eizaldint";
        Thread.sleep(1000);
        Assert.assertTrue(expectedResults.contains(actualResults));
        //Laptop
        homePage.onClickLaptopCat();
        laptopPage.onClickLaptopItem();
        laptopPage.AddToCart();
        expectedResults = laptopPage.verifyAlertMessageAdded();
        actualResults = "Product added.";
        Assert.assertTrue(expectedResults.contains(actualResults));
        laptopPage.acceptAlertMessage();
        laptopPage.navToHomePage();
        //Monitor
        homePage.onClickMonitorCat();
        monitorPage.onClickMonitorItem();
        monitorPage.AddToCart();
        expectedResults = monitorPage.verifyAlertMessageAdded();
        actualResults = "Product added.";
        Assert.assertTrue(expectedResults.contains(actualResults));
        monitorPage.acceptAlertMessage();
        monitorPage.navToHomePage();
        //Cart
        String monitor = "Apple monitor 24";
        String laptop = "Sony vaio i5";

        homePage.onClickCartLink();

        expectedResults = cartPage.setCheckItemName1();
        if (expectedResults == laptop) {
            Assert.assertTrue(expectedResults.contains(laptop));
            expectedResults = cartPage.setCheckItemPrice1();
            actualResults = "790";
            Assert.assertTrue(expectedResults.contains(actualResults));
        } else if (expectedResults == monitor) {
            Assert.assertTrue(expectedResults.contains(monitor));
            expectedResults = cartPage.setCheckItemPrice2();
            actualResults = "400";
            Assert.assertTrue(expectedResults.contains(actualResults));
        }
        //actualResults = laptop;
        //Assert.assertTrue(expectedResults.contains(actualResults));

        expectedResults = cartPage.setCheckItemName2();
        if (expectedResults == monitor) {
            expectedResults = cartPage.setCheckItemPrice2();
            actualResults = "400";
            Assert.assertTrue(expectedResults.contains(actualResults));
            Assert.assertTrue(expectedResults.contains(actualResults));
        } else if (expectedResults == laptop) {
            Assert.assertTrue(expectedResults.contains(laptop));
            expectedResults = cartPage.setCheckItemPrice1();
            actualResults = "790";
            Assert.assertTrue(expectedResults.contains(actualResults));
        }
        //actualResults = monitor;
        //Assert.assertTrue(expectedResults.contains(actualResults));

//        expectedResults = cartPage.setCheckItemPrice1();
//        actualResults = "790";
//        Assert.assertTrue(expectedResults.contains(actualResults));

//        expectedResults = cartPage.setCheckItemPrice2();
//        actualResults = "400";
//        Assert.assertTrue(expectedResults.contains(actualResults));

        int itemPrice1 = Integer.parseInt(cartPage.setCheckItemPrice1());
        int itemPrice2 = Integer.parseInt(cartPage.setCheckItemPrice2());
        int totalPrice = Integer.parseInt(cartPage.setTotalPrice());

        int sumOfTwoProducts = itemPrice1 + itemPrice2;
        Assert.assertEquals(totalPrice, sumOfTwoProducts);
        Thread.sleep(2000);

        cartPage.onClickPlaceOrder();

        //CheckOut
    }
}
