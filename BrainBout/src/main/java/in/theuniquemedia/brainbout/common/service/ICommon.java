package in.theuniquemedia.brainbout.common.service;

import in.theuniquemedia.brainbout.admin.vo.AddCompetitionVO;
import in.theuniquemedia.brainbout.admin.vo.CorporateVO;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import in.theuniquemedia.brainbout.common.vo.CompanyCompetitionVO;
import in.theuniquemedia.brainbout.common.vo.CompanyVO;
import in.theuniquemedia.brainbout.common.vo.CompetitionVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by mahesh on 2/20/17.
 */
public interface ICommon {
    @Transactional
    Genre fetchGenreBySeq(Integer genreSeq);

    public List<Genre> fetchGenreList();

    @Transactional
    List<CorporateVO> fetchCompanyList();

    @Transactional
    List<AddCompetitionVO> fetchCompanyCompetitionList();

    @Transactional
    List<String> fetchCompanyDomainList(Integer companySeq);

    @Transactional
    List<CompanyDomain> fetchCompanyDomain(Integer companySeq);

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

    @Transactional
    List<CommonDetailsVO> fetchcompanyDetails();

    @Transactional
    List<CommonDetailsVO> fetchGenreDetails();

    @Transactional
    CompanyCompetition fetchCompetitionInCompany(Integer companySeq);
}
