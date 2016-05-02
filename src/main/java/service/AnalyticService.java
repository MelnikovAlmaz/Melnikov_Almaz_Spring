package service;

import form.BasicInformationForm;
import form.CountInMonthForm;

import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface AnalyticService {
    List<CountInMonthForm> findAllInMonthByDriverId(int driverId, int month);

    BasicInformationForm findBasicInfoByDriver_Id(int driverId);
}
