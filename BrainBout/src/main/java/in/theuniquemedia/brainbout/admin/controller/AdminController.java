package in.theuniquemedia.brainbout.admin.controller;

import in.theuniquemedia.brainbout.admin.service.IAdmin;
import in.theuniquemedia.brainbout.admin.vo.*;
import in.theuniquemedia.brainbout.common.vo.CompetitionVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

/**
 * Created by mahesh on 2/27/17.
 */
@Controller
public class AdminController {

    @Autowired
    IAdmin adminService;

    @RequestMapping(value="createcompany", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> createCompany(@RequestBody CorporateVO addCorporateVO) {
        AddResponseVO addResponseVO = new AddResponseVO();
        try {
            adminService.createCompany(addCorporateVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="createcompetition", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> createCompetition(@RequestBody AddCompetitionVO addCompetitionVO) {
        AddResponseVO addResponseVO = new AddResponseVO();
        try {
            adminService.addCompetition(addCompetitionVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="createcompanycompetition", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> createCompanyCompetition(@RequestBody AddCompanyCompetitionVO addCompanyCompetitionVO) {
        AddResponseVO addResponseVO = new AddResponseVO();
        try {
            adminService.addCompanyCompetition(addCompanyCompetitionVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="createquestion", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> createQuestion(MultipartHttpServletRequest request) {
        AddResponseVO addResponseVO = new AddResponseVO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AddQuestionVO addQuestionVO = objectMapper.readValue(objectMapper.readTree(request.getParameter("addQuestionVO")), AddQuestionVO.class);
            if(request.getFileNames() != null) {
                Iterator<String> itr = request.getFileNames();
                if (itr.hasNext()) {
                    addQuestionVO.setMultipartFile(request.getFile(itr.next()));
                }
            }
            adminService.createQuestion(addQuestionVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="companylist")
    public @ResponseBody ResponseEntity<String> fetchCompanyList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("companyList", adminService.fetchCompanyList());
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @RequestMapping(value="competitionlist")
    public @ResponseBody ResponseEntity<String> fetchCompetitionList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("competitionList", adminService.fetchCompanyCompetitionList());
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @RequestMapping(value="questionlist")
    public @ResponseBody ResponseEntity<String> fetchQuestionList() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("questionList", adminService.fetchQuestionList());
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @RequestMapping(value="companydetails")
    public @ResponseBody ResponseEntity<String> fetchCompanyDetails() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("companydetails", adminService.fetchcompanyDetails());
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @RequestMapping(value="genredetails")
    public @ResponseBody ResponseEntity<String> fetchGenreDetails() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("genredetails", adminService.fetchGenreDetails());
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @RequestMapping(value="updatecompany", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> updateCompany(@RequestBody CorporateVO addCorporateVO) {
        AddResponseVO addResponseVO = new AddResponseVO();
        try {
            adminService.updateCompany(addCorporateVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="questioninfo")
    public @ResponseBody ResponseEntity<AddQuestionVO> fetchQuizDetails(@RequestParam Integer quizSeq) {
        AddQuestionVO addCorporateVO = new AddQuestionVO();
        try {
            addCorporateVO = adminService.fetchQuizDetails(quizSeq);
            return new ResponseEntity<>(addCorporateVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addCorporateVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="updatequestion", method= RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<AddResponseVO> updateQuestion(MultipartHttpServletRequest request) {
        AddResponseVO addResponseVO = new AddResponseVO();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            AddQuestionVO addQuestionVO = objectMapper.readValue(objectMapper.readTree(request.getParameter("addQuestionVO")), AddQuestionVO.class);
            if(request.getFileNames() != null) {
                Iterator<String> itr = request.getFileNames();
                if (itr.hasNext()) {
                    addQuestionVO.setMultipartFile(request.getFile(itr.next()));
                }
            }
            adminService.updateQuestion(addQuestionVO);
            addResponseVO.setStatus("success");
            return new ResponseEntity<>(addResponseVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(addResponseVO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
