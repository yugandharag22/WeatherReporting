import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.LogUtils;
import weather.Variance;
import weather.WeatherAPI;
import weather.WeatherPOJO;
import weather.WeatherUI;

@Epic("Weather Reporting")
@Feature("Compare Weather Reports")
public class CompareWeatherTest {

    @Test(description = "Compare weather data gathered from website UI & API")
    public void CompareTempHumidityTest() throws Exception {

        String city= System.getProperty("city");
        LogUtils.info("When city name is "+city);
        Variance.temp_range=Double.parseDouble(System.getProperty("temp_variance"));
        LogUtils.info("When temperature variance is "+Variance.temp_range);
        Variance.humidity_range=Double.parseDouble(System.getProperty("humidity_variance"));
        LogUtils.info("When humidity variance is "+Variance.humidity_range);

        WeatherUI ui=new WeatherUI();
        WeatherAPI api= new WeatherAPI();

        WeatherPOJO ui_weather=ui.fetchWeatherData(city);
        LogUtils.info(String.format("Then actual values captured from UI are - Temperature : %s & Humidity: %s",ui_weather.getTemp(),ui_weather.getHumidity()));
        WeatherPOJO api_weather=api.fetchWeatherData(city);
        LogUtils.info(String.format("Then actual values captured from API are - Temperature : %s & Humidity: %s",api_weather.getTemp(),api_weather.getHumidity()));

        /* Here, assertEquals will call overriden equals method in WeatherPOJO class.
         Variance logic is plugged in the overridden equals() method of weather.WeatherPOJO */
       Assert.assertEquals(ui_weather,api_weather);


    }
}
