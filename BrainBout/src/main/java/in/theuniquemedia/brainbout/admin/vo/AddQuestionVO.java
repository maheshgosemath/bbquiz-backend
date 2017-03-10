package in.theuniquemedia.brainbout.admin.vo;

import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by mahesh on 2/28/17.
 */
public class AddQuestionVO implements Serializable {
    private Integer quizSeq;
    private QuizVO quizVO;
    private MultipartFile multipartFile;
    private Integer genreSeq;
    private Integer correctAnswer;

    public Integer getQuizSeq() {
        return quizSeq;
    }

    public void setQuizSeq(Integer quizSeq) {
        this.quizSeq = quizSeq;
    }

    public QuizVO getQuizVO() {
        return quizVO;
    }

    public void setQuizVO(QuizVO quizVO) {
        this.quizVO = quizVO;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Integer getGenreSeq() {
        return genreSeq;
    }

    public void setGenreSeq(Integer genreSeq) {
        this.genreSeq = genreSeq;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
