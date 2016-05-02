package service;

import entity.Driver;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface DriverService {
    Driver getDriverById(Integer id);
    Driver getDriverByUsername(String username);
    List<Driver> getAllDriver();
}
