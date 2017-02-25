package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "quiz")
public class Quiz {
    private Integer quizSeq;
    private Competition competition;
    private Genre genre;
    private String quizTitle;
    private char status;

    public Quiz() {
    }

    public Quiz(Competition competition, Genre genre, String quizTitle, char status) {
        this.competition = competition;
        this.genre = genre;
        this.quizTitle = quizTitle;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "QUIZ_SEQ")
    public Integer getQuizSeq() {
        return quizSeq;
    }

    public void setQuizSeq(Integer quizSeq) {
        this.quizSeq = quizSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPETITION_SEQ", nullable = false)
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_SEQ", nullable = false)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Column(name = "QUIZ_TITLE")
    public String getQuizTitle() {
        return quizTitle;
    }

    public void setQuizTitle(String quizTitle) {
        this.quizTitle = quizTitle;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
