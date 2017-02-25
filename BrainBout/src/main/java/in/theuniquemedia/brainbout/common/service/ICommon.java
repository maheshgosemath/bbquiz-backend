package in.theuniquemedia.brainbout.common.service;

import in.theuniquemedia.brainbout.common.domain.Company;
import in.theuniquemedia.brainbout.common.domain.CompanyCompetition;
import in.theuniquemedia.brainbout.common.domain.Competition;
import in.theuniquemedia.brainbout.common.domain.Genre;
import in.theuniquemedia.brainbout.common.vo.CompanyCompetitionVO;
import in.theuniquemedia.brainbout.common.vo.CompanyVO;
import in.theuniquemedia.brainbout.common.vo.CompetitionVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mahesh on 2/20/17.
 */
public interface ICommon {
    public List<Genre> fetchGenreList();

    @Transactional
    CompanyCompetition fetchCompanyCompetitionDetails(String ref);

    @Transactional
    Company fetchCompanyBySeq(Integer companySeq);

    @Transactional
    Competition fetchCompetitionBySeq(Integer competitionSeq);

    @Transactional
    CompanyCompetition fetchCompanyCompetitionBySeq(Integer companySeq, Integer competitionSeq);

    @Transactional
    void addCompetition(CompetitionVO competitionVO);

    @Transactional
    void addCompany(CompanyVO companyVO);

    @Transactional
    CompanyCompetitionVO fetchCompanyCompetitionVO(String ref);
}
