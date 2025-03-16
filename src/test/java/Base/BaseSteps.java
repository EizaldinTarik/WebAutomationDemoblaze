package Base;

import Pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BaseSteps {
    public WebDriver driver;
    public HomePage homePage;


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        openHome();
        homePage = new HomePage(driver);
    }

    @BeforeMethod
    public void openHome() {
        driver.get("https://demoblaze.com/");
    }

    @AfterClass
    public void closeBrowser() {
        driver.quit();
    }
}
