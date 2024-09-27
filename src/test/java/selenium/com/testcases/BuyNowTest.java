package selenium.com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.com.base.BaseSetUp;
import selenium.com.pages.BuyNowPage;
import selenium.com.pages.LoginPageV1;

public class BuyNowTest extends BaseSetUp {
    private BuyNowPage buyNowPage;
    private LoginPageV1 loginPageV1;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        loginPageV1 = new LoginPageV1(driver);
        buyNowPage = new BuyNowPage(driver);
        loginPageV1.loginClothingStore("thuy", "12345");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
    }

    @Test(priority = 1)
    public void buy2Pay1Test() throws InterruptedException {
        buyNowPage.checkBuy2Pay1(true);
    }

    @Test(priority = 2)
    public void buy3Pay2Test() {
        buyNowPage.checkBuy3Pay2(true);
    }

    @Test(priority = 3)
    public void buy2Pay1LessThan2Test() {
        buyNowPage.checkBuy2Pay1LessThan2();
    }

    @Test(priority = 4)
    public void buy3Pay2LessThan3Test() {
        buyNowPage.checkBuy3Pay2LessThan3();
    }

    @Test(priority = 5)
    public void noPromotionTest() {
        buyNowPage.checkNoPromotion(true);
    }


}
