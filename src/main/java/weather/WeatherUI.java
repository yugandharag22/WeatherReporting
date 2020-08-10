package weather;

import enums.BROWSER;
import org.openqa.selenium.WebDriver;
import ui.Configuration;
import ui.Locator;

import java.io.IOException;
import java.util.Properties;

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

        //Step 1: Navigate to https://www.ndtv.com/
        driver.get(BASE_URL);
        //Step 2: Navigate to Weather section
        locator.click(driver,locator.find(driver,"expandSubMenuBtn",""));
        locator.click(driver,locator.find(driver,"weatherSubMenuBtn",""));
        //Step 3: Enter city & select the checkbox
        locator.enterText(driver,locator.find(driver,"cityTxtBx",""),city);
        locator.click(driver,locator.find(driver,"cityChkBx",city));
        //Step 4: Click on MapView of selected city
        locator.click(driver,locator.find(driver,"cityMapViewBtn",""));
        //Step 5: Get temperature & humidity
        String temp=locator.getText(driver,locator.find(driver,"tempLbl",""));
        String humidity=locator.getText(driver,locator.find(driver,"humidityLbl",""));

        weather_obj.setTemp(Double.parseDouble(temp));
        weather_obj.setHumidity(Double.parseDouble(humidity));

        return weather_obj;

    }
}
