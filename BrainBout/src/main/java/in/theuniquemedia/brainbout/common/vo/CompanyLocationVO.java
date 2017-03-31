package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 3/30/17.
 */
public class CompanyLocationVO implements Serializable {
    private Integer seq;
    private String name;
    private List<CommonDetailsVO> locationDetails;

    public CompanyLocationVO(Integer seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CommonDetailsVO> getLocationDetails() {
        return locationDetails;
    }

    public void setLocationDetails(List<CommonDetailsVO> locationDetails) {
        this.locationDetails = locationDetails;
    }
}
