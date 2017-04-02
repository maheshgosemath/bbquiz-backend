package in.theuniquemedia.brainbout.admin.service.impl;

import in.theuniquemedia.brainbout.admin.service.IAdmin;
import in.theuniquemedia.brainbout.admin.vo.*;
import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import in.theuniquemedia.brainbout.common.vo.CompanyCompetitionVO;
import in.theuniquemedia.brainbout.common.vo.CompanyLocationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by mahesh on 2/28/17.
 */
@Service
public class AdminService implements IAdmin {

    @Autowired
    IRepository<Company, Integer> companyRepository;

    @Autowired
    IRepository<Competition, Integer> competitionRepository;

    @Autowired
    IRepository<CompanyCompetition, Integer> companyCompetitionRepository;

    @Autowired
    IRepository<CompanyDomain, Integer> companyDomainRepository;

    @Autowired
    IRepository<CompanyLocation, Integer> companyLocationRepository;

    @Autowired
    CommonDelegate commonDelegate;

    @Override
    @Transactional
    public void createCompany(CorporateVO addCorporateVO) {
        Integer companySeq = createCorporate(addCorporateVO);
        addCompanyDomain(companySeq, addCorporateVO.getDomainList());
        addCompanyLocations(companySeq, addCorporateVO.getLocationDetails());
    }

    @Transactional
    public Integer createCorporate(CorporateVO addCorporateVO) {
        Company company = new Company();
        company.setCompanyName(addCorporateVO.getCorporateName());
        company.setSpocEmail(addCorporateVO.getSpocEmail());
        company.setSpocName(addCorporateVO.getSpocName());
        company.setCustomCompanyText(AppConstants.CHAR_N);
        company.setStatus(AppConstants.STATUS_ACTIVE);

        return companyRepository.save(company);
    }

    @Transactional
    private void addCompanyDomain(Integer companySeq, List<String> domainList) {
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        if(company != null) {
            if(domainList != null && domainList.size() > 0) {
                for (String domain : domainList) {
                    CompanyDomain companyDomain = new CompanyDomain();
                    companyDomain.setCompany(company);
                    companyDomain.setDomainName(domain);
                    companyDomain.setStatus(AppConstants.STATUS_ACTIVE);
                    companyDomainRepository.save(companyDomain);
                }
            }
        }
    }

    @Transactional
    private void addCompanyLocations(Integer companySeq, List<CommonDetailsVO> locationDetails) {
        if(locationDetails != null && locationDetails.size() > 0) {
            Company company = new Company();
            company.setCompanySeq(companySeq);
            for(CommonDetailsVO commonDetailsVO: locationDetails) {
                LocationMstr locationMstr = new LocationMstr();
                locationMstr.setLocationMstrSeq(commonDetailsVO.getSeq());
                CompanyLocation companyLocation = new CompanyLocation();

                companyLocation.setCompany(company);
                companyLocation.setLocationMstr(locationMstr);
                companyLocationRepository.save(companyLocation);
            }
        }
    }

    @Override
    @Transactional
    public void addCompetition(AddCompetitionVO addCompetitionVO) {
        CompanyCompetition companyCompetition = new CompanyCompetition();
        Company company = commonDelegate.fetchCompanyBySeq(addCompetitionVO.getCompanySeq());
        Competition competition = commonDelegate.fetchCompetitionBySeq(addCompetitionVO.getCompetitionSeq());

        if(company != null && competition != null) {
            companyCompetition.setCompany(company);
            companyCompetition.setCompetition(competition);
            companyCompetition.setStartTime(CommonUtil.convertStringToDate(addCompetitionVO.getStartDate(), "yyyy-MM-dd"));
            companyCompetition.setEndTime(CommonUtil.convertStringToDate(addCompetitionVO.getEndDate(), "yyyy-MM-dd"));
            companyCompetition.setTimeLimit(String.valueOf(addCompetitionVO.getTimeLimit()));
            companyCompetition.setRefCode(CommonUtil.getRandomString());
            companyCompetition.setStatus(AppConstants.STATUS_ACTIVE);
            companyCompetitionRepository.save(companyCompetition);
        }
    }

    @Override
    @Transactional
    public void addCompanyCompetition(AddCompanyCompetitionVO addCompanyCompetitionVO) {
        AddCompetitionVO addCompetitionVO = new AddCompetitionVO();
        addCompetitionVO.setCompetitionName(addCompanyCompetitionVO.getCompetitionName());
        addCompetitionVO.setStartDate(addCompanyCompetitionVO.getStartDate());
        addCompetitionVO.setEndDate(addCompanyCompetitionVO.getEndDate());
        addCompetitionVO.setCompetitionSubTitle(addCompanyCompetitionVO.getCompetitionSubTitle());
        addCompetitionVO.setTimeLimit(Integer.parseInt(addCompanyCompetitionVO.getTimeLimit()));
        for(CommonDetailsVO companyDetailsVO: addCompanyCompetitionVO.getCommonDetailsVOList()) {
            Integer competitionSeq = createCompetition(addCompetitionVO);
            addCompetitionVO.setCompetitionSeq(competitionSeq);
            addCompetitionVO.setCompanySeq(companyDetailsVO.getSeq());
            addCompetition(addCompetitionVO);
        }
    }

    @Transactional
    public Integer createCompetition(AddCompetitionVO addCompetitionVO) {
        Competition competition = new Competition();
        competition.setCompetitionName(addCompetitionVO.getCompetitionName());
        competition.setNoOfQuestions(10);
        competition.setStatus(AppConstants.STATUS_ACTIVE);
        return competitionRepository.save(competition);
    }

    @Override
    @Transactional
    public void updateCompanyCompetition(AddCompanyCompetitionVO addCompanyCompetitionVO) {
        CompanyCompetition companyCompetition = commonDelegate.fetchCompanyCompetitionDetails(addCompanyCompetitionVO.getToken());
        if(companyCompetition != null) {
            companyCompetition.setEndTime(CommonUtil.convertStringToDate(addCompanyCompetitionVO.getEndDate(), "yyyy-MM-dd"));
            companyCompetition.setStartTime(CommonUtil.convertStringToDate(addCompanyCompetitionVO.getStartDate(), "yyyy-MM-dd"));
            companyCompetition.setTimeLimit(addCompanyCompetitionVO.getTimeLimit());

            Competition competition = companyCompetition.getCompetition();
            if(competition != null) {
                competition.setCompetitionName(addCompanyCompetitionVO.getCompetitionName());
                competition.setCompetitionSubTitle(addCompanyCompetitionVO.getCompetitionSubTitle());
                competitionRepository.merge(competition);
            }
            companyCompetitionRepository.merge(companyCompetition);
        }
    }

    public void updateCompetition(AddCompetitionVO addCompetitionVO) {
        Competition competition = commonDelegate.fetchCompetitionBySeq(addCompetitionVO.getCompetitionSeq());
        if(competition != null) {
            competition.setCompetitionName(addCompetitionVO.getCompetitionName());
            competitionRepository.merge(competition);
        }
    }

    @Override
    @Transactional
    public void createQuestion(AddQuestionVO addQuestionVO) {
        commonDelegate.createQuestions(addQuestionVO);
    }

    @Override
    @Transactional
    public List<CorporateVO> fetchCompanyList() {
        return commonDelegate.fetchCompanyList();
    }

    @Override
    @Transactional
    public List<AddCompetitionVO> fetchCompanyCompetitionList() {
        return commonDelegate.fetchCompanyCompetitionList();
    }

    @Override
    @Transactional
    public List<QuestionVO> fetchQuestionList() {
        return commonDelegate.fetchQuestionList();
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchcompanyDetails() {
        return commonDelegate.fetchcompanyDetails();
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchGenreDetails() {
        return commonDelegate.fetchGenreDetails();
    }

    @Override
    @Transactional
    public void updateCompany(CorporateVO newCorporateVO) {
        Integer companySeq = newCorporateVO.getCorporateSeq();
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        if(company != null) {
            company.setCompanyName(newCorporateVO.getCorporateName());
            company.setSpocEmail(newCorporateVO.getSpocEmail());
            company.setSpocName(newCorporateVO.getSpocName());
            companyRepository.merge(company);
            updateCompanyDomain(companySeq, newCorporateVO.getDomainList());

            List<Integer> companyLocationList = new ArrayList<>();
            for(CommonDetailsVO commonDetailsVO: newCorporateVO.getLocationDetails()) {
                companyLocationList.add(commonDetailsVO.getSeq());
            }
            updateCompanyLocation(companySeq, companyLocationList);
        }
    }

    @Transactional
    public void updateCompanyDomain(Integer companySeq, List<String> domainList) {
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        List<CompanyDomain> companyDomainList = commonDelegate.fetchCompanyDomains(companySeq);
        List<CompanyDomain> finalDomainList = new ArrayList();
        for(CompanyDomain oldDomain: companyDomainList) {
            if(domainList.contains(oldDomain.getDomainName())) {
                domainList.remove(oldDomain.getDomainName());
            } else {
                oldDomain.setStatus(AppConstants.STATUS_INACTIVE);
                finalDomainList.add(oldDomain);
            }
        }
        for(String newDomain: domainList) {
            CompanyDomain companyDomain = new CompanyDomain();
            companyDomain.setCompany(company);
            companyDomain.setDomainName(newDomain);
            companyDomain.setStatus(AppConstants.STATUS_ACTIVE);
            finalDomainList.add(companyDomain);
        }
        for(CompanyDomain companyDomain: finalDomainList) {
            companyDomainRepository.merge(companyDomain);
        }
    }

    @Transactional
    public void updateCompanyLocation(Integer companySeq, List<Integer> locationList) {
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        List<CompanyLocation> companyLocationList = commonDelegate.fetchLocationsByCompanySeq(companySeq);
        List<CompanyLocation> finalLocationList = new ArrayList();
        for(CompanyLocation oldLocation: companyLocationList) {
            if(locationList.contains(oldLocation.getLocationMstr().getLocationMstrSeq())) {
                locationList.remove(oldLocation.getLocationMstr().getLocationMstrSeq());
            } else {
                oldLocation.setStatus(AppConstants.STATUS_INACTIVE);
                finalLocationList.add(oldLocation);
            }
        }
        for(Integer newDomain: locationList) {
            CompanyLocation companyLocation = new CompanyLocation();
            companyLocation.setCompany(company);
            companyLocation.setStatus(AppConstants.STATUS_ACTIVE);

            LocationMstr locationMstr = new LocationMstr();
            locationMstr.setLocationMstrSeq(newDomain);
            companyLocation.setLocationMstr(locationMstr);
            finalLocationList.add(companyLocation);
        }
        for(CompanyLocation companyLocation: finalLocationList) {
            companyLocationRepository.merge(companyLocation);
        }
    }

    @Override
    @Transactional
    public AddQuestionVO fetchQuizDetails(Integer quizSeq) {
        return commonDelegate.fetchQuizDetails(quizSeq);
    }

    @Override
    @Transactional
    public void updateQuestion(AddQuestionVO addQuestionVO) {
        commonDelegate.updateQuestion(addQuestionVO);
    }

    @Override
    @Transactional
    public AddCompetitionVO fetchCompetiotionDetails(String token) {
        return commonDelegate.fetchCompetitionDetails(token);
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchLocationDetails() {
        return commonDelegate.fetchLocationDetails();
    }

    @Override
    @Transactional
    public List<CompanyLocationVO> fetchCompanyLocationDetails() {
        return commonDelegate.fetchCompanyLocationDetails();
    }
}
