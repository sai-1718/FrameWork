package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_UserLogin extends BaseClass
{

    @Test(groups = {"regression","Master"})
    public  void userLogin()
    {
        try {
            logger.info("====== Started TC002_UserLogin Execution =========");

            ///////////  Home Page  //////////////

            logger.info("====== This is Home Page =========");
            HomePage hp=new HomePage(driver);
            hp.clickMyAccount();
            logger.info("====== clicked my account =========");
            hp.clickLogin();
            logger.info("====== clicked on login =========");

            logger.info("====== ---> This is Login Page =========");
            LogInPage login=new LogInPage(driver);
            login.InputMail(properties.getProperty("mail"));
            logger.info("====== email is entered =========");
            login.InputPass(properties.getProperty("password"));
            logger.info("====== password is entered =========");
            login.clickSubmit();
            logger.info("====== clicked submit =========");


            //alert().accept();
            logger.info("====== ---> This is MyAccount  Page =========");
            MyAccountPage mAP=new MyAccountPage(driver);
            boolean Myaccount=mAP.myAccMsgIsDisplayed();
            Assert.assertTrue(Myaccount);

        } catch (Exception e) {
            logger.error("********** Test case Failed ***********");
            logger.debug("********** Test case debugging ***********");
            e.printStackTrace();
            Assert.fail();
        }

        logger.info("====== TC002_UserLogin Execution completed =======");
    }

}
