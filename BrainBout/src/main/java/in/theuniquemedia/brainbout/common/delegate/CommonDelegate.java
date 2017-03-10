package in.theuniquemedia.brainbout.common.delegate;

import in.theuniquemedia.brainbout.admin.vo.AddCompetitionVO;
import in.theuniquemedia.brainbout.admin.vo.AddQuestionVO;
import in.theuniquemedia.brainbout.admin.vo.CorporateVO;
import in.theuniquemedia.brainbout.admin.vo.QuestionVO;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.service.ICommon;
import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import in.theuniquemedia.brainbout.quiz.service.IQuiz;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public Genre fetchGenreBySeq(Integer genreSeq) {
        return commonService.fetchGenreBySeq(genreSeq);
    }

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

    public void createQuestions(AddQuestionVO addQuestionVO) {
        quizService.createQuestions(addQuestionVO);
    }

    public List<CorporateVO> fetchCompanyList() {
        return commonService.fetchCompanyList();
    }

    public List<AddCompetitionVO> fetchCompanyCompetitionList() {
        return commonService.fetchCompanyCompetitionList();
    }

    public List<CommonDetailsVO> fetchcompanyDetails() {
        return commonService.fetchcompanyDetails();
    }

    public List<CommonDetailsVO> fetchGenreDetails() {
        return commonService.fetchGenreDetails();
    }

    public List<String> fetchCompanyDomainList(Integer companySeq) {
        return commonService.fetchCompanyDomainList(companySeq);
    }

    public List<CompanyDomain> fetchCompanyDomains(Integer companySeq) {
        return commonService.fetchCompanyDomain(companySeq);
    }

    public List<QuestionVO> fetchQuestionList() {
        return quizService.fetchQuizList();
    }

    public AddQuestionVO fetchQuizDetails(Integer quizSeq) {
        return quizService.fetchQuizDetails(quizSeq);
    }

    public void updateQuestion(AddQuestionVO addQuestionVO) {
        quizService.updateQuestion(addQuestionVO);
    }
 }
