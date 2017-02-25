package in.theuniquemedia.brainbout.common.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by mahesh on 2/20/17.
 */
@Entity
@Table(name = "genre")
public class Genre {

    private Integer genreSeq;
    private String genreText;
    private String genreCd;
    private char status;

    public Genre() {
    }

    public Genre(String genreText, String genreCd, char status) {
        this.genreText = genreText;
        this.genreCd = genreCd;
        this.status = status;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENRE_SEQ")
    public Integer getGenreSeq() {
        return genreSeq;
    }

    public void setGenreSeq(Integer genreSeq) {
        this.genreSeq = genreSeq;
    }

    @Column(name="GENRE_TEXT")
    public String getGenreText() {
        return genreText;
    }

    public void setGenreText(String genreText) {
        this.genreText = genreText;
    }

    @Column(name="GENRE_CD")
    public String getGenreCd() {
        return genreCd;
    }

    public void setGenreCd(String genreCd) {
        this.genreCd = genreCd;
    }

    @Column(name="STATUS")
    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

}
