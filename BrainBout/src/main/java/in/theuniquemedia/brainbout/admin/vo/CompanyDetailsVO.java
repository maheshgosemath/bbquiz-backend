package in.theuniquemedia.brainbout.admin.vo;

import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 11/4/17.
 */
public class CompanyDetailsVO implements Serializable {
    private Integer seq;
    private String name;
    private Integer score;
    private List<CommonDetailsVO> locationList;
    private String startDate;
    private String endDate;

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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CommonDetailsVO> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<CommonDetailsVO> locationList) {
        this.locationList = locationList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
