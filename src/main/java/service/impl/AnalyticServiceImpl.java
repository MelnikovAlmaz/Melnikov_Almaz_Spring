package service.impl;

import form.BasicInformationForm;
import form.CountInMonthForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import repository.DriverRepository;
import repository.impl.AnalyticRepositoryImpl;
import service.AnalyticService;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */
@Service
public class AnalyticServiceImpl implements AnalyticService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AnalyticRepositoryImpl analyticRepositoryImpl;

    @Autowired
    DriverRepository driverRepository;

    @Override
    public List<CountInMonthForm> findAllInMonthByDriverId(int driverId, int month) {
        return analyticRepositoryImpl.findAllInMonthByDriver_Id(driverId, month);
    }

    @Override
    public BasicInformationForm findBasicInfoByDriver_Id(int driverId){
        return analyticRepositoryImpl.findBasicInfoByDriver_Id(driverId);
    }
}
