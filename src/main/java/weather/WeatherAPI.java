package weather;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import utils.LogUtils;

public class WeatherAPI {

    private final String BASE_URL="http://api.openweathermap.org/data/2.5";
    private final String api_key="7fe67bf08c80ded756e598d6f8fedaea";


    public WeatherPOJO fetchWeatherData(String city)
    {
        WeatherPOJO weather_obj=new WeatherPOJO();
        LogUtils.debug("Hitting /weather request API");
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL+"/weather")
                .header("accept", "application/json")
                .queryString("appid",api_key)
                .queryString("q",city)
                .queryString("units","metric")
                .asJson();

        LogUtils.debug("API response is: "+response.getBody().toPrettyString());
        JSONObject obj = response.getBody().getObject();
        JSONObject main_obj= (JSONObject) obj.get("main");

        String temp= main_obj.get("temp").toString();
        String humidity= main_obj.get("humidity").toString();

        LogUtils.debug("Creating new weather object");
        weather_obj.setHumidity(Double.parseDouble(humidity));
        weather_obj.setTemp(Double.parseDouble(temp));
        return  weather_obj;
    }



}
