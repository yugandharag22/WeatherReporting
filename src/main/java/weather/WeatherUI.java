package weather;

import enums.BROWSER;
import org.openqa.selenium.WebDriver;
import ui.Configuration;
import ui.Locator;
import utils.LogUtils;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class WeatherUI {

    private Locator locator;
    private WebDriver driver;
    private final String BASE_URL="https://www.ndtv.com/";
    private Properties locators;

    public WeatherUI() throws IOException {
        locator=new Locator();

    }

    public WeatherPOJO fetchWeatherData(String city) throws Exception {

        WeatherPOJO weather_obj=new WeatherPOJO();

        LogUtils.debug("Initializing webdriver");
        driver= Configuration.getDriver(BROWSER.CHROME);

        LogUtils.debug("Maximizing the window");
        driver.manage().window().maximize();

        LogUtils.debug("Setting implicit timeout of 10 seconds");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try
        {
            //Step 1: Navigate to https://www.ndtv.com/
            LogUtils.debug("Navigate to https://www.ndtv.com/");
            driver.get(BASE_URL);

            //Step 2: Navigate to Weather section
            LogUtils.debug("Navigate to Weather section");
            locator.click(driver,locator.find(driver,"expandSubMenuBtn",""));
            locator.click(driver,locator.find(driver,"weatherSubMenuBtn",""));

            //Step 3: Enter city & select the checkbox
            LogUtils.debug("Enter city & select the checkbox");
            locator.enterText(driver,locator.find(driver,"cityTxtBx",""),city);
            locator.check(driver,locator.find(driver,"cityChkBx",city));

            //Step 4: Click on MapView of selected city
            LogUtils.debug("Click on MapView of selected city");
            locator.click(driver,locator.find(driver,"cityMapViewBtn",city));

            //Step 5: Get temperature & humidity
            LogUtils.debug("Get temperature & humidity");
            String temp=locator.getText(driver,locator.find(driver,"tempLbl",""));
            String humidity=locator.getText(driver,locator.find(driver,"humidityLbl",""));

            //Step 6: Creating new weather object
            LogUtils.debug("Creating new weather object");
            weather_obj.setTemp(Double.parseDouble(temp.substring(temp.length()-2,temp.length())));
            weather_obj.setHumidity(Double.parseDouble(humidity.substring(humidity.length()-3,humidity.length()-1)));
            LogUtils.debug("Closing webdriver");
            driver.quit();
        }
        catch(Exception e)
        {
            LogUtils.error("Exception occurred: "+e.getMessage(),e);
            driver.quit();
        }

        return weather_obj;

    }
}
