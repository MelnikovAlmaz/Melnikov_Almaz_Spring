package entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Almaz on 13.04.2016.
 */
@Entity
@Table(name = "passenger")
public class Passenger extends Person {

    @Column(name = "birth")
    private Date birth;

    @Column(name = "email")
    private String email;

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}