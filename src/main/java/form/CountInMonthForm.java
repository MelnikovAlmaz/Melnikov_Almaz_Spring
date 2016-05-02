package form;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;

/**
 * Created by Almaz on 01.05.2016.
 */
@SqlResultSetMapping(name="CountInMonthForm", classes = {
        @ConstructorResult(targetClass = CountInMonthForm.class,
                columns = {@ColumnResult(name="count"), @ColumnResult(name="date")})
})
public class CountInMonthForm {
    private int count;
    private int day;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
