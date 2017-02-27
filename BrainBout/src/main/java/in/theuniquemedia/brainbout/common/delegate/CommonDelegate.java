package in.theuniquemedia.brainbout.common.delegate;

import in.theuniquemedia.brainbout.common.domain.Company;
import in.theuniquemedia.brainbout.common.domain.CompanyCompetition;
import in.theuniquemedia.brainbout.common.domain.Competition;
import in.theuniquemedia.brainbout.common.domain.Genre;
import in.theuniquemedia.brainbout.common.service.ICommon;
import in.theuniquemedia.brainbout.quiz.service.IQuiz;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
@Component
public class CommonDelegate {

    @Autowired
    ICommon commonService;

    @Autowired
    IQuiz quizService;

    public List<Genre> fetchGenreList() {
        return commonService.fetchGenreList();
    }

    public Company fetchCompanyBySeq(Integer companySeq) {
        return commonService.fetchCompanyBySeq(companySeq);
    }

    public Competition fetchCompetitionBySeq(Integer competitionSeq) {
        return commonService.fetchCompetitionBySeq(competitionSeq);
    }

    public HashMap<Integer, Integer> fetchQuizCorrectOptionMap(List<Integer> quizSeqList) {
        return quizService.fetchQuizCorrectOptionMap(quizSeqList);
    }

    public CompanyCompetition fetchCompanyCompetitionBySeq(Integer companySeq, Integer competitionSeq) {
        return commonService.fetchCompanyCompetitionBySeq(companySeq, competitionSeq);
    }

    public List<Integer> fetchQuizList(Integer competitionSeq, Integer maxCount) {
        return quizService.fetchQuizList(competitionSeq, maxCount);
    }

    public List<QuizVO> fetchQuizVOList(List<Integer> quizSeqList) {
        return quizService.fetchQuizList(quizSeqList);
    }
 }
