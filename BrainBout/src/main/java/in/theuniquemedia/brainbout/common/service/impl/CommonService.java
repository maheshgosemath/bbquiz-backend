package in.theuniquemedia.brainbout.common.service.impl;

import in.theuniquemedia.brainbout.admin.vo.AddCompetitionVO;
import in.theuniquemedia.brainbout.admin.vo.CorporateVO;
import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.constants.ResponseCode;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.service.ICommon;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.common.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Autowired
    IRepository<CompanyDomain, Integer> companyDomainRepository;

    @Override
    @Transactional
    public Genre fetchGenreBySeq(Integer genreSeq) {
        return genreRepository.findById(Genre.class, genreSeq);
    }

    @Transactional
    @Override
    public List<Genre> fetchGenreList() {
        List<Genre> genreList = genreRepository.findByNamedQuery(AppConstants.FETCH_GENRE_LIST);
        return genreList;
    }

    @Override
    @Transactional
    public List<CorporateVO> fetchCompanyList() {
        List<Company> companyList = fetchCompanyDomainList();
        if(companyList != null) {
            return convertCompanyList(companyList);
        }
        return null;
    }

    @Transactional
    public List<Company> fetchCompanyDomainList() {
        return companyRepository.findByNamedQuery(AppConstants.FETCH_COMPANY_LIST);
    }

    @Override
    @Transactional
    public List<AddCompetitionVO> fetchCompanyCompetitionList() {
        List<CompanyCompetition> companyCompetitionList = companyCompetitionRepository.findByNamedQuery(AppConstants.FETCH_COMPETITION_LIST);
        if(companyCompetitionList != null) {
            return convertCompetitionList(companyCompetitionList);
        }
        return null;
    }

    private List<AddCompetitionVO> convertCompetitionList(List<CompanyCompetition> companyCompetitionList) {
        List<AddCompetitionVO> addCompetitionList = new ArrayList<>();
        for(CompanyCompetition companyCompetition: companyCompetitionList) {
            AddCompetitionVO addCompetitionVO = new AddCompetitionVO();
            if(companyCompetition.getCompanyCompetition() != null) {
                addCompetitionVO.setCompetitionName(companyCompetition.getCompetition().getCompetitionName());
            }
            if(companyCompetition.getCompany() != null) {
                addCompetitionVO.setCompanyName(companyCompetition.getCompany().getCompanyName());
            }
            if(companyCompetition.getStartTime() != null) {
                addCompetitionVO.setStartDate(CommonUtil.convertUtilDateToString(companyCompetition.getStartTime(), "dd-MMM-yyyy"));
            }
            if(companyCompetition.getEndTime() != null) {
                addCompetitionVO.setEndDate(CommonUtil.convertUtilDateToString(companyCompetition.getEndTime(), "dd-MMM-yyyy"));
            }
            addCompetitionVO.setTimeLimit(Integer.parseInt(companyCompetition.getTimeLimit()));
            addCompetitionVO.setToken(companyCompetition.getRefCode());
            addCompetitionList.add(addCompetitionVO);
        }
        return addCompetitionList;
    }

    private List<CorporateVO> convertCompanyList(List<Company> companyList) {
        List<CorporateVO> corporateList = new ArrayList<>();
        for(Company company: companyList) {
            CorporateVO corporateVO = new CorporateVO();
            corporateVO.setCorporateSeq(company.getCompanySeq());
            corporateVO.setCorporateName(company.getCompanyName());
            corporateVO.setSpocName(company.getSpocName());
            corporateVO.setSpocEmail(company.getSpocEmail());
            corporateVO.setDomainList(fetchCompanyDomainList(company.getCompanySeq()));
            corporateList.add(corporateVO);
        }
        return corporateList;
    }

    @Override

    @Transactional
    public List<String> fetchCompanyDomainList(Integer companySeq) {
        List<CompanyDomain> companyDomainList = fetchCompanyDomain(companySeq);
        List<String> domainList = new ArrayList<>();
        if(companyDomainList != null) {
            for(CompanyDomain companyDomain: companyDomainList) {
                domainList.add(companyDomain.getDomainName());
            }
        }
        return domainList;
    }

    @Override
    @Transactional
    public List<CompanyDomain> fetchCompanyDomain(Integer companySeq) {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("companySeq", companySeq);
        List<CompanyDomain> companyDomainList = companyDomainRepository.findByNamedQuery(
                AppConstants.FETCH_COMPANY_DOMAIN, queryParams);
        if(companyDomainList != null && companyDomainList.size() > 0) {
            return companyDomainList;
        }
        return null;
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

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchcompanyDetails() {
        List<CommonDetailsVO> companyDetailsList = new ArrayList<>();
        List<Company> companyList = fetchCompanyDomainList();
        if(companyList != null) {
            for(Company company: companyList) {
                CommonDetailsVO commonDetailsVO = new CommonDetailsVO();
                commonDetailsVO.setName(company.getCompanyName());
                commonDetailsVO.setSeq(company.getCompanySeq());
                companyDetailsList.add(commonDetailsVO);
            }
        }
        return companyDetailsList;
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchGenreDetails() {
        List<Genre> genreList = fetchGenreList();
        List<CommonDetailsVO> commonDetailsVOList = new ArrayList<>();
        if(genreList != null) {
            for(Genre genre: genreList) {
                CommonDetailsVO commonDetailsVO = new CommonDetailsVO();
                commonDetailsVO.setName(genre.getGenreText());
                commonDetailsVO.setSeq(genre.getGenreSeq());
                commonDetailsVOList.add(commonDetailsVO);
            }
        }
        return commonDetailsVOList;
    }

    @Override
    @Transactional
    public CompanyCompetition fetchCompetitionInCompany(Integer companySeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        List<CompanyCompetition> companyCompetitionList = companyCompetitionRepository.findByNamedQuery(
                AppConstants.FETCH_COMPETITION_BY_COMPANY, queryParams);
        if(companyCompetitionList != null && companyCompetitionList.size() > 0) {
            return companyCompetitionList.get(0);
        }
        return null;
    }

    @Transactional
    public CompanyCompetition fetchCompetitionByToken(String token) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("refCode", token);
        List<CompanyCompetition> companyCompetitionList = companyCompetitionRepository.findByNamedQuery(
                AppConstants.FETCH_COMPETITION_BY_TOKEN, queryParams);
        if(companyCompetitionList != null && companyCompetitionList.size() > 0) {
            return companyCompetitionList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public AddCompetitionVO fetchCompetitionDetails(String token) {
        AddCompetitionVO addCompetitionVO = new AddCompetitionVO();
        CompanyCompetition companyCompetition = fetchCompetitionByToken(token);
        if(companyCompetition != null) {
            addCompetitionVO.setCompanySeq(companyCompetition.getCompany().getCompanySeq());
            addCompetitionVO.setCompanyName(companyCompetition.getCompany().getCompanyName());
            addCompetitionVO.setCompetitionName(companyCompetition.getCompetition().getCompetitionName());
            addCompetitionVO.setCompetitionSeq(companyCompetition.getCompetition().getCompetitionSeq());
            addCompetitionVO.setStartDate(CommonUtil.convertUtilDateToString(companyCompetition.getStartTime(), "yyyy-MM-dd"));
            addCompetitionVO.setEndDate(CommonUtil.convertUtilDateToString(companyCompetition.getEndTime(), "yyyy-MM-dd"));
            addCompetitionVO.setTimeLimit(Integer.parseInt(companyCompetition.getTimeLimit()));
        }
        return addCompetitionVO;
    }

    @Transactional
    public List<CompanyCompetition> fetchActiveCompetitionList(Integer companySeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        return companyCompetitionRepository.findByNamedQuery(AppConstants.FETCH_COMPETITION_BY_COMPANY, queryParams);
    }

    @Override
    @Transactional
    public List<CompetitionVO> fetchCompetitionList(Integer companySeq) {
        List<CompetitionVO> competitionVOList = new ArrayList<>();
        List<CompanyCompetition> companyCompetitionList = fetchActiveCompetitionList(companySeq);
        if(companyCompetitionList != null && companyCompetitionList.size() > 0) {
            for(CompanyCompetition companyCompetition: companyCompetitionList) {
                CompetitionVO competitionVO = new CompetitionVO();
                competitionVO.setCompetitionSeq(companyCompetition.getCompetition().getCompetitionSeq());
                competitionVO.setCompetitionName(companyCompetition.getCompetition().getCompetitionName());
                competitionVO.setTimeLeft((CommonUtil.getNoOfSecondsDiff(companyCompetition.getEndTime(), new Date())).intValue());
                competitionVO.setTotalTime(Integer.parseInt(companyCompetition.getTimeLimit()));
                competitionVOList.add(competitionVO);
            }
        }
        return competitionVOList;
    }

}
