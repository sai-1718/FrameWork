package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage
{

    public MyAccountPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//h2[normalize-space()='My Account']")
    WebElement myAccMsg;
    //a[@class='list-group-item'][normalize-space()='Logout']
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logout;
    @FindBy(xpath = "//a[@class='btn btn-primary']")
    WebElement submit;




    public boolean myAccMsgIsDisplayed()
    {
        try {
            return myAccMsg.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout()
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeAsyncScript("arguments[0].click();",logout);

    }

    public void clickSubmit()
    {
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeAsyncScript("arguments[0].click();",submit);
    }

}
