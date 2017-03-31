package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/25/17.
 */
@Entity
@Table(name = "location_mstr")
public class LocationMstr {
    private Integer locationMstrSeq;
    private String locationCd;
    private String locationText;
    private char status;

    public LocationMstr() {
    }

    public LocationMstr(String locationCd, String locationText, char status) {
        this.locationCd = locationCd;
        this.locationText = locationText;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LOCATION_MSTR_SEQ")
    public Integer getLocationMstrSeq() {
        return locationMstrSeq;
    }

    public void setLocationMstrSeq(Integer locationMstrSeq) {
        this.locationMstrSeq = locationMstrSeq;
    }

    @Column(name = "LOCATION_CD")
    public String getLocationCd() {
        return locationCd;
    }

    public void setLocationCd(String locationCd) {
        this.locationCd = locationCd;
    }

    @Column(name = "LOCATION_TEXT")
    public String getLocationText() {
        return locationText;
    }

    public void setLocationText(String locationText) {
        this.locationText = locationText;
    }

    @Column(name = "STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
