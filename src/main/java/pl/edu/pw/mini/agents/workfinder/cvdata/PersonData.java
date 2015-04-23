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

    //TODO
    //Mozna to zrobic generycznie refleksja?
    public PersonData(Properties prop) {
        name = prop.getProperty("name");
        email = prop.getProperty("email");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
