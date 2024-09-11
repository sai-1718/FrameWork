package testBase;

import org.apache.commons.lang3.RandomStringUtils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import pageObjects.BasePage;

public class BaseClass {
    public static WebDriver driver;
    public Logger logger;
    public Properties properties;


    @BeforeClass(groups = {"sanity", "regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String ops, String bros) throws IOException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\saida\\CODING\\IntelliJIDE\\SELENIUM Workspace\\Drivers Folder\\chromedriver.exe");
        System.setProperty("webdriver.edge.driver", "C:\\Users\\saida\\CODING\\IntelliJIDE\\SELENIUM Workspace\\Drivers Folder\\msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\saida\\CODING\\IntelliJIDE\\SELENIUM Workspace\\Drivers Folder\\geckodriver.exe");

        ///////////// loading properties file config.properties
        FileReader file = new FileReader("src\\test\\resources\\cofig.properties");
        properties = new Properties();
        properties.load(file);
        ///// log manager
        logger = LogManager.getLogger(this.getClass());
        if (properties.getProperty("execution_env").equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            //ImmutableCapabilities capabilities = new ImmutableCapabilities();

            if (ops.equalsIgnoreCase("windows")) {
                capabilities.setPlatform(Platform.WIN11);
            } else if (ops.equalsIgnoreCase("mac")) {
                capabilities.setPlatform(Platform.MAC);
            } else if (ops.equalsIgnoreCase("linux")) {
                capabilities.setPlatform(Platform.LINUX);
            } else {
                System.out.println("no matched os");
            }

            switch (bros.toLowerCase()) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("edge");
                    break;
                case "firefox":
                    capabilities.setBrowserName("firefox");
                default:
                    System.out.println("no such browser..");
                    return;
            }
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        }
        if (properties.getProperty("execution_env").equals("local")) {
            //////// selecting browser from xml file by passing parameter
            switch (bros.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                default:
                    System.out.println("Invalid browser....");
                    return;
            }
        }

        driver.manage().deleteAllCookies();
        //driver.get("http://localhost/opencart/opencart/upload/");
        driver.get(properties.getProperty("tninja"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
    }

    @AfterClass(groups = {"sanity", "regression", "Master"})
    public void tearDown() {
        driver.quit();
    }


    public String randomNum() {
        return RandomStringUtils.randomNumeric(5);
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomAlpNum() {
        return RandomStringUtils.randomAlphanumeric(6);
    }

    public Alert alert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyMMddhhmmss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}