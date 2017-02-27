package in.theuniquemedia.brainbout.user.vo;

import in.theuniquemedia.brainbout.quiz.vo.QuizVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/24/17.
 */
public class UserRegistrationVO implements Serializable {
    private Integer timeLeft;
    private String userStatus;

    public Integer getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}
