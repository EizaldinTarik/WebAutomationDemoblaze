package Pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage {
    WebDriver driver;
    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }
    //Locators
    private By usernameBox = By.id("sign-username");
    private By passwordBox = By.id("sign-password");
    private By registerButton = By.xpath("//button[@onclick = \"register()\"]");
    //Actions
    public void insertUsername(String username) {
        driver.findElement(usernameBox).sendKeys(username);
    }
    public void insertPassword(String password) {
        driver.findElement(passwordBox).sendKeys(password);
    }
    public void onClickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    public String getSignUpAlertMessage() {
        String text = driver.switchTo().alert().getText();
        return text;
    }
    public void acceptSignUpAlertMessage() throws InterruptedException {
        driver.switchTo().alert().dismiss();
    }

}
