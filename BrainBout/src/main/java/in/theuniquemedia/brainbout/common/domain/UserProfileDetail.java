package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/8/17.
 */
@Entity
@Table(name = "user_profile_detail")
public class UserProfileDetail {
    private Integer userProfileDetailSeq;
    private UserProfile userProfile;
    private LocationMstr locationMstr;
    private String userImg;
    private String userRegistrationStatus;
    private char status;

    public UserProfileDetail() {
    }

    public UserProfileDetail(UserProfile userProfile, String userImg, String userRegistrationStatus, char status) {
        this.userProfile = userProfile;
        this.userImg = userImg;
        this.userRegistrationStatus = userRegistrationStatus;
        this.status = status;
    }

    public UserProfileDetail(UserProfile userProfile, LocationMstr locationMstr, String userImg, String userRegistrationStatus, char status) {
        this.userProfile = userProfile;
        this.locationMstr = locationMstr;
        this.userImg = userImg;
        this.userRegistrationStatus = userRegistrationStatus;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_PROFILE_DETAIL_SEQ")
    public Integer getUserProfileDetailSeq() {
        return userProfileDetailSeq;
    }

    public void setUserProfileDetailSeq(Integer userProfileDetailSeq) {
        this.userProfileDetailSeq = userProfileDetailSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PROFILE_SEQ", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_MSTR_SEQ", nullable = false)
    public LocationMstr getLocationMstr() {
        return locationMstr;
    }

    public void setLocationMstr(LocationMstr locationMstr) {
        this.locationMstr = locationMstr;
    }

    @Column(name = "USER_IMG", nullable = true)
    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Column(name = "USER_REGISTRATION_STATUS")
    public String getUserRegistrationStatus() {
        return userRegistrationStatus;
    }

    public void setUserRegistrationStatus(String userRegistrationStatus) {
        this.userRegistrationStatus = userRegistrationStatus;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
