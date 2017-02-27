package in.theuniquemedia.brainbout.quiz.controller;

import in.theuniquemedia.brainbout.quiz.service.IQuiz;
import in.theuniquemedia.brainbout.quiz.vo.QuizVO;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by mahesh on 2/22/17.
 */
@Controller
public class QuizController {

    @Autowired
    private IQuiz quizService;

    @RequestMapping("quizinfo")
    public @ResponseBody
    ResponseEntity<QuizVO> fetchQuizInfo(@RequestParam(value="quizseq") Integer quizSeq) {
        return new ResponseEntity<>(quizService.fetchQuiz(quizSeq), HttpStatus.OK);
    }
}
