package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LogInPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC003_LoginDDT extends BaseClass
{
    @Test(dataProvider ="loginData",dataProviderClass =DataProviders.class)
    public void VerifyLogin(String mail,String pwd,String exres)
    {
         HomePage hp=new HomePage(driver);
         hp.clickMyAccount();
         hp.clickLogin();

         LogInPage lp=new LogInPage(driver);
         lp.InputMail(mail);
         lp.InputPass(pwd);
         lp.clickSubmit();

         MyAccountPage mac=new MyAccountPage(driver);
         boolean msg=mac.myAccMsgIsDisplayed();
         if (exres.equalsIgnoreCase("valid"))
         {
             if (msg)
             {
                 mac.clickLogout();
                 Assert.assertTrue(true);
             }
             else
             {
                 lp.tryAgain();
                 Assert.fail();
             }
         }
       else
        {
            if (msg)
            {
                mac.clickLogout();
                Assert.fail();
            }
            else
            {
                lp.tryAgain();
                Assert.assertTrue(true);
            }
        }



































//                                                                                logger.info("====== Started TC002_UserLogin Execution =========");
//        try {
//                                                                                logger.info("====== This is Home Page =========");
//            HomePage hp=new HomePage(driver);
//            hp.clickMyAccount();
//                                                                                logger.info("====== clicked my account =========");
//            hp.clickLogin();
//                                                                                 logger.info("====== clicked on login =========");
//
//                                                                                 logger.info("====== ---> This is Login Page =========");
//            LogInPage login=new LogInPage(driver);
//            login.InputMail(mail);
//                                                                                logger.info("====== email is entered =========");
//            login.InputPass(pwd);
//                                                                                 logger.info("====== password is entered =========");
//            login.clickSubmit();
//                                                                                 logger.info("====== clicked submit =========");
//
//
//            //alert().accept();
//                                                                                    logger.info("====== ---> This is MyAccount  Page =========");
//            MyAccountPage mAP=new MyAccountPage(driver);
//            boolean Myaccount=mAP.myAccMsgIsDisplayed();
//
//
//            if (exres.equalsIgnoreCase("valid"))
//            {
//                if (Myaccount)
//                {
//                                                                                    logger.info("====== ---> This is MyAccount  Page =========");
//                    mAP.clickLogout();
//                                                                                    logger.info("@@@@@@== valid password login success --PASS-- ==@@@@@@");
//                    mAP.clickSubmit();
//                    Assert.assertTrue(true);
//                }
//                else
//                {
//                                                                                    logger.info("@@@@@@== valid password not logged in  --FAIL-- ==@@@@@@");
//                    login.tryAgain();
//                    Assert.fail();
//                }
//            }
//            if (exres.equalsIgnoreCase("invalid"))
//            {
//                if (Myaccount)
//                {
//                                                                                    logger.info("====== ---> This is MyAccount Page =========");
//                    mAP.clickLogout();
//                    mAP.clickSubmit();
//                                                                                    logger.info("@@@@@@== invalid password login success --FAIL--==@@@@@@");
//                    Assert.fail();
//                }
//                else
//                {
//
//                                                                                    logger.info("@@@@@@== invalid password login fail --PASS--==@@@@@@");
//                    login.tryAgain();
//                    Assert.assertTrue(true);
//                }
//            }
//        } catch (Exception e) {
//                                                                                    logger.fatal(" *** Exception occurred ****");
//                System.out.println( e.getMessage());
//            Assert.fail();
//        }
//

    }
}
