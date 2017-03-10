package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/9/17.
 */
@Entity
@Table(name = "company_domain")
public class CompanyDomain {
    private Integer companyDomainSeq;
    private Company company;
    private String domainName;
    private char status;

    public CompanyDomain() {
    }

    public CompanyDomain(Company company, String domainName, char status) {
        this.company = company;
        this.domainName = domainName;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPANY_DOMAIN_SEQ")
    public Integer getCompanyDomainSeq() {
        return companyDomainSeq;
    }

    public void setCompanyDomainSeq(Integer companyDomainSeq) {
        this.companyDomainSeq = companyDomainSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_SEQ", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "DOMAIN_NAME", nullable = false)
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
