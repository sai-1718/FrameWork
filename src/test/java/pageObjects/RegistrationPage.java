package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage extends BasePage
{
    public RegistrationPage(WebDriver driver)
    {
        super(driver);
    }

    ///////////   elements
    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement phone;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confpassword;
    @FindBy(xpath = "//label[normalize-space()='Yes']")
    WebElement subscribeYes;
    @FindBy(xpath = "//input[@value='0']")
    WebElement subscribeNo;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement agree;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement submit;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement confirmMsg;


    //////////// Action Methods
    public void setFirstName(String firstname)
    {
        firstName.sendKeys(firstname);
    }
    public void setLastName(String lastname)
    {
        lastName.sendKeys(lastname);
    }
    public void setEmail(String mail)
    {
        email.sendKeys(mail);
    }
    public void setPhone(String phnNo)
    {
        phone.sendKeys(phnNo);
    }
    public void setPassword(String pass)
    {
        password.sendKeys(pass);
    }
    public void setConfpassword(String confPass)
    {
        confpassword.sendKeys(confPass);
    }
    public void clickSubscribeYes()
    {
       // subscribe.click();
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",subscribeYes);
    }
    public void clickSubscribeNo()
    {
        // subscribe.click();
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",subscribeNo);
    }
    public void clickAgree()
    {
        //agree.click();
        Actions act=new Actions(driver);
        act.moveToElement(agree).click().perform();
    }
    public void clickSubmit()
    {
        //submit.click();
        submit.submit();

//        Actions act=new Actions(driver);
//        act.moveToElement(submit).click().perform();
//
//        JavascriptExecutor js=(JavascriptExecutor) driver;
//        js.executeScript("arguments[0].click();",submit);
//
//        submit.sendKeys(Keys.RETURN);
//
//        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.elementToBeClickable(submit)).click();

    }
    public String msgConfirm()
    {
        try {
            return  confirmMsg.getText();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
