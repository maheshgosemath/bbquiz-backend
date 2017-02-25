package in.theuniquemedia.brainbout.user.controller;

import in.theuniquemedia.brainbout.common.delegate.CommonDelegate;
import in.theuniquemedia.brainbout.common.domain.CompanyCompetition;
import in.theuniquemedia.brainbout.common.domain.CompetitionParticipant;
import in.theuniquemedia.brainbout.common.domain.Participant;
import in.theuniquemedia.brainbout.common.util.CommonUtil;
import in.theuniquemedia.brainbout.quiz.vo.QuizOptionVO;
import in.theuniquemedia.brainbout.user.service.IUser;
import in.theuniquemedia.brainbout.user.vo.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
                }
                CompetitionParticipant competitionParticipant = userService.getUserCompetitionData(email, competitionSeq);
                if (competitionParticipant == null) {
                    userService.addUserToCompetition(email, competitionSeq);
                    competitionParticipant = userService.getUserCompetitionData(email, competitionSeq);
                }
                CompanyCompetition companyCompetition = commonDelegate.fetchCompanyCompetitionBySeq(companySeq,
                        competitionSeq);
                if (competitionParticipant.getStartTime() == null) {
                    if (companyCompetition != null) {
                        userRegistrationVO.setTimeLeft(Integer.parseInt(companyCompetition.getTimeLimit()));
                    }
                } else {
                    Integer timeLeft = Integer.parseInt(companyCompetition.getTimeLimit()) -
                            (CommonUtil.getNoOfMinutesDiff(new Date(), competitionParticipant.getStartTime())).intValue();
                    userRegistrationVO.setTimeLeft(timeLeft);
                }
                List<Integer> quizSeqList = commonDelegate.fetchQuizList(competitionSeq);
                if (quizSeqList != null) {
                    userRegistrationVO.setQuizVOList(commonDelegate.fetchQuizVOList(quizSeqList));
                }
            }
            return new ResponseEntity<>(userRegistrationVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserRegistrationVO(), HttpStatus.INTERNAL_SERVER_ERROR);
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
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="submit", method=RequestMethod.POST)
    public @ResponseBody
    UserStatusVO getUserScore(@RequestBody QuizSubmissionVO quizSubmissionVO) {
        return userService.submitCompetitionResults(quizSubmissionVO.getEmail(), quizSubmissionVO.getCompetitionSeq(),
                new Date(), quizSubmissionVO.getQuizOptionVOList());
    }
}
