package selenium.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class BuyNowPage {
    private WebDriver driver;
    private By productsHeader = By.xpath("//a[normalize-space()='PRODUCTS']");
    private By productBuy2PayFor1 = By.xpath("//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/main[1]/section[1]/div[1]/div[1]/div[1]/div[1]/div[1]/img[1]");
    private By productBuy3PayFor2 = By.xpath("//div[3]//div[1]//img[1]");
    private By sizeM = By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/section[1]/div[2]/div[2]/form[1]/div[1]/fieldset[1]/div[1]/span[2]");
    private By btnBuyNow = By.xpath("//button[normalize-space()='Buy now']");
    private By txtPrice = By.xpath("//p[@class='font-bold ml-2']");
    private By txtQuantity = By.xpath("//span[@class='px-4 border rounded-sm']");
    private By txtTotalAmount = By.id("totalAmount");
    private By noPromotion = By.xpath("//div//div//div//div//div[2]//div[1]//img[1]");
    private By plusBtn = By.xpath("//*[name()='path' and contains(@d,'M13 7h-2v4')]");

    private Double price = 0.0;
    private int quantity = 0;
    private Double discountedTotal = 0.0;
    private Double originPrice = 0.0;
    private Double totalAmount = 0.0;

    public BuyNowPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkBuy2Pay1LessThan2() {
        waitForPageLoaded();
        if(!driver.findElement(productsHeader).isDisplayed()) {
            driver.navigate().forward();
        }
        driver.findElement(productsHeader).click();
        waitForPageLoaded();
        driver.findElement(productBuy2PayFor1).click();
        waitForPageLoaded();
        driver.findElement(sizeM).click();
        driver.findElement(btnBuyNow).click();
        price = Double.parseDouble(driver.findElement(txtPrice).getText().replace("$", "").trim());
        quantity = Integer.parseInt(driver.findElement(txtQuantity).getText());
        totalAmount = Double.parseDouble(driver.findElement(txtTotalAmount).getText().replace("$", "").trim());
        discountedTotal = price * quantity;
        discountedTotal = discountedTotal * (1 - 0.1);
        System.out.println("Mua 2 tính tiền 1, SL < 2 giá thực tế:  " +  totalAmount);
        System.out.println("Mua 2 tính tiền 1, SL < 2 mong đợi: " + discountedTotal);
        Assert.assertEquals(totalAmount, discountedTotal);
    }

    public void checkBuy2Pay1(Boolean isLoyal) {
        waitForPageLoaded();
        if(!driver.findElement(productsHeader).isDisplayed()) {
            driver.navigate().forward();
        }
        driver.findElement(productsHeader).click();
        waitForPageLoaded();
        driver.findElement(productBuy2PayFor1).click();
        waitForPageLoaded();
        driver.findElement(sizeM).click();
        driver.findElement(btnBuyNow).click();
        driver.findElement(plusBtn).click();
        double discountedTotal = 0.0;
        price = Double.parseDouble(driver.findElement(txtPrice).getText().replace("$", "").trim());
        quantity = Integer.parseInt(driver.findElement(txtQuantity).getText());
        totalAmount = Double.parseDouble(driver.findElement(txtTotalAmount).getText().replace("$", "").trim());
        discountedTotal = Math.ceil((double) quantity / 2) * price;
        if(isLoyal) {
            discountedTotal = discountedTotal * (1 - 0.1);
        }
        Assert.assertEquals(totalAmount, discountedTotal,0.01);
        System.out.println("Mua 2 tính tiền 1, SL >= 2 giá thực tế:  " +  totalAmount);
        System.out.println("Mua 2 tính tiền 1, SL >= 2 mong đợi: " + discountedTotal);
    }

    public void checkBuy3Pay2LessThan3() {
        waitForPageLoaded();
        if(!driver.findElement(productsHeader).isDisplayed()) {
            driver.navigate().forward();
        }
        driver.findElement(productsHeader).click();
        waitForPageLoaded();
        driver.findElement(productBuy3PayFor2).click();
        waitForPageLoaded();
        driver.findElement(sizeM).click();
        driver.findElement(btnBuyNow).click();
        driver.findElement(plusBtn).click();

        double discountedTotal = 0.0;
        price = Double.parseDouble(driver.findElement(txtPrice).getText().replace("$", "").trim());
        quantity = Integer.parseInt(driver.findElement(txtQuantity).getText());
        totalAmount = Double.parseDouble(driver.findElement(txtTotalAmount).getText().replace("$", "").trim());
        discountedTotal = price * quantity;
        discountedTotal = discountedTotal * (1 - 0.1);
        System.out.println("Mua 3 tính tiền 2 giá thực tế:  " +  totalAmount);
        System.out.println("Mua 3 tính tiền 2 giá mong đợi: " + discountedTotal);
        Assert.assertEquals(totalAmount, discountedTotal,0.01);

    }

    public void checkBuy3Pay2(Boolean isLoyal) {
        waitForPageLoaded();
        if(!driver.findElement(productsHeader).isDisplayed()) {
            driver.navigate().forward();
        }
        driver.findElement(productsHeader).click();
        waitForPageLoaded();
        driver.findElement(productBuy3PayFor2).click();
        waitForPageLoaded();
        driver.findElement(sizeM).click();
        driver.findElement(btnBuyNow).click();
        driver.findElement(plusBtn).click();
        driver.findElement(plusBtn).click();

        double discountedTotal = 0.0;
        price = Double.parseDouble(driver.findElement(txtPrice).getText().replace("$", "").trim());
        quantity = Integer.parseInt(driver.findElement(txtQuantity).getText());
        totalAmount = Double.parseDouble(driver.findElement(txtTotalAmount).getText().replace("$", "").trim());
        discountedTotal = ((int)(quantity / 3)*2 + (quantity % 3)) * price;
        if(isLoyal) {
            discountedTotal = discountedTotal * (1 - 0.1);
        }
        Assert.assertEquals(totalAmount, discountedTotal, 0.01);
        System.out.println("Sản phẩm có khuyến mãi mua 3 tính tiền 2, giá thực tế:  " +  totalAmount);
        System.out.println("Sản phẩm có khuyến mãi mua 3 tính tiền 2, giá mong đợi: " + discountedTotal);
    }



    public void checkNoPromotion(Boolean isLoyal) {
        if(!driver.findElement(productsHeader).isDisplayed()) {
            driver.navigate().forward();
        }
        driver.findElement(productsHeader).click();
        waitForPageLoaded();
        driver.findElement(noPromotion).click();
        waitForPageLoaded();
        driver.findElement(sizeM).click();
        driver.findElement(btnBuyNow).click();
        double discountedTotal = 0.0;
        price = Double.parseDouble(driver.findElement(txtPrice).getText().replace("$", "").trim());
        quantity = Integer.parseInt(driver.findElement(txtQuantity).getText());
        totalAmount = Double.parseDouble(driver.findElement(txtTotalAmount).getText().replace("$", "").trim());
        if(isLoyal) {
            discountedTotal = price*quantity * (1 - 0.1);
        }
        else {
            discountedTotal = price*quantity;
        }
        Assert.assertEquals(totalAmount, discountedTotal,0.01);
        System.out.println("Sản phẩm không có khuyến mãi giá thực tế:  " +  totalAmount);
        System.out.println("Sản phẩm không có khuyến mãi giá mong đợi: " + discountedTotal);

    }


    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");

                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(expectation);
        } catch (InterruptedException e) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");

        }
    }



}
