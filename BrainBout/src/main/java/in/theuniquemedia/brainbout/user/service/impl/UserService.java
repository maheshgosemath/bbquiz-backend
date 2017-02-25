package in.theuniquemedia.brainbout.user.service.impl;

import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.Company;
import in.theuniquemedia.brainbout.common.domain.Competition;
import in.theuniquemedia.brainbout.common.domain.CompetitionParticipant;
import in.theuniquemedia.brainbout.common.domain.Participant;
import in.theuniquemedia.brainbout.common.repository.IRepository;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.service.IUser;
import in.theuniquemedia.brainbout.user.vo.UserStatusVO;
import in.theuniquemedia.brainbout.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public UserStatusVO submitCompetitionResults(String email, Integer competitionSeq, Date endDate, List<QuizOptionVO> quizOptionVOList) {
        UserStatusVO userStatusVO = null;
        CompetitionParticipant competitionParticipant = getUserCompetitionData(email, competitionSeq);
        if(competitionParticipant != null) {
            userStatusVO = fetchUserStatus(quizOptionVOList);
            if(userStatusVO != null) {
                competitionParticipant.setScore(userStatusVO.getScore());
                competitionParticipant.setTimeTaken((CommonUtil.getNoOfMinutesDiff(endDate,
                        competitionParticipant.getStartTime())).intValue());
                competitionParticipant.setSubmitted('Y');
                competitionParticipantRepository.merge(competitionParticipant);
            }
        }
        return userStatusVO;
    }

    @Override
    @Transactional
    public UserStatusVO fetchUserStatus(List<QuizOptionVO> quizOptionVOList) {
        Integer score = 0;
        UserStatusVO userStatusVO = new UserStatusVO();
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
        userStatusVO.setQuizOptionVOList(quizOptionVOList);
        userStatusVO.setScore(score);
        return userStatusVO;
    }
}
