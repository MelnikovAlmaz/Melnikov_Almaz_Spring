package repository.impl;

import form.BasicInformationForm;
import form.CountInMonthForm;
import org.springframework.stereotype.Repository;
import utils.DbDriverSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 30.04.2016.
 */
@Repository
public class AnalyticRepositoryImpl {
    public List<CountInMonthForm> findAllInMonthByDriver_Id(int driverId, int month) {
        ArrayList<CountInMonthForm> countInMonthForms = new ArrayList<>();
        try {
            Connection dbConnection = DbDriverSingleton.getDbConnection();
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count, date(b.date) as date FROM book as b  WHERE b.driver = ? AND date_part('month', b.date) = ? GROUP BY date(b.date) ORDER BY date(b.date)");
            stmt.setInt(1, driverId);
            stmt.setInt(2, month);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CountInMonthForm countInMonthForm = new CountInMonthForm();
                countInMonthForm.setCount(rs.getInt("count"));
                countInMonthForm.setDate(rs.getDate("date"));
                countInMonthForms.add(countInMonthForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return countInMonthForms;
    }
    public BasicInformationForm findBasicInfoByDriver_Id(int driverId) {
        BasicInformationForm basicInformationForm = new BasicInformationForm();
        try {
            Connection dbConnection = DbDriverSingleton.getDbConnection();
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as bookCount, SUM(b.cost) as costSum, AVG(b.cost) as costAVG, MIN(b.cost) as minCost, MAX(b.cost) as maxCost FROM book as b  WHERE b.driver = ?");
            stmt.setInt(1, driverId);
            ResultSet rs = stmt.executeQuery();
            rs.next();

            basicInformationForm.setBookCount(rs.getInt("bookCount"));
            basicInformationForm.setCostSum(rs.getInt("costSum"));
            basicInformationForm.setCostAvr(rs.getInt("costAVG"));
            basicInformationForm.setMinCost(rs.getInt("minCost"));
            basicInformationForm.setMaxCost(rs.getInt("maxCost"));
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return basicInformationForm;
    }
}
