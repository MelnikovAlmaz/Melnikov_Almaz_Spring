package entity;

import javax.persistence.*;

/**
 * Created by Almaz on 13.04.2016.
 */
@Entity
@Table(name = "driver")
public class Driver extends Person{
    @Column(name = "short_name")
    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
