package in.theuniquemedia.brainbout.user.service.impl;

import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.constants.ResponseCode;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.service.IEmail;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.common.util.PropertiesUtil;
import in.theuniquemedia.brainbout.common.vo.*;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.service.IUser;
import in.theuniquemedia.brainbout.user.vo.DashboardVO;
import in.theuniquemedia.brainbout.user.vo.UserRegistrationRequestVO;
import in.theuniquemedia.brainbout.user.vo.UserResultVO;
import in.theuniquemedia.brainbout.user.vo.UserVO;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.*;

/**
 * Created by mahesh on 2/22/17.
 */
@Service
public class UserService implements IUser {

    @Autowired
    CommonDelegate commonDelegate;

    @Autowired
    IRepository<Participant, Integer> participantRepository;

    @Autowired
    IRepository<CompetitionParticipant, Integer> competitionParticipantRepository;

    @Autowired
    IRepository<UserProfile, Integer> userProfileRepository;

    @Autowired
    IRepository<UserProfileDetail, Integer> userProfileDetailRepository;

    @Autowired
    IRepository<UserToken, Integer> userTokenRepository;

    @Autowired
    IRepository<UserGenre, Integer> userGenreRepository;

    @Autowired
    IEmail emailService;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    @Transactional
    public void createUser(Integer companySeq, UserVO userVO) {
        Participant participant = new Participant();
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        participant.setCompany(company);
        participant.setName(userVO.getName());
        participant.setEmail(userVO.getEmail());
        participant.setStatus(AppConstants.STATUS_ACTIVE);

        participantRepository.save(participant);
    }

    @Transactional
    public void createUser(Integer companySeq, Integer userProfileSeq, UserVO userVO) {
        Participant participant = new Participant();
        Company company = commonDelegate.fetchCompanyBySeq(companySeq);
        UserProfile userProfile = fetchUserProfileById(userProfileSeq);

        participant.setCompany(company);
        participant.setUserProfile(userProfile);
        participant.setName(userVO.getName());
        participant.setFirstName(userVO.getFirstName());
        participant.setLastName(userVO.getLastName());
        participant.setEmail(userVO.getEmail());
        participant.setPhoneNo(userVO.getPhoneNo());
        participant.setStatus(AppConstants.STATUS_ACTIVE);

        participantRepository.save(participant);
    }

    @Override
    public void getUserInfo() {

    }

    @Override
    @Transactional
    public Participant fetchUserByEmail(String email) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("email", email);
        List<Participant> participantList = participantRepository.findByNamedQuery(AppConstants.FETCH_USER_BY_EMAIL, queryParams);
        if(participantList != null && participantList.size() > 0) {
            return participantList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public void addUserToCompetition(String email, Integer competitionSeq) {
        Participant participant = fetchUserByEmail(email);
        Competition competition = commonDelegate.fetchCompetitionBySeq(competitionSeq);
        CompetitionParticipant competitionParticipant = new CompetitionParticipant();
        competitionParticipant.setParticipant(participant);
        competitionParticipant.setCompanySeq(participant.getCompany().getCompanySeq());
        competitionParticipant.setCompetition(competition);
        competitionParticipant.setStartTime(new Date());
        competitionParticipant.setTimeTaken(0);
        competitionParticipant.setScore(0);
        competitionParticipant.setStatus(AppConstants.STATUS_ACTIVE);
        competitionParticipantRepository.save(competitionParticipant);
    }

    @Override
    @Transactional
    public void startCompetitionForUser(String email, Integer competitionSeq) {
        CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionSeq);
        if(competitionParticipant != null) {
            competitionParticipant.setStartTime(new Date());
            competitionParticipantRepository.merge(competitionParticipant);
        } else {
            addUserToCompetition(email, competitionSeq);
        }
    }

    @Override
    @Transactional
    public CompetitionParticipant getUserCompetitionData(String email, Integer competitionSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("email", email);
        queryParams.put("competitionSeq", competitionSeq);
        List<CompetitionParticipant> competitionParticipantList = competitionParticipantRepository.findByNamedQuery(
                AppConstants.FETCH_USER_COMPETITION_DETAILS, queryParams);
        if(competitionParticipantList != null && competitionParticipantList.size() > 0) {
            return competitionParticipantList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public UserResultVO submitCompetitionResults(String email, Integer competitionSeq, Date endDate, List<QuizOptionVO> quizOptionVOList) {
        UserResultVO userResultVO = null;
        CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionSeq);
        if(competitionParticipant != null) {
            userResultVO = fetchUserStatus(quizOptionVOList);
            if(userResultVO != null) {
                userResultVO.setToken(CommonUtil.getRandomString());
                competitionParticipant.setScore(userResultVO.getScore());
                competitionParticipant.setTimeTaken((CommonUtil.getNoOfMinutesDiff(endDate,
                        competitionParticipant.getStartTime())).intValue());
                competitionParticipant.setSubmitted('Y');
                competitionParticipant.setToken(userResultVO.getToken());
                competitionParticipantRepository.merge(competitionParticipant);
            }
        }
        return userResultVO;
    }

    @Override
    @Transactional
    public UserResultVO fetchUserStatus(List<QuizOptionVO> quizOptionVOList) {
        Integer score = 0;
        UserResultVO userResultVO = new UserResultVO();
        List<Integer> quizSeqList = new ArrayList<>();
        for(QuizOptionVO quizOptionVO: quizOptionVOList) {
            quizSeqList.add(quizOptionVO.getQuizSeq());
        }
        HashMap<Integer, Integer> correctOptions = commonDelegate.fetchQuizCorrectOptionMap(quizSeqList);
        if(correctOptions != null) {
            for(QuizOptionVO quizOptionVO: quizOptionVOList) {
                if(quizOptionVO.getOptionSeq().equals(correctOptions.get(quizOptionVO.getQuizSeq()))) {
                    score++;
                    quizOptionVO.setIsCorrect("Y");
                } else {
                    quizOptionVO.setIsCorrect("N");
                }
            }
        }
        userResultVO.setQuizOptionVOList(quizOptionVOList);
        userResultVO.setScore(score);
        return userResultVO;
    }

    @Override
    @Transactional
    public Integer fetchUserTime(Integer companySeq, Integer competitionSeq, String email) {
        Integer minTime = 0;
        CompanyCompetition companyCompetition = commonDelegate.fetchCompanyCompetitionBySeq(companySeq, competitionSeq);
        if(companyCompetition != null) {
            CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionSeq);
            if(competitionParticipant != null) {
                minTime = Integer.valueOf(companyCompetition.getTimeLimit()) * 60;
                if (competitionParticipant.getStartTime() != null) {
                    Long userTime = (Integer.valueOf(companyCompetition.getTimeLimit()) * 60) -
                            (CommonUtil.getNoOfSecondsDiff(new Date(), competitionParticipant.getStartTime()));
                    minTime = Integer.min(minTime, userTime.intValue());
                }
                Long competitionTime = CommonUtil.getNoOfSecondsDiff(companyCompetition.getEndTime(), new Date());
                minTime = Integer.min(minTime, competitionTime.intValue());
            }
        }
        return minTime;
    }

    @Override
    @Transactional
    public Integer getUserScore(Integer competitionSeq, String email) {
        CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionSeq);
        if(competitionParticipant != null) {
            if(competitionParticipant.getScore() != null) {
                return competitionParticipant.getScore();
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public CompetitionParticipant getUserStats(String token) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("token", token);
        List<CompetitionParticipant> competitionParticipantList = competitionParticipantRepository.findByNamedQuery(
                AppConstants.FETCH_USER_COMPETITION_DETAILS_BY_TOKEN, queryParams);
        if(competitionParticipantList != null && competitionParticipantList.size() > 0) {
            return competitionParticipantList.get(0);
        }
        return null;
    }

    @Transactional
    public UserProfile fetchUserProfileById(Integer userProfileSeq) {
        return userProfileRepository.findById(UserProfile.class, userProfileSeq);
    }

    @Override
    @Transactional
    public UserProfile fetchUserProfileByUserId(String userId) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("userId", userId);
        List<UserProfile> userProfileList = userProfileRepository.findByNamedQuery(AppConstants.FETCH_USER_PROFILE_BY_USER_ID,
                queryParams);
        if(userProfileList != null && userProfileList.size() > 0) {
            return userProfileList.get(0);
        }
        return null;
    }

    @Transactional
    public UserProfileDetail fetchUserProfileDetailById(Integer userProfileDetailSeq) {
        return userProfileDetailRepository.findById(UserProfileDetail.class, userProfileDetailSeq);
    }

    @Transactional
    public UserToken fetchUserTokenById(Integer userTokenSeq) {
        return userTokenRepository.findById(UserToken.class, userTokenSeq);
    }

    @Transactional
    public Integer createUserProfile(UserRegistrationRequestVO userRegistrationRequestVO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setCompanySeq(userRegistrationRequestVO.getCompanySeq());
        userProfile.setUserId(userRegistrationRequestVO.getUserVO().getEmail());

        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
        shaPasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = shaPasswordEncoder.encodePassword(userRegistrationRequestVO.getPassword(), null);
        userProfile.setPassword(encodedPassword);

        LocationMstr locationMstr = new LocationMstr();
        locationMstr.setLocationMstrSeq(userRegistrationRequestVO.getLocationSeq());
        userProfile.setLocationMstr(locationMstr);

        userProfile.setStatus(AppConstants.STATUS_ACTIVE);
        return userProfileRepository.save(userProfile);
    }


    @Override
    @Transactional
    public void createUser(UserRegistrationRequestVO userRegistrationRequestVO) {
        Integer userProfileSeq = createUserProfile(userRegistrationRequestVO);
        UserProfile userProfile = fetchUserProfileById(userProfileSeq);
        if(userProfile != null) {
            Company company = commonDelegate.fetchCompanyBySeq(userRegistrationRequestVO.getCompanySeq());
            if(company != null) {
                UserVO userVO = userRegistrationRequestVO.getUserVO();
                String fullName = userVO.getFirstName() + " " + userVO.getLastName();
                Participant participant = new Participant(company, userProfile, fullName, userVO.getFirstName(), userVO.getLastName(),
                        userVO.getEmail(), userVO.getPhoneNo(), AppConstants.STATUS_ACTIVE);
                participantRepository.save(participant);

                createUserProfileDetails(userProfileSeq);
                createUserTokenAndSendMail(userProfileSeq, userVO);
                createUserLocationAndGenre(userProfileSeq, userRegistrationRequestVO);
            }
        }
    }

    @Transactional
    private void createUserTokenAndSendMail(Integer userProfileSeq, UserVO userVO) {
        String userTokenString = CommonUtil.getRandomString();
        UserToken userToken = new UserToken();
        userToken.setUserProfile(fetchUserProfileById(userProfileSeq));
        userToken.setTokenType("REGISTRATION");
        userToken.setUserToken(userTokenString);
        userToken.setStatus(AppConstants.STATUS_ACTIVE);
        userTokenRepository.save(userToken);

        Map<String, Object> model = new HashMap<>();
        model.put("username", userVO.getFirstName());
        model.put("link", PropertiesUtil.getPropValue("app.verification.url") + userTokenString);
        String message = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "template/mails/registration.vm", model);
        EmailVO emailVO = new EmailVO(PropertiesUtil.getPropValue("email.sender"), userVO.getEmail(), message,
                PropertiesUtil.getPropValue("email.sender.label"), userVO.getFirstName() + " " + userVO.getLastName(), "Welcome to brainbout");
        emailService.sendMail(emailVO);
    }

    @Transactional
    private void createUserProfileDetails(Integer userProfileSeq) {
        UserProfileDetail userProfileDetail = new UserProfileDetail();
        userProfileDetail.setUserProfile(fetchUserProfileById(userProfileSeq));
        userProfileDetail.setUserRegistrationStatus("PENDING");
        userProfileDetail.setStatus(AppConstants.STATUS_ACTIVE);

        userProfileDetailRepository.save(userProfileDetail);
    }

    @Transactional
    private void createUserLocationAndGenre(Integer userProfileSeq, UserRegistrationRequestVO userRegistrationRequestVO) {
        UserProfileDetail userProfileDetails = fetchUserProfileDetail(userProfileSeq);
        if(userProfileDetails != null) {
            if (userRegistrationRequestVO.getLocationSeq() != null) {
                LocationMstr locationMstr = new LocationMstr();
                locationMstr.setLocationMstrSeq(userRegistrationRequestVO.getLocationSeq());
                userProfileDetails.setLocationMstr(locationMstr);
                userProfileDetailRepository.merge(userProfileDetails);
            }
        }

        UserProfile userProfile = fetchUserProfileById(userProfileSeq);
        if(userProfile != null) {
            if(userRegistrationRequestVO.getGenreSeq() != null && userRegistrationRequestVO.getGenreSeq().size() > 0) {
                for(Integer genreSeq: userRegistrationRequestVO.getGenreSeq()) {
                    Genre genre = new Genre();
                    genre.setGenreSeq(genreSeq);

                    UserGenre userGenre = new UserGenre(userProfile, genre, AppConstants.STATUS_ACTIVE);
                    userGenreRepository.save(userGenre);
                }
            }
        }
    }

    @Override
    @Transactional
    public AuthenticationVO fetchAuthenticationInfo(String userId) {
        AuthenticationVO authenticationVO = new AuthenticationVO();
        Participant participant = fetchUserByEmail(userId);
        if(participant != null) {
            Integer companySeq = participant.getCompany().getCompanySeq();
            authenticationVO.setName(participant.getName());
            authenticationVO.setEmail(participant.getEmail());
            authenticationVO.setCompanySeq(companySeq);
        }
        return authenticationVO;
    }

    @Override
    @Transactional
    public String verifyToken(String token) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("token", token);
        List<UserToken> userTokenList = userTokenRepository.findByNamedQuery(AppConstants.FETCH_USER_TOKEN, queryParams);
        if(userTokenList != null && userTokenList.size() > 0) {
            UserToken userToken = userTokenList.get(0);
            if(userToken.getStatus() == AppConstants.STATUS_INACTIVE) {
                return ResponseCode.EMAIL_ALREADY_VERIFIED;
            } else {
                verifyUserEmail(userToken.getUserProfile().getUserProfileSeq());
                userToken.setStatus(AppConstants.STATUS_INACTIVE);
                userTokenRepository.merge(userToken);
            }
        } else {
            return ResponseCode.VERIFICATION_TOKEN_NOT_FOUND;
        }
        return ResponseCode.EMAIL_VERIFICATION_SUCCESSFUL;
    }

    @Transactional
    public void verifyUserEmail(Integer userProfileSeq) {
        UserProfileDetail userProfileDetail = fetchUserProfileDetail(userProfileSeq);
        if(userProfileDetail != null) {
            userProfileDetail.setUserRegistrationStatus("VERIFIED");
            userProfileDetailRepository.merge(userProfileDetail);
        }
    }

    @Transactional
    public UserProfileDetail fetchUserProfileDetail(Integer userProfileSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("userProfileSeq", userProfileSeq);
        List<UserProfileDetail> userProfileDetailList = userProfileDetailRepository.findByNamedQuery(
                AppConstants.FETCH_USER_PROFILE_DETAIL_BY_SEQ, queryParams);
        if(userProfileDetailList != null && userProfileDetailList.size() > 0) {
            return userProfileDetailList.get(0);
        }
        return null;
    }

    @Override
    @Transactional
    public String isUserProfileVerified(String email) {
        Participant participant = fetchUserByEmail(email);
        if(participant != null) {
            UserProfileDetail userProfileDetail = fetchUserProfileDetail(participant.getUserProfile().getUserProfileSeq());
            if(userProfileDetail != null) {
                if(!userProfileDetail.getUserRegistrationStatus().equals("VERIFIED")) {
                    return ResponseCode.LOGIN_VERIFICATION_PENDING;
                }
            }
        } else {
            return ResponseCode.LOGIN_USER_NOT_EXISTS;
        }
        return ResponseCode.VERIFIED_USER;
    }

    @Override
    @Transactional
    public boolean verifyUserEmail(String email, Integer companySeq) {
        List<String> companyDomainList = commonDelegate.fetchCompanyDomainList(companySeq);
        if(companyDomainList != null && companyDomainList.size() > 0) {
            if(email != null) {
                String emailDomain = email.split("@")[1];
                for(String domain: companyDomainList) {
                    if(emailDomain.equalsIgnoreCase(domain)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    @Transactional
    public Long fetchCompetitionParticipantCount(Integer companySeq, Integer competitionSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        queryParams.put("competitionSeq", competitionSeq);
        Long count = competitionParticipantRepository.findCountByNamedQuery(AppConstants.FETCH_COMPETITION_COUNT, queryParams);
        if(count != null) {
            return count;
        }
        return 0L;
    }

    @Transactional
    public DashboardVO fetchUserDashboard(Integer companySeq, String email) {
        DashboardVO dashboardVO = new DashboardVO();
        Integer userLocation = fetchUserLocation(email);
        if(userLocation != null) {
            List<CompetitionVO> competitionVOList = commonDelegate.fetchActiveCompetitionList(companySeq, userLocation,
                    AppConstants.COMPETITION_STATUS_ACTIVE);
            dashboardVO.setCurrentCompetitionList(fetchCompetitionStatus(companySeq, email, competitionVOList));

            competitionVOList = commonDelegate.fetchActiveCompetitionList(companySeq, userLocation,
                    AppConstants.COMPETITION_STATUS_CLOSED);
            dashboardVO.setPastCompetitionList(fetchCompetitionStatus(companySeq, email, competitionVOList));

            competitionVOList = commonDelegate.fetchActiveCompetitionList(companySeq, userLocation,
                    AppConstants.COMPETITION_STATUS_UPCOMING);
            dashboardVO.setUpcomingCompetitionList(fetchCompetitionStatus(companySeq, email, competitionVOList));
        }
        return dashboardVO;
    }

    @Transactional
    private List<UserCompetitionVO> fetchCompetitionStatus(Integer companySeq, String email, List<CompetitionVO> competitionVOList) {
        List<UserCompetitionVO> userCompetitionVOList = new ArrayList();
        if(competitionVOList != null && competitionVOList.size() > 0) {
            for(CompetitionVO competitionVO: competitionVOList) {
                UserCompetitionVO UserCompetitionVO = new UserCompetitionVO();
                competitionVO.setParticipants(fetchCompetitionParticipantCount(companySeq,
                        competitionVO.getCompetitionSeq()).intValue());
                UserCompetitionVO.setCompetitionVO(competitionVO);
                CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionVO.getCompetitionSeq());
                if(competitionParticipant != null) {
                    if(competitionParticipant.getSubmitted() == 'Y') {
                        UserCompetitionVO.setUserStatus("submitted");
                    } else {
                        UserCompetitionVO.setUserStatus("PARTICIPATED");
                        UserCompetitionVO.setUserTimeLeft(fetchUserTime(companySeq, competitionVO.getCompetitionSeq(), email));
                    }
                } else {
                    UserCompetitionVO.setUserStatus("NP");
                    UserCompetitionVO.setUserTimeLeft(competitionVO.getTotalTime());
                }
                userCompetitionVOList.add(UserCompetitionVO);
            }
        }
        return userCompetitionVOList;
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchCompanyTopPlayers(Integer companySeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        List<CommonDetailsVO> commonDetailsVOList = (List<CommonDetailsVO>) competitionParticipantRepository.findVOByNamedQuery(CommonDetailsVO.class,
                AppConstants.FETCH_COMPANY_TOP_PARTICIPANTS, queryParams);
        return commonDetailsVOList;
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchCompetitionTopPlayers(Integer companySeq, Integer competitionSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        queryParams.put("competitionSeq", competitionSeq);
        List<CommonDetailsVO> commonDetailsVOList = (List<CommonDetailsVO>) competitionParticipantRepository.findVOByNamedQuery(CommonDetailsVO.class,
                AppConstants.FETCH_COMPETITION_TOP_PARTICIPANTS, queryParams);
        return commonDetailsVOList;
    }

    @Override
    @Transactional
    public List<CommonDetailsVO> fetchTopPlayersByLocation(String userId, Integer companySeq, Integer competitionSeq) {
        Integer userLocation = fetchUserLocation(userId);
            return fetchCompetitionTopPlayersByLocation(companySeq, competitionSeq,
                    userLocation);
    }

    @Transactional
    public List<CommonDetailsVO> fetchCompetitionTopPlayersByLocation(Integer companySeq, Integer competitionSeq, Integer locationSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("companySeq", companySeq);
        queryParams.put("competitionSeq", competitionSeq);
        queryParams.put("locationSeq", locationSeq);
        List<CommonDetailsVO> commonDetailsVOList = (List<CommonDetailsVO>) competitionParticipantRepository.findVOByNamedQuery(CommonDetailsVO.class,
                AppConstants.FETCH_COMPETITION_TOP_PARTICIPANTS_BY_LOCATION, queryParams);
        return commonDetailsVOList;
    }

    @Override
    @Transactional
    public List<UserGenre> fetchUserGenre(String userId) {
        UserProfile userProfile = fetchUserProfileByUserId(userId);
        if(userProfile != null) {
            return fetchUserGenre(userProfile.getUserProfileSeq());
        }
        return null;
    }

    @Transactional
    public List<UserGenre> fetchUserGenre(Integer userProfileSeq) {
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put("userProfileSeq", userProfileSeq);
        List<UserGenre> userGenreList = userGenreRepository.findByNamedQuery(AppConstants.FETCH_USER_GENRE, queryParams);
        return userGenreList;
    }

    @Transactional
    private Integer fetchUserLocation(String userId) {
        UserProfile userProfile = fetchUserProfileByUserId(userId);
        if(userProfile != null) {
            if(userProfile.getLocationMstr() != null) {
                return userProfile.getLocationMstr().getLocationMstrSeq();
            }
        }
        return null;
    }

    @Override
    @Transactional
    public boolean verifyExistingUserProfile(String userId) {
        UserProfile userProfile = fetchUserProfileByUserId(userId);
        if(userProfile == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public CompetitionDetailsVO fetchCompetitionDetails(Integer companySeq, Integer competitionSeq) {
        return commonDelegate.fetchCompetitionDetails(companySeq, competitionSeq);
    }
}
