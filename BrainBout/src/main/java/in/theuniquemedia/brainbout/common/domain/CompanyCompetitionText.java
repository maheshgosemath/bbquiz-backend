package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 4/2/17.
 */
@Entity
@Table(name = "company_competition_text")
public class CompanyCompetitionText {
    private Integer companyCompetitionTextSeq;
    private Integer companySeq;
    private String companyText;
    private char status;

    public CompanyCompetitionText() {
    }

    public CompanyCompetitionText(Integer companySeq, String companyText, char status) {
        this.companySeq = companySeq;
        this.companyText = companyText;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPANY_COMPETITION_TEXT_SEQ")
    public Integer getCompanyCompetitionTextSeq() {
        return companyCompetitionTextSeq;
    }

    public void setCompanyCompetitionTextSeq(Integer companyCompetitionTextSeq) {
        this.companyCompetitionTextSeq = companyCompetitionTextSeq;
    }

    @Column(name = "COMPANY_SEQ")
    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    @Column(name = "COMPANY_TEXT")
    public String getCompanyText() {
        return companyText;
    }

    public void setCompanyText(String companyText) {
        this.companyText = companyText;
    }

    @Column(name = "STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
