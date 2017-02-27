package in.theuniquemedia.brainbout.user.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mahesh on 2/26/17.
 */
public class QuizRequestVO implements Serializable {
    private Integer competitionSeq;
    private Integer companySeq;
    private String email;
    private List<Integer> quizSeqList;

    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getQuizSeqList() {
        return quizSeqList;
    }

    public void setQuizSeqList(List<Integer> quizSeqList) {
        this.quizSeqList = quizSeqList;
    }
}
