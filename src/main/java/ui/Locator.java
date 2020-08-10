package ui;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;

public class Locator {

    public static Properties locators=new Properties();

    public Locator() throws IOException {
        locators.load(getClass()
                .getClassLoader()
                .getResourceAsStream("weather.properties"));

    }

    public WebElement find(WebDriver driver,String locator_name,String value)
    {
        WebElement element =null;
        String locator = locators.getProperty("locator_name");
        String locator_type=locator.split("__")[0];
        String locator_value= String.format(locator.split("__")[1],value);

        switch(locator_type.toUpperCase())
        {
            case "XPATH":
                element=driver.findElement(By.xpath(locator_value));
                break;
            case "ID":
                element=driver.findElement(By.id(locator_value));
                break;
            case "CLASSNAME":
                element=driver.findElement(By.className(locator_value));
                break;
            case "CSSSELECTOR":
                element=driver.findElement(By.cssSelector(locator_value));
                break;
            case "LINKTEXT":
                element=driver.findElement(By.linkText(locator_value));
                break;
            case "PARTIALLINKTEXT":
                element=driver.findElement(By.partialLinkText(locator_value));
                break;
            case "TAGNAME":
                element=driver.findElement(By.tagName(locator_value));
                break;
            default: break;
        }
        return element;
    }

    public void click(WebDriver driver, WebElement element)
    {
        WebDriverWait wt=new WebDriverWait(driver,5);
        wt.until( ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void enterText(WebDriver driver, WebElement element,String text)
    {
        WebDriverWait wt=new WebDriverWait(driver,5);
        wt.until( ExpectedConditions.visibilityOf(element));
        element.click();
        element.sendKeys(text);
    }

    public String getText (WebDriver driver, WebElement element)
    {
        WebDriverWait wt=new WebDriverWait(driver,5);
        wt.until( ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}
