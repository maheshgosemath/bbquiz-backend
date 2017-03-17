package in.theuniquemedia.brainbout.admin.service;

import in.theuniquemedia.brainbout.admin.vo.*;
import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mahesh on 2/28/17.
 */
public interface IAdmin {
    public void createCompany(CorporateVO addCorporateVO);

    void addCompetition(AddCompetitionVO addCompetitionVO);

    @Transactional
    void addCompanyCompetition(AddCompanyCompetitionVO addCompanyCompetitionVO);

    @Transactional
    void createQuestion(AddQuestionVO addQuestionVO);

    @Transactional
    List<CorporateVO> fetchCompanyList();

    @Transactional
    List<AddCompetitionVO> fetchCompanyCompetitionList();

    @Transactional
    List<QuestionVO> fetchQuestionList();

    @Transactional
    List<CommonDetailsVO> fetchcompanyDetails();

    @Transactional
    List<CommonDetailsVO> fetchGenreDetails();

    @Transactional
    void updateCompany(CorporateVO newCorporateVO);

    @Transactional
    AddQuestionVO fetchQuizDetails(Integer quizSeq);

    @Transactional
    void updateQuestion(AddQuestionVO addQuestionVO);

    @Transactional
    AddCompetitionVO fetchCompetiotionDetails(String token);
}
