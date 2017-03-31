package in.theuniquemedia.brainbout.common.domain;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by mahesh on 3/8/17.
 */
@Entity
@Table(name = "user_profile")
public class UserProfile {
    private Integer userProfileSeq;
    private Integer companySeq;
    private LocationMstr locationMstr;
    private String userId;
    private String password;
    private char status;

    public UserProfile() {
    }

    public UserProfile(Integer companySeq, String userId, String password, char status) {
        this.companySeq = companySeq;
        this.userId = userId;
        this.password = password;
        this.status = status;
    }

    public UserProfile(Integer companySeq, LocationMstr locationMstr, String userId, String password, char status) {
        this.companySeq = companySeq;
        this.locationMstr = locationMstr;
        this.userId = userId;
        this.password = password;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_PROFILE_SEQ")
    public Integer getUserProfileSeq() {
        return userProfileSeq;
    }

    public void setUserProfileSeq(Integer userProfileSeq) {
        this.userProfileSeq = userProfileSeq;
    }

    @Column(name = "COMPANY_SEQ", nullable = false)
    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_MSTR_SEQ", nullable = false)
    public LocationMstr getLocationMstr() {
        return locationMstr;
    }

    public void setLocationMstr(LocationMstr locationMstr) {
        this.locationMstr = locationMstr;
    }

    @Column(name = "USER_ID", nullable = false)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
