package in.theuniquemedia.brainbout.user.service;

import in.theuniquemedia.brainbout.common.domain.CompetitionParticipant;
import in.theuniquemedia.brainbout.common.domain.Participant;
import in.theuniquemedia.brainbout.common.vo.AuthenticationVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.vo.UserRegistrationRequestVO;
import in.theuniquemedia.brainbout.user.vo.UserResultVO;
import in.theuniquemedia.brainbout.user.vo.UserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
public interface IUser {
    public void createUser(Integer companySeq, UserVO userVO);
    public void getUserInfo();

    @Transactional
    Participant fetchUserByEmail(String email);

    @Transactional
    void addUserToCompetition(String email, Integer competitionSeq);

    @Transactional
    void startCompetitionForUser(String email, Integer competitionSeq);

    @Transactional
    CompetitionParticipant getUserCompetitionData(String email, Integer competitionSeq);

    @Transactional
    UserResultVO submitCompetitionResults(String email, Integer competitionSeq, Date endDate, List<QuizOptionVO> quizOptionVOList);

    @Transactional
    UserResultVO fetchUserStatus(List<QuizOptionVO> quizOptionVOList);

    @Transactional
    Integer fetchUserTime(Integer companySeq, Integer competitionSeq, String email);

    @Transactional
    Integer getUserScore(Integer competitionSeq, String email);

    @Transactional
    CompetitionParticipant getUserStats(String token);

    @Transactional
    void createUser(UserRegistrationRequestVO userRegistrationRequestVO);

    @Transactional
    AuthenticationVO fetchAuthenticationInfo(String userId);

    @Transactional
    boolean verifyUserEmail(String email, Integer companySeq);
}
