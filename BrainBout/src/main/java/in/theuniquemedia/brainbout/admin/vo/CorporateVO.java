package in.theuniquemedia.brainbout.admin.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/27/17.
 */
public class CorporateVO implements Serializable {
    private Integer corporateSeq;
    private String corporateName;
    private String spocName;
    private String spocEmail;
    List<String> domainList;

    public Integer getCorporateSeq() {
        return corporateSeq;
    }

    public void setCorporateSeq(Integer corporateSeq) {
        this.corporateSeq = corporateSeq;
    }

    public String getCorporateName() {
        return corporateName;
    }

    public void setCorporateName(String corporateName) {
        this.corporateName = corporateName;
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

    public List<String> getDomainList() {
        return domainList;
    }

    public void setDomainList(List<String> domainList) {
        this.domainList = domainList;
    }
}
