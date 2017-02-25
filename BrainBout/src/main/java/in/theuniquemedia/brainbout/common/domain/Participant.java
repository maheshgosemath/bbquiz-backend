package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/22/17.
 */
@Entity
@Table(name = "participant")
public class Participant {
    private Integer participantSeq;
    private Company company;
    private String name;
    private String email;
    private String participantId;
    private char status;

    public Participant() {
    }

    public Participant(Company company, String name, String email, String participantId, char status) {
        this.company = company;
        this.name = name;
        this.email = email;
        this.participantId = participantId;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PARTICIPANT_SEQ")
    public Integer getParticipantSeq() {
        return participantSeq;
    }

    public void setParticipantSeq(Integer participantSeq) {
        this.participantSeq = participantSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_SEQ", nullable = false)
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PARTICIPANT_ID")
    public String getParticipantId() {
        return participantId;
    }

    public void setParticipantId(String participantId) {
        this.participantId = participantId;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
