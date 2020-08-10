import org.testng.Assert;
import org.testng.annotations.Test;
import weather.Variance;
import weather.WeatherAPI;
import weather.WeatherPOJO;
import weather.WeatherUI;

public class CompareWeatherTest {

    @Test
    public void CompareTempHumidityTest() throws Exception {
        String city= System.getProperty("city");
        Variance.temp_range=Double.parseDouble(System.getProperty("temp_variance"));
        Variance.humidity_range=Double.parseDouble(System.getProperty("humidity_variance"));

        WeatherUI ui=new WeatherUI();
        WeatherAPI api= new WeatherAPI();

        WeatherPOJO ui_weather=ui.fetchWeatherData(city);
        WeatherPOJO api_weather=api.fetchWeatherData(city);
        System.out.println(String.format("UI result- Temp: %s, Humidity: %s",ui_weather.getTemp(),ui_weather.getHumidity()));
        System.out.println(String.format("API result- Temp: %s, Humidity: %s",api_weather.getTemp(),api_weather.getHumidity()));

        /* Here, assertEquals will call overriden equals method in WeatherPOJO class.
         Variance logic is plugged in the overridden equals() method of weather.WeatherPOJO */
       Assert.assertEquals(ui_weather,api_weather);

    }
}
