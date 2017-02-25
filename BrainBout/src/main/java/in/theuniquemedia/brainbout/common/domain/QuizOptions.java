package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "quiz_options")
public class QuizOptions {
    private Integer quizOptionsSeq;
    private Quiz quiz;
    private String optionTitle;
    private char isCorrect;
    private char status;

    public QuizOptions() {
    }

    public QuizOptions(Quiz quiz, char isCorrect, char status) {
        this.quiz = quiz;
        this.isCorrect = isCorrect;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUIZ_OPTIONS_SEQ")
    public Integer getQuizOptionsSeq() {
        return quizOptionsSeq;
    }

    public void setQuizOptionsSeq(Integer quizOptionsSeq) {
        this.quizOptionsSeq = quizOptionsSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUIZ_SEQ", nullable = false)
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    @Column(name = "OPTION_TITLE")
    public String getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(String optionTitle) {
        this.optionTitle = optionTitle;
    }

    @Column(name = "IS_CORRECT")
    public char getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(char isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
