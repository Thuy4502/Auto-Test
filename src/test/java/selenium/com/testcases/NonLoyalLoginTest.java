package selenium.com.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.com.base.BaseSetUp;
import selenium.com.pages.BuyNowPage;
import selenium.com.pages.LoginPageV1;

public class NonLoyalLoginTest extends BaseSetUp {
    public BuyNowPage buyNowPage;
    private LoginPageV1 loginPageV1;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        loginPageV1 = new LoginPageV1(driver);
        buyNowPage = new BuyNowPage(driver);
        loginPageV1.loginClothingStore("annhien", "123456");
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(10000);
    }

    @Test(priority = 1)
    public void nonLoyalBuy2Pay1Test() {
        buyNowPage.checkBuy2Pay1(false);
    }

    @Test(priority = 2)
    public void nonLoyalBuy3Pay2Test() {
        buyNowPage.checkBuy3Pay2(false);
    }

    @Test(priority = 3)
    public void nonLoyalNoPromotionTest() {
        buyNowPage.checkNoPromotion(false);
    }

    @Test(priority = 4)
    public void nonLoyalBuy2Pay1TestLessThan2() {
        buyNowPage.checkBuy2Pay1LessThan2();
    }



}
