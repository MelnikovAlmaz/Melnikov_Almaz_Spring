package service;

import form.BasicInformationForm;
import form.CountInYearForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 28.04.2016.
 */

public interface AnalyticService {
    ArrayList<Integer> findAllInMonthByDriverId(int driverId, String date);
    List<CountInYearForm> findAllInYearByDriverId(int driverId, int year);
    BasicInformationForm findBasicInfoByDriver_Id(int driverId);
}
