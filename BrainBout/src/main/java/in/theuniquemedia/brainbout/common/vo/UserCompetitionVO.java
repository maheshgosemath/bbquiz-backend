package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/14/17.
 */
public class UserCompetitionVO implements Serializable {
    private CompetitionVO competitionVO;
    private String userStatus;
    private Integer userTimeLeft;

    public CompetitionVO getCompetitionVO() {
        return competitionVO;
    }

    public void setCompetitionVO(CompetitionVO competitionVO) {
        this.competitionVO = competitionVO;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserTimeLeft() {
        return userTimeLeft;
    }

    public void setUserTimeLeft(Integer userTimeLeft) {
        this.userTimeLeft = userTimeLeft;
    }
}
