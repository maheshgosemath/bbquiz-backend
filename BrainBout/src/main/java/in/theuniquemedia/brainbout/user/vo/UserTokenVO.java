package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/17/17.
 */
public class UserTokenVO implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
