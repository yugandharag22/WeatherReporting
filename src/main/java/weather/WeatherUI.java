package weather;

import enums.BROWSER;
import org.openqa.selenium.WebDriver;
import ui.Configuration;
import ui.Locator;

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

        driver= Configuration.getDriver(BROWSER.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        try
        {
            //Step 1: Navigate to https://www.ndtv.com/
            driver.get(BASE_URL);
            //Step 2: Navigate to Weather section
            locator.click(driver,locator.find(driver,"expandSubMenuBtn",""));
            locator.click(driver,locator.find(driver,"weatherSubMenuBtn",""));
            //Step 3: Enter city & select the checkbox
            locator.enterText(driver,locator.find(driver,"cityTxtBx",""),city);
            locator.check(driver,locator.find(driver,"cityChkBx",city));
            //Step 4: Click on MapView of selected city
            locator.click(driver,locator.find(driver,"cityMapViewBtn",city));
            //Step 5: Get temperature & humidity
            String temp=locator.getText(driver,locator.find(driver,"tempLbl",""));
            String humidity=locator.getText(driver,locator.find(driver,"humidityLbl",""));

            weather_obj.setTemp(Double.parseDouble(temp.substring(temp.length()-2,temp.length())));
            weather_obj.setHumidity(Double.parseDouble(humidity.substring(humidity.length()-3,humidity.length()-1)));
            driver.quit();
        }
        catch(Exception e)
        {
            driver.quit();
            e.printStackTrace();
        }

        return weather_obj;

    }
}
