package in.theuniquemedia.brainbout.admin.vo;

import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 3/2/17.
 */
public class AddCompanyCompetitionVO implements Serializable {
    private String competitionName;
    private String competitionSubTitle;
    private List<CommonDetailsVO> commonDetailsVOList;
    private String startDate;
    private String endDate;
    private String timeLimit;
    private String token;

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCompetitionSubTitle() {
        return competitionSubTitle;
    }

    public void setCompetitionSubTitle(String competitionSubTitle) {
        this.competitionSubTitle = competitionSubTitle;
    }

    public List<CommonDetailsVO> getCommonDetailsVOList() {
        return commonDetailsVOList;
    }

    public void setCommonDetailsVOList(List<CommonDetailsVO> commonDetailsVOList) {
        this.commonDetailsVOList = commonDetailsVOList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
