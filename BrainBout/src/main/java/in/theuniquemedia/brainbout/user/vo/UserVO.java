package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/22/17.
 */
public class UserVO implements Serializable {
    public String name;
    public String email;

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
}
