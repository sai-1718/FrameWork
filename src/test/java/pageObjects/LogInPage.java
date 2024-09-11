package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage
{
   public LogInPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement mail;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement pass;
    @FindBy(xpath = "//input[@value='Login']")
    WebElement sbtn;
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Login']")
    WebElement relogin;


    public void InputMail(String email)
    {
        mail.sendKeys(email);
    }
   public void InputPass(String password)
   {
       pass.sendKeys(password);
   }
    public void clickSubmit()
    {
        sbtn.sendKeys(Keys.RETURN);
//        JavascriptExecutor js=(JavascriptExecutor)driver;
//        js.executeAsyncScript("arguments[0].click();",sbtn);
    }
    public void tryAgain()
    {
        relogin.click();
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeAsyncScript("arguments[0].click();",relogin);
    }

}
