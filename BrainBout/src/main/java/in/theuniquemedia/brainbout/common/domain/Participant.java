package in.theuniquemedia.brainbout.common.domain;

import org.springframework.security.core.userdetails.User;

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
    private UserProfile userProfile;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNo;
    private char status;

    public Participant() {
    }

    public Participant(Company company, UserProfile userProfile, String name, String firstName, String lastName,
                       String email, String phoneNo, char status) {
        this.company = company;
        this.userProfile = userProfile;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNo = phoneNo;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PROFILE_SEQ", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @Column(name = "PHONE_NO")
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
