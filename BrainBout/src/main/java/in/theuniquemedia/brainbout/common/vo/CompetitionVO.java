package in.theuniquemedia.brainbout.common.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 2/23/17.
 */
public class CompetitionVO implements Serializable {
    private Integer competitionSeq;
    private String competitionName;
    private Integer noOfQuestions;
    private char status;
    private Integer timeLeft;
    private Integer totalTime;
    private Integer participants;

    public Integer getCompetitionSeq() {
        return competitionSeq;
    }

    public void setCompetitionSeq(Integer competitionSeq) {
        this.competitionSeq = competitionSeq;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public Integer getNoOfQuestions() {
        return noOfQuestions;
    }

    public void setNoOfQuestions(Integer noOfQuestions) {
        this.noOfQuestions = noOfQuestions;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Integer getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Integer timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }
}
