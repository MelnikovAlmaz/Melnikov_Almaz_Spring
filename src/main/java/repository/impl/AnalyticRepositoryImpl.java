package repository.impl;

import form.BasicInformationForm;
import form.CountInMonthForm;
import form.CountInYearForm;
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
    public ArrayList<Integer> findAllInMonthByDriver_Id(int driverId, int month, int year) {
        ArrayList<Integer> data = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            data.add(0);
        }
        try {
            Connection dbConnection = DbDriverSingleton.getDbConnection();
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count, date_part('day',b.date) as date FROM book as b  WHERE b.driver = ? AND date_part('month', b.date) = ? AND date_part('year', b.date) = ? GROUP BY date_part('day',b.date) ORDER BY date_part('day',b.date)");
            stmt.setInt(1, driverId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                data.set(rs.getInt("date"), rs.getInt("count"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return data;
    }

    public List<CountInYearForm> findAllInYearByDriver_Id(int driverId, int year) {
        ArrayList<CountInYearForm> countInYearForms = new ArrayList<>();
        try {
            Connection dbConnection = DbDriverSingleton.getDbConnection();
            PreparedStatement stmt = dbConnection.prepareStatement("SELECT COUNT(*) as count, date_part('month', b.date) as month FROM book as b  WHERE b.driver = ? AND date_part('year', b.date) = ? GROUP BY date_part('month', b.date) ORDER BY date_part('month', b.date)");
            stmt.setInt(1, driverId);
            stmt.setInt(2, year);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CountInYearForm countInYearForm = new CountInYearForm();
                countInYearForm.setCount(rs.getInt("count"));
                countInYearForm.setMonth(rs.getInt("month"));
                countInYearForms.add(countInYearForm);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return countInYearForms;
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
