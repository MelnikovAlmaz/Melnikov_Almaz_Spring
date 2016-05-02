package service;

import entity.Passenger;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface PassengerService {
    Passenger getPassengerById(Integer id);
    Passenger getPassengerByUsername(String username);
    List<Passenger> getAllPassenger();
}
