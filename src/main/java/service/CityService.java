package service;

import entity.City;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface CityService {
    City getCityById(Integer id);
    List<City> getAllCities();
}
