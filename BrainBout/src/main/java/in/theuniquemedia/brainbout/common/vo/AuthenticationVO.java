package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/11/17.
 */
public class AuthenticationVO implements Serializable{
    private String name;
    private String email;
    private Integer companySeq;
    private Integer competitionSeq;
    private String competitionStatus;
    private Integer timeLeft;
    private String userStatus;

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

    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    public String getCompetitionStatus() {
        return competitionStatus;
    }

    public void setCompetitionStatus(String competitionStatus) {
        this.competitionStatus = competitionStatus;
    }

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
