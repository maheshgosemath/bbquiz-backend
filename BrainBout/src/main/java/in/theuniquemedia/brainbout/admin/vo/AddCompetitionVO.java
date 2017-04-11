package in.theuniquemedia.brainbout.admin.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/27/17.
 */
public class AddCompetitionVO implements Serializable {
    private Integer competitionSeq;
    private String companyName;
    private String competitionName;
    private String competitionSubTitle;
    private Integer companySeq;
    private String startDate;
    private String endDate;
    private Integer timeLimit;
    private String token;
    private String status;
    private Integer locationSeq;

    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

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

    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
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

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getLocationSeq() {
        return locationSeq;
    }

    public void setLocationSeq(Integer locationSeq) {
        this.locationSeq = locationSeq;
    }
}
