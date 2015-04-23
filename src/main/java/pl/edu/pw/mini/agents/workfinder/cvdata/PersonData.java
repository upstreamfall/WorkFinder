package pl.edu.pw.mini.agents.workfinder.cvdata;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by pawel.bielicki on 2015-04-15.
 */
public class PersonData {
    private String name;    //John Smith
    private String email;   //john.smith@gmail.com
    private String birthday;  //2015-04-16
    private String phoneNumber;      //555-223-445
    private String placeOfLiving;   //Warsaw, ul. Jana Pawła 23

    //TODO
    //Mozna to zrobic generycznie refleksja?
    public PersonData(Properties prop) {
        name = prop.getProperty("name");
        email = prop.getProperty("email");
        birthday = prop.getProperty("birthday");
        phoneNumber = prop.getProperty("phoneNumber");
        placeOfLiving = prop.getProperty("placeOfLiving");
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
