package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/24/17.
 */
public class UserCompetitionDetails implements Serializable {
    private Integer companySeq;
    private Integer competitionSeq;
    private String name;
    private String email;

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
