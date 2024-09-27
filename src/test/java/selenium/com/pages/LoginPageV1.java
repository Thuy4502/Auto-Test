package selenium.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import selenium.com.base.ConfigData;

import java.time.Duration;

public class LoginPageV1 {
    private WebDriver driver;
    private WebDriverWait wait;

    private By headerPage = By.xpath("//button[normalize-space()='SIGNIN']");
    private By inputUsername = By.xpath("//input[@id='username']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By btnLogin = By.xpath("//button[normalize-space()='Login']");
    private By txtError = By.xpath("//p[@id='error']");
    private By txtUsername = By.xpath("//span[@id='username']");

    public LoginPageV1(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver cannot be null");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public  void clickLogin() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(headerPage));
        driver.findElement(headerPage).click();
    }

    public void setUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputUsername));
        driver.findElement(inputUsername).clear();
        driver.findElement(inputUsername).sendKeys(username);
    }

    public void setPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);

    }

    public void clickLoginButton() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnLogin));
        driver.findElement(btnLogin).click();


    }

    public void loginClothingStore(String username, String password) throws InterruptedException {
        driver.get(ConfigData.URL);
        clickLogin();
        setUsername(username);
        setPassword(password);
        Thread.sleep(3000);
        clickLoginButton();
    }

    public void checkEmptyUsername(String username, String password) throws InterruptedException {
        loginClothingStore(username, password);
        String actualError = driver.findElement(txtError).getText();
        Assert.assertEquals(actualError, "Username is required");
        System.out.println("Actual: " + actualError);
        System.out.println("Expected : " + "Username is required");

    }

    public void checkEmptyPassword(String username, String password) throws InterruptedException {
        loginClothingStore(username, password);
        String actualError = driver.findElement(txtError).getText();
        Assert.assertEquals(actualError, "Password is required");
        System.out.println("Actual: " + actualError);
        System.out.println("Expected : " + "Password is required");
    }

    public void checkUsernameNonExistent(String username, String password) throws InterruptedException {
        loginClothingStore(username, password);
        String actualError = driver.findElement(txtError).getText();
        Assert.assertEquals(actualError, "Username does not exist");
        System.out.println("Actual: " + actualError);
        System.out.println("Expected : " + "Username does not exist");
    }

    public void checkWrongPassword(String username, String password) throws InterruptedException {
        loginClothingStore(username, password);
        String actualError = driver.findElement(txtError).getText();
        Assert.assertEquals(actualError, "Password is incorrect for this username");
        System.out.println("Actual: " + actualError);
        System.out.println("Expected : " + "Password is incorrect for this username");
    }

    public void checkLoginSuccessful(String username, String password) throws InterruptedException {
        loginClothingStore(username, password);
        String actualUsername = driver.findElement(txtUsername).getText();
        Assert.assertEquals(actualUsername, username);
        System.out.println("Actual: " + actualUsername);
        System.out.println("Expected : " + username);
    }



}
