package ui;

import enums.BROWSER;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Configuration {

    private static WebDriver driver;


    public static WebDriver getDriver(BROWSER browser) throws Exception {
        switch(browser)
        {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                driver= new ChromeDriver();
                break;
            case IE:
                driver= new InternetExplorerDriver();
                break;
            case FIREFOX:
                driver= new FirefoxDriver();
                break;
            default:
                throw new Exception("Unknown Browser type");

        }
        return driver;
    }
}
