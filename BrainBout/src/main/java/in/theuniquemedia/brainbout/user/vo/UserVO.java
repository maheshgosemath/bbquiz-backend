package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/22/17.
 */
public class UserVO implements Serializable {
    public String name;
    public String email;
    public String firstName;
    public String lastName;
    private String phoneNo;

    public UserVO(String name, String email, String firstName, String lastName, String phoneNo) {
        this.name = name;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
    }

    public UserVO(String name, String email) {
        this.name = name;
        this.email = email;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
