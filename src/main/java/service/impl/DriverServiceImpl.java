package service.impl;

import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DriverRepository;
import service.DriverService;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */
@Service
public class DriverServiceImpl implements DriverService {
    @Autowired
    DriverRepository driverRepository;

    @Override
    @Transactional
    public Driver getDriverById(Integer id) {
        return driverRepository.findOneById(id);
    }

    @Override
    @Transactional
    public Driver getDriverByUsername(String username) {
        return driverRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public List<Driver> getAllDriver() {
        return driverRepository.findAll();
    }
}
