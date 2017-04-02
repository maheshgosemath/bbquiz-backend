package in.theuniquemedia.brainbout.user.service;

import in.theuniquemedia.brainbout.common.domain.CompetitionParticipant;
import in.theuniquemedia.brainbout.common.domain.Participant;
import in.theuniquemedia.brainbout.common.domain.UserGenre;
import in.theuniquemedia.brainbout.common.domain.UserProfile;
import in.theuniquemedia.brainbout.common.vo.AuthenticationVO;
import in.theuniquemedia.brainbout.common.vo.CommonDetailsVO;
import in.theuniquemedia.brainbout.common.vo.CompetitionDetailsVO;
import in.theuniquemedia.brainbout.common.vo.UserCompetitionVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.vo.DashboardVO;
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
    UserProfile fetchUserProfileByUserId(String userId);

    @Transactional
    void createUser(UserRegistrationRequestVO userRegistrationRequestVO);

    @Transactional
    AuthenticationVO fetchAuthenticationInfo(String userId);

    @Transactional
    String verifyToken(String token);

    @Transactional
    String isUserProfileVerified(String email);

    @Transactional
    boolean verifyUserEmail(String email, Integer companySeq);

    @Transactional
    Long fetchCompetitionParticipantCount(Integer companySeq, Integer competitionSeq);

    @Transactional
    DashboardVO fetchUserDashboard(Integer companySeq, String email);

    @Transactional
    List<CommonDetailsVO> fetchCompanyTopPlayers(Integer companySeq);

    @Transactional
    List<CommonDetailsVO> fetchCompetitionTopPlayers(Integer companySeq, Integer competitionSeq);

    @Transactional
    List<CommonDetailsVO> fetchTopPlayersByLocation(String userId, Integer companySeq, Integer competitionSeq);

    @Transactional
    List<UserGenre> fetchUserGenre(String userId);

    @Transactional
    boolean verifyExistingUserProfile(String userId);

    @Transactional
    CompetitionDetailsVO fetchCompetitionDetails(Integer companySeq, Integer competitionSeq);
}
