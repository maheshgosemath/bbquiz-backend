package in.theuniquemedia.brainbout.user.service.impl;

import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.*;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.service.IUser;
import in.theuniquemedia.brainbout.user.vo.UserRegistrationRequestVO;
import in.theuniquemedia.brainbout.user.vo.UserResultVO;
import in.theuniquemedia.brainbout.user.vo.UserVO;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
                if(quizOptionVO.getOptionSeq() == correctOptions.get(quizOptionVO.getQuizSeq())) {
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
                minTime = Integer.valueOf(companyCompetition.getTimeLimit());
                if (competitionParticipant.getStartTime() != null) {
                    Long userTime = Integer.valueOf(companyCompetition.getTimeLimit()) -
                            (CommonUtil.getNoOfMinutesDiff(new Date(), competitionParticipant.getStartTime()));
                    minTime = Integer.min(minTime, userTime.intValue());
                }
                Long competitionTime = CommonUtil.getNoOfMinutesDiff(companyCompetition.getEndTime(), new Date());
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
        userProfile.setCompnaySeq(userRegistrationRequestVO.getCompanySeq());
        userProfile.setUserId(userRegistrationRequestVO.getUserVO().getEmail());

        ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);shaPasswordEncoder.setEncodeHashAsBase64(true);
        String encodedPassword = shaPasswordEncoder.encodePassword(userRegistrationRequestVO.getPassword(), null);
        userProfile.setPassword(encodedPassword);

        userProfile.setStatus(AppConstants.STATUS_INACTIVE);
        return userProfileRepository.save(userProfile);
    }


    public void createUser(UserRegistrationRequestVO userRegistrationRequestVO) {
        Integer userProfileSeq = createUserProfile(userRegistrationRequestVO);

    }

}
