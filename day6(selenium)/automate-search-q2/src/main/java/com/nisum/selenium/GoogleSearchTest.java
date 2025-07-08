package com.nisum.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class GoogleSearchTest {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.google.com");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

            try {
                WebElement agreeButton = driver.findElement(By.xpath("//div[text()='I agree' or text()='Accept all']"));
                agreeButton.click();
            } catch (Exception ignored) {}

            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("BDD in Selenium");
            String query = "BDD in Selenium";
            searchBox.submit();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            boolean resultsDisplayed = driver.findElements(By.cssSelector("div.g")).size() > 0;

            if (resultsDisplayed) {
                System.out.println("Search results are displayed.");
            } else {
                System.out.println("No search results found.");
            }
        } finally {
            driver.quit();
        }
    }
}
