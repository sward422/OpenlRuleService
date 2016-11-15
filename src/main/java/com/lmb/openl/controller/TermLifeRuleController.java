package com.lmb.openl.controller;

import com.lmb.openl.app.OpenlServiceApp;
import com.lmb.openl.domain.TermLifeRuleDto;
import com.lmb.openl.exception.RuleServiceException;
import com.lmb.openl.service.IRule;
import com.lmb.openl.service.TermLifeRuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by n0292928 on 11/9/16.
 */
@RestController
@RequestMapping(value="termlifeRule")
public class TermLifeRuleController {
    private static final Logger logger = LoggerFactory.getLogger(MedicalRuleController.class);

    @Autowired
    private OpenlServiceApp     app;
    @Autowired
    private TermLifeRuleService ruleService;

    //{42,"M",true,"WC",true,false,"NY","LIFEPLAN2"};
    // 61-70	F	TRUE	BC	TRUE	FALSE	NY	LIFEPLAN3

    @RequestMapping(value="/rate/{age}/{gender}/{smoker}/{emptype}/{cihist}/{retired}/{state}/{plan}", method = RequestMethod.GET)
    public ResponseEntity<String> ratePremium(@PathVariable Integer age, @PathVariable String gender, @PathVariable Boolean smoker,
                                              @PathVariable String emptype, @PathVariable Boolean cihist,@PathVariable Boolean retired,
                                              @PathVariable String state, @PathVariable String plan) throws RuleServiceException {
        TermLifeRuleDto     termLifeRuleDto=null;
        Object              objResponse = null;

        if ( !validateParameters(age,gender,smoker,emptype, cihist, retired, state, plan) ) {
            IllegalArgumentException argEx = new IllegalArgumentException(getValidationErrMsg(age,gender,smoker,emptype, cihist, retired, state, plan));
            throw new RuleServiceException(argEx, null);
        }

        termLifeRuleDto = initTermLifeRuleDto(age,gender,smoker,emptype, cihist, retired, state, plan);
        objResponse = ruleService.execTermLifeRule(app.getRulesPathFileName(),new Object[]{age,gender,smoker,emptype,cihist,retired,state,plan});
        if (objResponse != null) {
            logger.info("Response Value = [{}]", String.valueOf(objResponse));
            termLifeRuleDto.setPremium(Double.parseDouble(objResponse.toString()));

            return new ResponseEntity(termLifeRuleDto, HttpStatus.OK);
        }
        termLifeRuleDto.setPremium(0.0);
        return new ResponseEntity(termLifeRuleDto, HttpStatus.NO_CONTENT);
    }

    private boolean validateParameters(Integer age,String gender,Boolean smoker,String emptype,
                                       Boolean cihist,Boolean retired,String state,String plan) {
        boolean  bIsValid = true;
        if ( (age < 20 || age > 100) ||
             (!StringUtils.containsAny(gender, new char[]{'M', 'F'})) ||
             (StringUtils.isEmpty(emptype) || emptype.length() != 2 ) ||
             (StringUtils.isEmpty(state)   || state.length() != 2 )   ||
             (StringUtils.isEmpty(plan)    || !plan.startsWith("LIFEPLAN") )) {
                 bIsValid=false;
        }
        return bIsValid;
    }

    private String getValidationErrMsg(int parmAge, String parmGender, boolean parmSmoker, String parmEmpType, boolean parmCIhist,
                                         boolean parmRetired, String parmState,String parmPlan ) {
        StringBuffer errMsg = new StringBuffer("Invalid argument value(s). Values given: age = ");
        errMsg.append(parmAge);  errMsg.append(", gender ");    errMsg.append(parmGender);
        errMsg.append(", smoker ");  errMsg.append(parmSmoker); errMsg.append(", empType ");
        errMsg.append(parmEmpType);      errMsg.append(", ciHist ");  errMsg.append(parmCIhist);
        errMsg.append(", retired "); errMsg.append(parmRetired);      errMsg.append(", state ");
        errMsg.append(parmState);  errMsg.append(", plan "); errMsg.append(parmPlan);

        return errMsg.toString();
    }

    private TermLifeRuleDto initTermLifeRuleDto(int age, String gender,boolean smoker,String emptype, boolean cihist, boolean retired, String state, String plan) {
        TermLifeRuleDto      termLifeRuleDto = new TermLifeRuleDto();
        termLifeRuleDto.setAge(age);
        termLifeRuleDto.setGender(gender);
        termLifeRuleDto.setSmoker(smoker);
        termLifeRuleDto.setCiHist(cihist);
        termLifeRuleDto.setEmpType(emptype);
        termLifeRuleDto.setRetired(retired);
        termLifeRuleDto.setState(state);
        termLifeRuleDto.setTermLifePlan(plan);
        termLifeRuleDto.setRuleName(IRule.RULE_LIFE);

        return termLifeRuleDto;
    }
}
