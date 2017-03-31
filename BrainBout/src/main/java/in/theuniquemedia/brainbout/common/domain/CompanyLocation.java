package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/25/17.
 */
@Entity
@Table(name = "company_location")
public class CompanyLocation {
    private Integer companyLocationSeq;
    private Company company;
    private LocationMstr locationMstr;
    private char status;

    public CompanyLocation() {
    }

    public CompanyLocation(Company company, LocationMstr locationMstr, char status) {
        this.company = company;
        this.locationMstr = locationMstr;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPANY_LOCATION_SEQ")
    public Integer getCompanyLocationSeq() {
        return companyLocationSeq;
    }

    public void setCompanyLocationSeq(Integer companyLocationSeq) {
        this.companyLocationSeq = companyLocationSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_SEQ", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_MSTR_SEQ", nullable = false)
    public LocationMstr getLocationMstr() {
        return locationMstr;
    }

    public void setLocationMstr(LocationMstr locationMstr) {
        this.locationMstr = locationMstr;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
