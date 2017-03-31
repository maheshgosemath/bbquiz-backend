package in.theuniquemedia.brainbout.user.vo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 3/7/17.
 */
@JsonIgnoreProperties
public class UserRegistrationRequestVO implements Serializable {
    private UserVO userVO;
    private Integer companySeq;
    private Integer subCompanySeq;
    private String password;
    private String passwordConfirm;
    private Integer locationSeq;
    private List<Integer> genreSeq;

    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    public Integer getSubCompanySeq() {
        return subCompanySeq;
    }

    public void setSubCompanySeq(Integer subCompanySeq) {
        this.subCompanySeq = subCompanySeq;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Integer getLocationSeq() {
        return locationSeq;
    }

    public void setLocationSeq(Integer locationSeq) {
        this.locationSeq = locationSeq;
    }

    public List<Integer> getGenreSeq() {
        return genreSeq;
    }

    public void setGenreSeq(List<Integer> genreSeq) {
        this.genreSeq = genreSeq;
    }
}
