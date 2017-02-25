package in.theuniquemedia.brainbout.quiz.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/23/17.
 */
public class QuizOptionVO implements Serializable {
    private Integer quizSeq;
    private Integer optionSeq;
    private String isCorrect;

    public Integer getQuizSeq() {
        return quizSeq;
    }

    public void setQuizSeq(Integer quizSeq) {
        this.quizSeq = quizSeq;
    }

    public Integer getOptionSeq() {
        return optionSeq;
    }

    public void setOptionSeq(Integer optionSeq) {
        this.optionSeq = optionSeq;
    }

    public String getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(String isCorrect) {
        this.isCorrect = isCorrect;
    }
}
