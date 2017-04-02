package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name="competition")
public class Competition {
    private Integer competitionSeq;
    private String competitionName;
    private String competitionSubTitle;
    private Integer noOfQuestions;
    private char status;

    public Competition() {
    }

    public Competition(String competitionName, Integer noOfQuestions, char status) {
        this.competitionName = competitionName;
        this.noOfQuestions = noOfQuestions;
        this.status = status;
    }

    public Competition(String competitionName, String competitionSubTitle, Integer noOfQuestions, char status) {
        this.competitionName = competitionName;
        this.competitionSubTitle = competitionSubTitle;
        this.noOfQuestions = noOfQuestions;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPETITION_SEQ")
    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    @Column(name = "COMPETITION_NAME")
    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    @Column(name = "COMPETITION_SUB_TITLE")
    public String getCompetitionSubTitle() {
        return competitionSubTitle;
    }

    public void setCompetitionSubTitle(String competitionSubTitle) {
        this.competitionSubTitle = competitionSubTitle;
    }

    @Column(name = "NO_OF_QUESTIONS")
    public Integer getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(Integer noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
