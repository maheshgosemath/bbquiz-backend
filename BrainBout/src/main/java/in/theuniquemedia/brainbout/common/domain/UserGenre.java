package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;

/**
 * Created by mahesh on 3/29/17.
 */
@Entity
@Table(name = "user_genre")
public class UserGenre {
    private Integer userGenreSeq;
    private UserProfile userProfile;
    private Genre genre;
    private char status;

    public UserGenre() {
    }

    public UserGenre(UserProfile userProfile, Genre genre, char status) {
        this.userProfile = userProfile;
        this.genre = genre;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_GENRE_SEQ")
    public Integer getUserGenreSeq() {
        return userGenreSeq;
    }

    public void setUserGenreSeq(Integer userGenreSeq) {
        this.userGenreSeq = userGenreSeq;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_PROFILE_SEQ", nullable = false)
    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_SEQ", nullable = false)
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Column(name = "STATUS", nullable = false, length = 1)
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }
}
