package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/23/17.
 */
public class CompanyVO implements Serializable {
    private Integer companySeq;
    private String companyName;
    private String spocName;
    private String spocEmail;
    private char status;

    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSpocName() {
        return spocName;
    }

    public void setSpocName(String spocName) {
        this.spocName = spocName;
    }

    public String getSpocEmail() {
        return spocEmail;
    }

    public void setSpocEmail(String spocEmail) {
        this.spocEmail = spocEmail;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
