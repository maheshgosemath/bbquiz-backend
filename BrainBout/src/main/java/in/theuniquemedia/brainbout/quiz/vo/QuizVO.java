package in.theuniquemedia.brainbout.quiz.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
public class QuizVO implements Serializable {
    private String QuizTitle;
    private Integer quizSeq;
    private String quizImg;
    private List<OptionVO> optionList;

    public String getQuizTitle() {
        return QuizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        QuizTitle = quizTitle;
    }

    public Integer getQuizSeq() {
        return quizSeq;
    }

    public void setQuizSeq(Integer quizSeq) {
        this.quizSeq = quizSeq;
    }

    public String getQuizImg() {
        return quizImg;
    }

    public void setQuizImg(String quizImg) {
        this.quizImg = quizImg;
    }

    public List<OptionVO> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<OptionVO> optionList) {
        this.optionList = optionList;
    }
}
