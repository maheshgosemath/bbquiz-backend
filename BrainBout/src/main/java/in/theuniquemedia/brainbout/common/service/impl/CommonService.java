package in.theuniquemedia.brainbout.common.service.impl;

import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.constants.ResponseCode;
import in.theuniquemedia.brainbout.common.domain.Company;
import in.theuniquemedia.brainbout.common.domain.CompanyCompetition;
import in.theuniquemedia.brainbout.common.domain.Competition;
import in.theuniquemedia.brainbout.common.domain.Genre;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.service.ICommon;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.common.vo.CompanyCompetitionVO;
import in.theuniquemedia.brainbout.common.vo.CompanyVO;
import in.theuniquemedia.brainbout.common.vo.CompetitionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mahesh on 2/20/17.
 */
@Service
public class CommonService implements ICommon {

    @Autowired
    IRepository<Genre, Integer> genreRepository;

    @Autowired
    IRepository<Company, Integer> companyRepository;

    @Autowired
    IRepository<Competition, Integer> competitionRepository;

    @Autowired
    IRepository<CompanyCompetition, Integer> companyCompetitionRepository;

    @Transactional
    @Override
    public List<Genre> fetchGenreList() {
        List<Genre> genreList = genreRepository.findByNamedQuery(AppConstants.FETCH_GENRE_LIST);
        return genreList;
    }

    @Override
    @Transactional
    public CompanyCompetition fetchCompanyCompetitionDetails(String ref) {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("refCd", ref);
        List<CompanyCompetition> companyCompetitionList = companyCompetitionRepository.findByNamedQuery(
                AppConstants.FETCH_COMPANY_COMPETITION_DETAILS, queryParams);
        if(companyCompetitionList != null && companyCompetitionList.size() > 0) {
            return companyCompetitionList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public Company fetchCompanyBySeq(Integer companySeq) {
        return companyRepository.findById(Company.class, companySeq);
    }

    @Override
    @Transactional
    public Competition fetchCompetitionBySeq(Integer competitionSeq) {
        return competitionRepository.findById(Competition.class, competitionSeq);
    }

    @Override
    @Transactional
    public CompanyCompetition fetchCompanyCompetitionBySeq(Integer companySeq, Integer competitionSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        queryParams.put("competitionSeq", competitionSeq);
        List<CompanyCompetition> companyCompetitionList = companyCompetitionRepository.findByNamedQuery(
                AppConstants.FETCH_COMPANY_COMPETITION_BY_SEQ, queryParams);
        if(companyCompetitionList != null && companyCompetitionList.size() > 0) {
            return companyCompetitionList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void addCompetition(CompetitionVO competitionVO) {
        Competition competition = new Competition();
        competition.setCompetitionName(competitionVO.getCompetitionName());
        competition.setNoOfQuestions(competitionVO.getNoOfQuestions());
        competitionVO.setStatus(AppConstants.STATUS_INACTIVE);
        competitionRepository.save(competition);
    }

    @Override
    @Transactional
    public void addCompany(CompanyVO companyVO) {
        Company company = new Company();
        company.setCompanyName(companyVO.getCompanyName());
        company.setSpocEmail(companyVO.getSpocEmail());
        company.setSpocName(companyVO.getSpocName());
        companyRepository.save(company);
    }

    @Override
    @Transactional
    public CompanyCompetitionVO fetchCompanyCompetitionVO(String ref) {
        CompanyCompetitionVO companyCompetitionVO = new CompanyCompetitionVO();
        CompanyCompetition companyCompetition = fetchCompanyCompetitionDetails(ref);
        if(companyCompetition != null) {
            companyCompetitionVO.setCompanySeq(companyCompetition.getCompany().getCompanySeq());
            companyCompetitionVO.setCompetitionSeq(companyCompetition.getCompetition().getCompetitionSeq());
            companyCompetitionVO.setTimeLimit(Integer.parseInt(companyCompetition.getTimeLimit()));
            companyCompetitionVO.setCompetitionStatus(ResponseCode.COMPETITION_RUNNING);
            Date startDate = companyCompetition.getStartTime();
            Date endDate = companyCompetition.getEndTime();
            Date now = new Date();

            if(CommonUtil.compareDates(startDate, now) > 0) {
                companyCompetitionVO.setCompetitionStatus(ResponseCode.COMPETITION_NOT_STARTED);
            }
            if(CommonUtil.compareDates(now, endDate) > 0) {
                companyCompetitionVO.setCompetitionStatus(ResponseCode.COMPETITION_CLOSED);
            }
            return companyCompetitionVO;
        }
        return null;
    }
}
