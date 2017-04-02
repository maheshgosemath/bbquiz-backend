package in.theuniquemedia.brainbout.user.controller;

import in.theuniquemedia.brainbout.common.constants.AppConstants;
import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.CompetitionParticipant;
import in.theuniquemedia.brainbout.common.domain.Participant;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.common.vo.AuthenticationVO;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import in.theuniquemedia.brainbout.user.service.IUser;
import in.theuniquemedia.brainbout.user.vo.*;
import net.sf.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by mahesh on 2/24/17.
 */
@Controller
public class UserController {

    @Autowired
    IUser userService;

    @Autowired
    CommonDelegate commonDelegate;

    @RequestMapping(value="register", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UserRegistrationVO> registerUser(@RequestBody UserCompetitionDetails userCompetitionDetails) {
        UserRegistrationVO userRegistrationVO = new UserRegistrationVO();
        try {
            if(userCompetitionDetails != null) {
                Integer companySeq = userCompetitionDetails.getCompanySeq();
                Integer competitionSeq = userCompetitionDetails.getCompetitionSeq();
                String email = userCompetitionDetails.getEmail();
                String name = userCompetitionDetails.getName();
                Participant participant = userService.fetchUserByEmail(email);
                if (participant == null) {
                    UserVO userVO = new UserVO(name, email);
                    userService.createUser(companySeq, userVO);
                } else {
                    userRegistrationVO.setUserStatus(AppConstants.USER_NOT_ELIGIBLE);
                }
                CompetitionParticipant competitionParticipant = userService.getUserCompetitionData(email, competitionSeq);
                if (competitionParticipant == null) {
                    userService.addUserToCompetition(email, competitionSeq);
                } else {
                    if(competitionParticipant.getSubmitted() == 'Y') {
                        userRegistrationVO.setUserStatus(AppConstants.USER_STATUS_SUBMITTED);
                        return new ResponseEntity<>(userRegistrationVO, HttpStatus.OK);
                    }
                }
                Integer timeLeft = userService.fetchUserTime(companySeq, competitionSeq, email);
                userRegistrationVO.setTimeLeft(timeLeft);
            }
            return new ResponseEntity<>(userRegistrationVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(userRegistrationVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="start", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<String> startCompetition(@RequestBody UserCompetitionDetails userCompetitionDetails) {
        try {
            JSONObject jsonObject = new JSONObject();
            userService.startCompetitionForUser(userCompetitionDetails.getEmail(), userCompetitionDetails.getCompetitionSeq());
            jsonObject.put("status", "success");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="submit", method=RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<UserResultVO> getUserScore(@RequestBody QuizSubmissionVO quizSubmissionVO) {
        UserResultVO userResultVO = new UserResultVO();
        try {
            userResultVO = userService.submitCompetitionResults(quizSubmissionVO.getEmail(), quizSubmissionVO.getCompetitionSeq(),
                    new Date(), quizSubmissionVO.getQuizOptionVOList());
            return new ResponseEntity<>(userResultVO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(userResultVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="quizlist")
    public @ResponseBody ResponseEntity<QuizResponseVO> fetchQuizList(@RequestBody QuizRequestVO quizRequestVO) {
        QuizResponseVO quizResponseVO = new QuizResponseVO();
        try {
            String userId = quizRequestVO.getEmail();
            List<Integer> quizSeqVOList = quizRequestVO.getQuizSeqList();
            if(quizSeqVOList == null) {
                quizSeqVOList = commonDelegate.fetchQuizList(userId, 2);
            }
            if(quizSeqVOList != null) {
                List<QuizVO> quizVOList = commonDelegate.fetchQuizVOList(quizSeqVOList);
                if(quizVOList != null) {
                    quizResponseVO.setQuizVOList(quizVOList);
                }
            }
            Integer timeLeft = userService.fetchUserTime(quizRequestVO.getCompanySeq(), quizRequestVO.getCompetitionSeq(),
                    quizRequestVO.getEmail());
            quizResponseVO.setTimeLeft(timeLeft);
            return new ResponseEntity<>(quizResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(quizResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="userstats", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<UserResultVO> getUserStats(HttpServletRequest request) {
        UserResultVO userResultVO = new UserResultVO();
        try {
            CompetitionParticipant competitionParticipant = userService.getUserCompetitionData(request.getParameter("email"),
                    Integer.parseInt(request.getParameter("competitionSeq")));
            if(competitionParticipant != null) {
                userResultVO.setScore(competitionParticipant.getScore());
                userResultVO.setToken(competitionParticipant.getToken());
            }
            return new ResponseEntity<>(userResultVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(userResultVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("app")
    public ModelAndView getUserAppStatus(HttpServletRequest request, ModelMap model) {
        String userAgent = request.getHeader("User-Agent");

        String token = request.getParameter("token");
        Integer userScore = 0;
        if(token != null && token.length() > 0) {
            CompetitionParticipant competitionParticipant = userService.getUserStats(token);
            if(competitionParticipant != null) {
                userScore = competitionParticipant.getScore();
            }
        }
        if (userAgent!=null && (userAgent.contains(AppConstants.FACEBOOK_USER_AGENT)
                || userAgent.contains(AppConstants.LINKEDIN_USER_AGENT)
                || userAgent.contains(AppConstants.TWITTER_USER_AGENT))
                || userAgent.contains(AppConstants.WHATSAPP_AGENT)) {
            model.put("points", userScore);
            return new ModelAndView("socialapp");
        }
        return new ModelAndView("app");
    }

    @RequestMapping(value="signup")
    public @ResponseBody ResponseEntity<String> createUserProfile(@RequestBody UserRegistrationRequestVO userRegistrationRequestVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            boolean validateDomain = userService.verifyUserEmail(userRegistrationRequestVO.getUserVO().getEmail(), userRegistrationRequestVO.getCompanySeq());
            if(validateDomain) {
                if(!userService.verifyExistingUserProfile(userRegistrationRequestVO.getUserVO().getEmail())) {
                    userService.createUser(userRegistrationRequestVO);
                    jsonObject.put("status", "success");
                } else {
                    jsonObject.put("msg", "User with this email already exists");
                    jsonObject.put("status", "error");
                }
            } else {
                jsonObject.put("msg", "This email is not allowed in selected company");
                jsonObject.put("status", "error");
            }
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            jsonObject.put("status", "error");
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="userinfo")
    public @ResponseBody ResponseEntity<AuthenticationVO> fetchUserInfo() {
        AuthenticationVO authenticationVO = new AuthenticationVO();
        try {
            String userId = CommonUtil.getUserName();
            authenticationVO = userService.fetchAuthenticationInfo(userId);
            return new ResponseEntity<>(authenticationVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(authenticationVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="dashboard")
    public @ResponseBody ResponseEntity<String> fetchUserInfo(@RequestBody UserVO userVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            String userId = CommonUtil.getUserName();
            jsonObject.put("dashboard", userService.fetchUserDashboard(userVO.getCompanySeq(), userId));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="companyleaderboard")
    public @ResponseBody ResponseEntity<String> fetchCompanyLeaderBoard(Integer companySeq) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("leaderboard", userService.fetchCompanyTopPlayers(companySeq));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="competitionleaderboard")
    public @ResponseBody ResponseEntity<String> fetchCompetitionLeaderBoard(Integer companySeq, Integer competitionSeq) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("leaderboard", userService.fetchCompetitionTopPlayers(companySeq, competitionSeq));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="competitionleaderboardlocation")
    public @ResponseBody ResponseEntity<String> fetchCompetitionLeaderBoardByLocation(Integer companySeq, Integer competitionSeq, String userId) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("leaderboard", userService.fetchTopPlayersByLocation(userId, companySeq, competitionSeq));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="validateuser", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<String> validateUserAccount(@RequestParam String email) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", userService.isUserProfileVerified(email));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="emailverify", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<String> verifyUserAccount(@RequestBody UserTokenVO userTokenVO) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", userService.verifyToken(userTokenVO.getToken()));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="usercompetitiondetails")
    public @ResponseBody ResponseEntity<String> fetchCompanyCompetitionDetails(Integer companySeq, Integer competitionSeq) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("competitiondetails", userService.fetchCompetitionDetails(companySeq, competitionSeq));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
