package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/8/17.
 */
@Entity
@Table(name = "user_token")
public class UserToken {
    private Integer userTokenSeq;
    private UserProfile userProfile;
    private String tokenType;
    private String userToken;
    private char status;

    public UserToken() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_TOKEN_SEQ")
    public Integer getUserTokenSeq() {
        return userTokenSeq;
    }

    public void setUserTokenSeq(Integer userTokenSeq) {
        this.userTokenSeq = userTokenSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PROFILE_SEQ", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Column(name = "TOKEN_TYPE")
    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    @Column(name = "USER_TOKEN")
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
