package in.theuniquemedia.brainbout.admin.vo;

import java.io.Serializable;

/**
 * Created by mahesh on 3/10/17.
 */
public class QuestionVO implements Serializable {
    private Integer questionSeq;
    private String questionTitle;
    private String genre;
    private String imgUrl;

    public Integer getQuestionSeq() {
        return questionSeq;
    }

    public void setQuestionSeq(Integer questionSeq) {
        this.questionSeq = questionSeq;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
