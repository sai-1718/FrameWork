package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

public class TC001_UserRegistration extends BaseClass
{
    @Test(groups = {"sanity","Master"})
    public void registerUser()
    {
        try {
            logger.info("****** Starting TC001_UserRegistration Execution ***");
            HomePage homePage=new HomePage(driver);
            homePage.clickMyAccount();
            logger.info(" Clicked on MyAccount");
            homePage.clickRegister();
            logger.info(" Clicked on Register");
            RegistrationPage regPage=new RegistrationPage(driver);
            logger.info(" *** Entering details ***");
            regPage.setFirstName(randomString());
            regPage.setLastName(randomString());
            regPage.setEmail(randomString()+"@gmail.com");
            regPage.setPhone(randomNum()+randomNum());
            String pass=randomAlpNum();
            regPage.setPassword(pass);
            regPage.setConfpassword(pass);
            regPage.clickSubscribeYes();
            regPage.clickAgree();
            regPage.clickSubmit();
            String msg=regPage.msgConfirm();
            logger.info("==== *** Validating  Message ***");
            if (msg.equals("Your Account Has Been Created!"))
            {
                Assert.assertTrue(true);
            }
            else
            {
                logger.error("-----Test case failed ");
                logger.debug("debugggg/////////");
                Assert.assertTrue(false);
            }

        } catch (Exception e)
        {
          Assert.fail();
          e.printStackTrace();
        }

        logger.info("****** Completed TC001_UserRegistration Execution ***");
    }

}
