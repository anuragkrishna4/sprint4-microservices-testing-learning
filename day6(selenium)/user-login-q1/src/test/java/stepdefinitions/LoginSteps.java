package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;

import static org.junit.Assert.*;

public class LoginSteps {

    private WebDriver driver;


    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Given("the user is on the login page")
    public void openLoginPage() {
        File file = new File("src/main/resources/html/login.html");
        driver.get("file:///" + file.getAbsolutePath());
    }

    @When("the user enters username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

        WebElement userField = driver.findElement(By.id("username"));
        WebElement passField = driver.findElement(By.id("password"));

        userField.clear();
        passField.clear();

        userField.sendKeys(username);
        passField.sendKeys(password);
    }

    @When("clicks the login button")
    public void clickLogin() {
        driver.findElement(By.id("loginBtn")).click();
    }

    @Then("the user should be redirected to the dashboard")
    public void verifyDashboard() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/dashboard"));

        assertTrue("User not redirected to dashboard",
                driver.getCurrentUrl().contains("/dashboard"));
    }

    @Then("an error message {string} should be displayed")
    public void verifyErrorMessage(String expected) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")));

        String actual = driver.findElement(By.id("error")).getText();
        assertEquals(expected, actual);
    }
}
