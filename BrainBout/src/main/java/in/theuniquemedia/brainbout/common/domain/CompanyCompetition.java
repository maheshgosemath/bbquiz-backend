package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "company_competition")
public class CompanyCompetition {
    private Integer companyCompetition;
    private Company company;
    private Competition competition;
    private LocationMstr locationMstr;
    private String refCode;
    private Date startTime;
    private Date endTime;
    private String timeLimit;
    private char status;

    public CompanyCompetition() {
    }

    public CompanyCompetition(Company company, Competition competition, String refCode, Date startTime, Date endTime, String timeLimit, char status) {
        this.company = company;
        this.competition = competition;
        this.refCode = refCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeLimit = timeLimit;
        this.status = status;
    }

    public CompanyCompetition(Company company, Competition competition, LocationMstr locationMstr, String refCode, Date startTime, Date endTime, String timeLimit, char status) {
        this.company = company;
        this.competition = competition;
        this.locationMstr = locationMstr;
        this.refCode = refCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeLimit = timeLimit;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPANY_COMPETITION_SEQ")
    public Integer getCompanyCompetition() {
        return companyCompetition;
    }

    public void setCompanyCompetition(Integer companyCompetition) {
        this.companyCompetition = companyCompetition;
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
    @JoinColumn(name = "COMPETITION_SEQ", nullable = false)
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_MSTR_SEQ", nullable = false)
    public LocationMstr getLocationMstr() {
        return locationMstr;
    }

    public void setLocationMstr(LocationMstr locationMstr) {
        this.locationMstr = locationMstr;
    }

    @Column(name = "REF_CODE")
    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    @Column(name = "START_DATE")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "END_DATE")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "TIME_LIMIT")
    public String getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(String timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
