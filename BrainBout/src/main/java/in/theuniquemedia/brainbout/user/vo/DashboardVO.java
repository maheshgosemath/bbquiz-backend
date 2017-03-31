package in.theuniquemedia.brainbout.user.vo;

import in.theuniquemedia.brainbout.common.vo.UserCompetitionVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 3/31/17.
 */
public class DashboardVO implements Serializable {
    private List<UserCompetitionVO> currentCompetitionList;
    private List<UserCompetitionVO> pastCompetitionList;
    private List<UserCompetitionVO> upcomingCompetitionList;

    public List<UserCompetitionVO> getCurrentCompetitionList() {
        return currentCompetitionList;
    }

    public void setCurrentCompetitionList(List<UserCompetitionVO> currentCompetitionList) {
        this.currentCompetitionList = currentCompetitionList;
    }

    public List<UserCompetitionVO> getPastCompetitionList() {
        return pastCompetitionList;
    }

    public void setPastCompetitionList(List<UserCompetitionVO> pastCompetitionList) {
        this.pastCompetitionList = pastCompetitionList;
    }

    public List<UserCompetitionVO> getUpcomingCompetitionList() {
        return upcomingCompetitionList;
    }

    public void setUpcomingCompetitionList(List<UserCompetitionVO> upcomingCompetitionList) {
        this.upcomingCompetitionList = upcomingCompetitionList;
    }
}
