package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/8/17.
 */
@Entity
@Table(name = "user_profile")
public class UserProfile {
    private Integer userProfileSeq;
    private Integer compnaySeq;
    private String userId;
    private String password;
    private char status;

    public UserProfile() {
    }

    public UserProfile(Integer compnaySeq, String userId, String password, char status) {
        this.compnaySeq = compnaySeq;
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
    public Integer getCompnaySeq() {
        return compnaySeq;
    }

    public void setCompnaySeq(Integer compnaySeq) {
        this.compnaySeq = compnaySeq;
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
