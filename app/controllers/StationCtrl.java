package controllers;

import java.util.List;

import models.Station;
import models.Reading;
import play.Logger;
import play.mvc.Controller;

public class StationCtrl extends Controller
{
    public static void index(Long id) {
        Station station = Station.findById(id);
        Logger.info("Station id = " + id);
        if (station.readings.size() != 0) {
            Reading reading = station.readings.get(station.readings.size() - 1);
            station.latestWeather = station.convertToLatestWeather(reading.code);
            station.farenheit = station.convertToFarenheit(reading.temperature);
            station.celsius = station.temperatureCelsius(reading.temperature);
            station.latestWindSpeed = station.convertToLatestWindSpeed(reading.windSpeed);
            station.showPressure = station.showPressureHPA(reading.pressure);
            station.windCompass = station.showWindCompass(reading.windDirection);
            station.windChill = station.showWindChill(reading.temperature, reading.windSpeed);
            render("station.html", station);
        }
    }
    public static void addReading(Long id, int code, double temperature, double windSpeed, int pressure, int windDirection)
    {
        Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection);
        Station station = Station.findById(id);
        station.readings.add(reading);
        station.save();
        redirect("/stations/" + id);
    }
    public static void deleteReading (Long id, Long readingid)
    {
        Station station = Station.findById(id);
        Reading reading = Reading.findById(readingid);
        Logger.info ("Removing " + reading.code);
        station.readings.remove(reading);
        station.save();
        reading.delete();

        render("station.html", station);
    }
}