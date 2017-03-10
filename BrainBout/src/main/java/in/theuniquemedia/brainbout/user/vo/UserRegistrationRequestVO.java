package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/7/17.
 */
public class UserRegistrationRequestVO implements Serializable {
    private UserVO userVO;
    private Integer companySeq;
    private Integer subCompanySeq;
    private String password;

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
}