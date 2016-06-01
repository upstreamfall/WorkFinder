package workfinder.utils.dto;

import java.io.Serializable;

/**
 * Created by pbielicki on 31.05.2016.
 */
public class Person implements Serializable {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name: " + firstName + " " + lastName + ", age: " + age + "]";
    }
}
