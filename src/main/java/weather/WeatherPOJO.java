package weather;
import lombok.*;

@Data
public class WeatherPOJO {

    private Double temp;
    private Double humidity;


    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        /* Check if o is an instance of WeatherPOJO or not */
        if (!(o instanceof WeatherPOJO)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        WeatherPOJO pojo = (WeatherPOJO) o;
        return isEqualTemp(temp,pojo.temp) && isEqualHumidity(humidity,pojo.humidity);
    }

    public boolean isEqualTemp(Double temp1, Double temp2)
    {
        // Compare the data members and return accordingly
        Double diff=Math.abs(temp1 -temp2);

        if (diff > Double.parseDouble(Variance.temp_range))
        {
            return false;
        }
        return true;
    }

    public boolean isEqualHumidity(Double hum1, Double hum2)
    {
        // Compare the data members and return accordingly
        Double diff=Math.abs(hum1 -hum2);

        if (diff > Double.parseDouble(Variance.humidity_range))
        {
            return false;
        }
        return true;
    }
}
