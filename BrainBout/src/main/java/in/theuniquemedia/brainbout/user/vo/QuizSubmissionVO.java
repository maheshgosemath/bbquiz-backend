package in.theuniquemedia.brainbout.user.vo;

import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/25/17.
 */
public class QuizSubmissionVO implements Serializable{
    private String email;
    private Integer competitionSeq;
    private List<QuizOptionVO> quizOptionVOList;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    public List<QuizOptionVO> getQuizOptionVOList() {
        return quizOptionVOList;
    }

    public void setQuizOptionVOList(List<QuizOptionVO> quizOptionVOList) {
        this.quizOptionVOList = quizOptionVOList;
    }
}
