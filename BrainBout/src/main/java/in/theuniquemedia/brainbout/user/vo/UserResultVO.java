package in.theuniquemedia.brainbout.user.vo;

import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
public class UserResultVO implements Serializable {
    private List<QuizOptionVO> quizOptionVOList;
    private Integer score;
    private String token;

    public List<QuizOptionVO> getQuizOptionVOList() {
        return quizOptionVOList;
    }

    public void setQuizOptionVOList(List<QuizOptionVO> quizOptionVOList) {
        this.quizOptionVOList = quizOptionVOList;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
