package in.theuniquemedia.brainbout.quiz.service;

import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
public interface IQuiz {
    public List<Integer> fetchQuizList(Integer competitionSeq, Integer maxCount);
    public QuizVO fetchQuiz(Integer quizSeq);
    public List<QuizVO> fetchQuizList(List<Integer> quizList);

    @Transactional
    List<QuizOptionVO> fetchQuizCorrectOptionList(List<Integer> quizSeqList);

    @Transactional
    HashMap<Integer, Integer> fetchQuizCorrectOptionMap(List<Integer> quizSeqList);

    @Transactional
    QuizOptionVO fetchQuizCorrectOption(Integer quizSeq);
}
