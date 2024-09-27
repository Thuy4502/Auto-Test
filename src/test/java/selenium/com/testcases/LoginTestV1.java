package selenium.com.testcases;

import org.example.helpers.ExcelHelpers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import selenium.com.base.BaseSetUp;
import selenium.com.pages.LoginPageV1;

public class LoginTestV1 extends BaseSetUp {
    LoginPageV1 loginPage;
    ExcelHelpers excel = new ExcelHelpers();

    @BeforeMethod
    public void setUp() throws InterruptedException {
        loginPage = new LoginPageV1(driver);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
    }

    @Test(priority = 1)
    public void emptyUsernameTest() throws InterruptedException {
        excel.setExcelFile("src/test/resource/Book1.xlsx", "Sheet1");
        loginPage.checkEmptyUsername(excel.getCellData("username", 2), excel.getCellData("password", 2));
    }

    @Test(priority = 2)
    public void emptyPasswordTest() throws InterruptedException {
        loginPage.checkEmptyPassword("thuy", "");
    }

    @Test(priority = 3)
    public void usernameNonExistTest() throws InterruptedException {
        loginPage.checkUsernameNonExistent("ttttt", "12345");
    }

    @Test(priority = 4)
    public void wrongPasswordTest() throws InterruptedException {
        loginPage.checkWrongPassword("thuy", "11111");
    }

    @Test(priority = 3)
    public void loginSuccessfulTest() throws InterruptedException {
        excel.setExcelFile("src/test/resource/Book1.xlsx", "Sheet1");
        loginPage.checkLoginSuccessful(excel.getCellData("username", 1), excel.getCellData("password", 1));
        excel.setCellData("pass", 3,1);
    }

    @Test(priority = 4)
    public void signInWithExcelFileTest() throws InterruptedException {
        excel.setExcelFile("src/test/resource/Book1.xlsx", "Sheet1");
        
        for(int i = 0; i <= 2; i++) {
            loginPage.checkLoginSuccessful(excel.getCellData("username", i), excel.getCellData("password", i));
            Thread.sleep(1000);
        }
    }






}
