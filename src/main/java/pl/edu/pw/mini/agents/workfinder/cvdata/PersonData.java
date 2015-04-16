package pl.edu.pw.mini.agents.workfinder.cvdata;

import java.util.Date;

/**
 * Created by pawel.bielicki on 2015-04-15.
 */
public class PersonData {
    private String name;    //John Smith
    private String email;   //john.smith@gmail.com
    private Date birthday;  //205-04-16
    private int phone;      //555-223-445
    private String placeOfLiving;   //Warsaw, ul. Jana Pawła 23

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceOfLiving() {
        return placeOfLiving;
    }

    public void setPlaceOfLiving(String placeOfLiving) {
        this.placeOfLiving = placeOfLiving;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
