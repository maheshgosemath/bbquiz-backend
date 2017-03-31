package in.theuniquemedia.brainbout.quiz.service;

import in.theuniquemedia.brainbout.admin.vo.AddQuestionVO;
import in.theuniquemedia.brainbout.admin.vo.QuestionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
public interface IQuiz {
    public List<Integer> fetchQuizList(String userId, Integer maxCount);

    @Transactional
    List<Integer> fetchQuizListByGenreSeq(List<Integer> genreSeqList, Integer maxCount);

    public QuizVO fetchQuiz(Integer quizSeq);
    public List<QuizVO> fetchQuizList(List<Integer> quizList);

    @Transactional
    List<QuizOptionVO> fetchQuizCorrectOptionList(List<Integer> quizSeqList);

    @Transactional
    HashMap<Integer, Integer> fetchQuizCorrectOptionMap(List<Integer> quizSeqList);

    @Transactional
    QuizOptionVO fetchQuizCorrectOption(Integer quizSeq);

    @Transactional
    void createQuestions(AddQuestionVO addQuestionVO);

    @Transactional
    void updateQuestion(AddQuestionVO addQuestionVO);

    @Transactional
    List<QuestionVO> fetchQuizList();

    @Transactional
    AddQuestionVO fetchQuizDetails(Integer quizSeq);
}
