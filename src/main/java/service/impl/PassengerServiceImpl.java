package service.impl;

import entity.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.PassengerRepository;
import service.PassengerService;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */
@Service
public class PassengerServiceImpl implements PassengerService {
    @Autowired
    PassengerRepository passengerRepository;

    @Override
    @Transactional
    public Passenger getPassengerById(Integer id) {
        return passengerRepository.findOneById(id);
    }

    @Override
    @Transactional
    public Passenger getPassengerByUsername(String username) {
        return passengerRepository.findOneByUsername(username);
    }

    @Override
    @Transactional
    public List<Passenger> getAllPassenger() {
        return passengerRepository.findAll();
    }
}
