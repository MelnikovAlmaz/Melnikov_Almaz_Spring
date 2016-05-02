package form;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.SqlResultSetMapping;
import java.sql.Date;

/**
 * Created by Almaz on 01.05.2016.
 */
@SqlResultSetMapping(name="CountInMonthForm", classes = {
        @ConstructorResult(targetClass = CountInMonthForm.class,
                columns = {@ColumnResult(name="count"), @ColumnResult(name="date")})
})
public class CountInMonthForm {
    private int count;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
