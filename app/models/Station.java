package models;

import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Entity
public class Station extends Model {
    public String name;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Reading> readings = new ArrayList<Reading>();
    public String latestWeather;
    public double farenheit;
    public double celsius;
    public double latestWindSpeed;
    public int showPressure;
    public String windCompass;
    public double windChill;

    public Station(String name) {
        this.name = name;
    }

    public String convertToLatestWeather(int weatherCode) {
        if (weatherCode == 100) {
            return "Clear";
        } else if (weatherCode == 200) {
            return "Partial Clouds";
        } else if (weatherCode == 300) {
            return "Cloudy";
        } else if (weatherCode == 400) {
            return "Light Showers";
        } else if (weatherCode == 500) {
            return "Heavy Showers";
        } else if (weatherCode == 600) {
            return "Rain";
        } else if (weatherCode == 700) {
            return "Snow";
        } else if (weatherCode == 800) {
            return "Thunder";
        } else
            return "Not Defined";
    }

    public double convertToFarenheit(double temperature) {
        for (int i = 0; i < this.readings.size() - 1; ++i) {
            farenheit = ((this.readings.get(i + 1).temperature * 9 / 5) + 32);
        }
        return farenheit;
    }

    public double temperatureCelsius(double temperature) {
        for (int i = 0; i < this.readings.size() - 1; ++i) {
            celsius = (this.readings.get(i + 1).temperature);
        }
        return celsius;
    }

    public int showPressureHPA(int pressure) {
        for (int i = 0; i < this.readings.size() - 1; ++i) {
            showPressure = (this.readings.get(i + 1).pressure);
        }
        return showPressure;
    }

    public double showWindChill(double temperature, double windSpeed) {
        windChill = (((13.12 + (0.6215 * temperature)) - (11.37 * (Math.pow(windSpeed, 0.16)))) + ((0.3965 * temperature) * (Math.pow(windSpeed, 0.16))));
        double d=windChill;
        double roundedDouble = Math.round(d * 100.0) / 100.0;
        return (roundedDouble);
        //return windChill;
        //String total2 = String.valueOf(windChill);
        //return parseDouble(String.format("%.2f", total2));
    }

    public double convertToLatestWindSpeed(double windSpeed) {
        if (windSpeed <= 1) {
            return 0;
        } else if (windSpeed > 1 || windSpeed <= 5) {
            return 1;
        } else if (windSpeed >= 6 || windSpeed <= 11) {
            return 2;
        } else if (windSpeed >= 12 || windSpeed <= 19) {
            return 3;
        } else if (windSpeed >= 20 || windSpeed <= 28) {
            return 4;
        } else if (windSpeed >= 29 || windSpeed <= 38) {
            return 5;
        } else if (windSpeed >= 39 || windSpeed <= 49) {
            return 6;
        } else if (windSpeed >= 50 || windSpeed <= 61) {
            return 7;
        } else if (windSpeed >= 62 || windSpeed <= 74) {
            return 8;
        } else if (windSpeed >= 75 || windSpeed <= 88) {
            return 9;
        } else if (windSpeed >= 89 || windSpeed <= 102) {
            return 10;
        } else
            return 11;
    }
    public String showWindCompass(int windDirection) {
        if (windDirection >= 348.75 || windDirection <=11.25) {
            return "North";
        } else if (windDirection > 11.25 && windDirection < 33.75) {
            return "North North East";
        } else if (windDirection > 33.75 && windDirection < 56.25) {
            return "North East";
        } else if (windDirection > 56.25 && windDirection < 78.75) {
            return "East North East";
        } else if (windDirection > 78.75 && windDirection < 101.25) {
            return "East";
        } else if (windDirection > 101.25 && windDirection < 123.75) {
            return "East South East";
        } else if (windDirection > 123.75 && windDirection < 146.25) {
            return "South East";
        } else if (windDirection > 146.25 && windDirection < 168.75) {
            return "South South East";
        } else if (windDirection > 168.75 && windDirection < 191.25) {
            return "South";
        } else if (windDirection > 191.25 && windDirection < 213.75) {
            return "South South West";
        } else if (windDirection > 213.75 && windDirection < 236.25) {
            return "South West";
        } else if (windDirection > 236.25 && windDirection < 258.75) {
            return "West South West";
        } else if (windDirection > 258.75 && windDirection < 281.25) {
            return "West";
        } else if (windDirection > 281.25 && windDirection < 303.75) {
            return "West North West";
        } else if (windDirection > 303.75 && windDirection < 326.25) {
            return "North West";
        } else if (windDirection > 326.25 && windDirection < 348.75) {
            return "North North West";
        } else
            return "Nothing";
    }
}