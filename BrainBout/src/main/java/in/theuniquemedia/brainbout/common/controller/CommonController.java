package in.theuniquemedia.brainbout.common.controller;

import in.theuniquemedia.brainbout.common.service.ICommon;
import in.theuniquemedia.brainbout.common.vo.CompanyCompetitionVO;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mahesh on 2/23/17.
 */
@Controller
public class CommonController {

    @Autowired
    ICommon commonService;

    @RequestMapping(value="login")
    public @ResponseBody
    ResponseEntity<CompanyCompetitionVO> fetchCompetitionStatus(HttpServletRequest request) {
        try {
            CompanyCompetitionVO companyCompetitionVO = commonService.fetchCompanyCompetitionVO(request.getParameter("ref"));
            return new ResponseEntity<>(companyCompetitionVO, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new CompanyCompetitionVO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value="companylocations")
    public @ResponseBody ResponseEntity<String> fetchLocationDetails(@RequestParam Integer companySeq) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("companylocations", commonService.fetchCompanyLocationDetails(companySeq));
            return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }
}
