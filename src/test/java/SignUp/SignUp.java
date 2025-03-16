package SignUp;

import Base.BaseSteps;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.SignUpPage;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUp extends BaseSteps {
    Faker faker = new Faker();
    String fakeUsername = faker.name().username() + faker.number().digits(3);
    String fakePassword = faker.internet().password(8, 12, true, true, true);
    @Test (priority = 1)
    public void validSignUpTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        homePage.onClickSignUpLink();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signUpPage.insertUsername(fakeUsername);
        signUpPage.insertPassword(fakePassword);
        signUpPage.onClickRegisterButton();
        Thread.sleep(2000);
        String actualResults = signUpPage.getSignUpAlertMessage();
        String expectedResults = "Sign up successful.";
        Assert.assertTrue(actualResults.contains(expectedResults));
        Thread.sleep(2000);
        signUpPage.acceptSignUpAlertMessage();
    }

    @Test (priority = 2)
    public void invalidSignUpTest() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        SignUpPage signUpPage = new SignUpPage(driver);
        homePage.onClickSignUpLink();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        signUpPage.insertUsername("Eizaldint");
        signUpPage.insertPassword("11413");
        signUpPage.onClickRegisterButton();
        Thread.sleep(2000);
        String actualResults = signUpPage.getSignUpAlertMessage();
        String expectedResults = "This user already exist.";
        Assert.assertTrue(actualResults.contains(expectedResults));
        Thread.sleep(2000);
        signUpPage.acceptSignUpAlertMessage();
    }
}
