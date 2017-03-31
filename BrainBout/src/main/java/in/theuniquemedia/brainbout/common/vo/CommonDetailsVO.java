package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/2/17.
 */
public class CommonDetailsVO implements Serializable {
    private Integer seq;
    private String name;
    private Long score;

    public CommonDetailsVO() {
    }

    public CommonDetailsVO(Integer seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public CommonDetailsVO(Integer seq, String name, Long score) {
        this.seq = seq;
        this.name = name;
        this.score = score;
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

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
