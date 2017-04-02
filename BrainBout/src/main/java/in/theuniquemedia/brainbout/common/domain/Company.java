package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "company")
public class Company {
    private Integer companySeq;
    private String companyName;
    private String spocName;
    private String spocEmail;
    private char customCompanyText;
    private char status;

    public Company() {
    }

    public Company(String companyName, String spocName, String spocEmail, char status) {
        this.companyName = companyName;
        this.spocName = spocName;
        this.spocEmail = spocEmail;
        this.status = status;
    }

    public Company(String companyName, String spocName, String spocEmail, char customCompanyText, char status) {
        this.companyName = companyName;
        this.spocName = spocName;
        this.spocEmail = spocEmail;
        this.customCompanyText = customCompanyText;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPANY_SEQ")
    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    @Column(name="COMPANY_NAME")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Column(name="SPOC_NAME")
    public String getSpocName() {
        return spocName;
    }

    public void setSpocName(String spocName) {
        this.spocName = spocName;
    }

    @Column(name="SPOC_EMAIL")
    public String getSpocEmail() {
        return spocEmail;
    }

    public void setSpocEmail(String spocEmail) {
        this.spocEmail = spocEmail;
    }

    @Column(name="COMPANY_CUSTOM_TEXT")
    public char getCustomCompanyText() {
        return customCompanyText;
    }

    public void setCustomCompanyText(char customCompanyText) {
        this.customCompanyText = customCompanyText;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
