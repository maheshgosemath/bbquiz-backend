package in.theuniquemedia.brainbout.admin.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/28/17.
 */
public class AddResponseVO implements Serializable {
    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
