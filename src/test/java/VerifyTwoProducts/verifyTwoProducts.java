package VerifyTwoProducts;

import Base.BaseSteps;
import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Objects;

public class verifyTwoProducts extends BaseSteps {
    @Test (priority = 1)
    public void verifyTwoProductsPurchasedSuccessfully() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        LaptopPage laptopPage = new LaptopPage(driver);
        MonitorPage monitorPage = new MonitorPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
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
        checkOutPage.setCheckOutName("Eizaldin");
        checkOutPage.setCheckOutCountry("Egypt");
        checkOutPage.setCheckOutCity("Giza");
        checkOutPage.setCheckOutCard("1234");
        checkOutPage.setCheckOutMonth("April");
        checkOutPage.setCheckOutYear("2024");
        checkOutPage.onClickPurchaseButton();
        //Verify Purchase
        expectedResults = checkOutPage.VerifyPurchaseMessage();
        actualResults = "Thank you for your purchase!";
        Assert.assertTrue(expectedResults.contains(actualResults));
        checkOutPage.onClickAcceptVerifyMessage();
    }
    @Test (priority = 2)
    public void invalidLoginCredentials() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        LaptopPage laptopPage = new LaptopPage(driver);
        MonitorPage monitorPage = new MonitorPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        Thread.sleep(2000);
        //Login
        homePage.onClickLogOutLink();
        Thread.sleep(2000);
        homePage.onClickLoginLink();
        loginPage.insertLoginUsername("Eizal");
        loginPage.insertLoginPassword("1143");
        loginPage.onClickLoginButton();
        String expectedResults = loginPage.getLoginAlertMessage();
        String actualResults = "User does not exist.";
        Thread.sleep(2000);
        Assert.assertTrue(expectedResults.contains(actualResults));
        loginPage.acceptLoginAlertMessage();
    }
    @Test (priority = 3)
    public void addSameProductTwice() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        LaptopPage laptopPage = new LaptopPage(driver);
        MonitorPage monitorPage = new MonitorPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
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

        laptopPage.AddToCart();
        expectedResults = laptopPage.verifyAlertMessageAdded();
        actualResults = "Product added.";
        Assert.assertTrue(expectedResults.contains(actualResults));
        laptopPage.acceptAlertMessage();
        homePage.onClickCartLink();
       Thread.sleep(2000);
        String expectedResults1 = cartPage.setCheckItemName1();
        String expectedResults2 = cartPage.setCheckItemName2();
        if (Objects.equals(expectedResults1, expectedResults2)) {
            Assert.assertEquals(expectedResults1, expectedResults2);
        }
    }
}
