package in.theuniquemedia.brainbout.quiz.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/22/17.
 */
public class OptionVO implements Serializable {
    private String optionTitle;
    private Integer optionSeq;

    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    public Integer getOptionSeq() {
        return optionSeq;
    }

    public void setOptionSeq(Integer optionSeq) {
        this.optionSeq = optionSeq;
    }
}
