package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "competition_participant")
public class CompetitionParticipant {
    public Integer competitionParticipantSeq;
    public Integer companySeq;
    private Competition competition;
    private Participant participant;
    private Date startTime;
    private Integer timeTaken;
    private Integer score;
    private char submitted;
    private char status;

    public CompetitionParticipant() {
    }

    public CompetitionParticipant(Integer companySeq, Competition competition, Participant participant, Date startTime,
                                  Integer timeTaken, Integer score, char submitted, char status) {
        this.companySeq = companySeq;
        this.competition = competition;
        this.participant = participant;
        this.startTime = startTime;
        this.timeTaken = timeTaken;
        this.score = score;
        this.submitted = submitted;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPETITION_PARTICIPANT_SEQ")
    public Integer getCompetitionParticipantSeq() {
        return competitionParticipantSeq;
    }

    public void setCompetitionParticipantSeq(Integer competitionParticipantSeq) {
        this.competitionParticipantSeq = competitionParticipantSeq;
    }

    @Column(name = "COMPANY_SEQ")
    public Integer getCompanySeq() {
        return companySeq;
    }

    public void setCompanySeq(Integer companySeq) {
        this.companySeq = companySeq;
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
    @JoinColumn(name = "PARTICIPANT_SEQ", nullable = false)
    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    @Column(name = "START_TIME")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "DURATION")
    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }

    @Column(name = "SCORE")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name="SUBMITTED")
    public char getSubmitted() {
        return submitted;
    }

    public void setSubmitted(char submitted) {
        this.submitted = submitted;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
