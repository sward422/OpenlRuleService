package com.lmb.openl.controller;

import com.lmb.openl.app.OpenlServiceApp;
import com.lmb.openl.domain.MedicalPremiumRuleDto;
import com.lmb.openl.exception.RuleServiceException;
import com.lmb.openl.service.IRule;
import com.lmb.openl.service.MedicalRuleService;
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
@RequestMapping(value = "medicalRule")
public class MedicalRuleController {
    private static final Logger logger = LoggerFactory.getLogger(MedicalRuleController.class);

    @Autowired
    private OpenlServiceApp     app;
    @Autowired
    private MedicalRuleService ruleService;

    @RequestMapping(value="/rate/{age}/{gender}/{smoker}", method = RequestMethod.GET)
    public ResponseEntity<String> ratePremium(@PathVariable Integer age,@PathVariable String gender,@PathVariable Boolean smoker) throws RuleServiceException {
        Object  objResponse                 = null;
        MedicalPremiumRuleDto   medRuleDto  = new MedicalPremiumRuleDto();

        if ( !validateParameters(age,gender,smoker) ) {
            IllegalArgumentException argEx = new IllegalArgumentException("Invalid argument value(s). Values given: age = "  + age +
                                                                                                                 ", gender " + gender + ", smoker " + smoker);
            throw new RuleServiceException(argEx, null);
        }

        medRuleDto.setAge(age);
        medRuleDto.setGender(gender);
        medRuleDto.setSmoker(smoker);
        medRuleDto.setRulename(IRule.RULE_MEDICAL);
        objResponse = ruleService.execMedicalRule(app.getRulesPathFileName(),new Object[]{age,gender,smoker});
        if (objResponse != null) {
            logger.info("Response Value = [{}]", String.valueOf(objResponse));
            medRuleDto.setPremium(Double.parseDouble(objResponse.toString()));
            return new ResponseEntity(medRuleDto, HttpStatus.OK);
        }

        medRuleDto.setPremium(0.0);
        return new ResponseEntity(medRuleDto, HttpStatus.NO_CONTENT);
    }

    private boolean validateParameters(Integer parmAge, String parmGender, Boolean parmSmoker ) {
        boolean  bIsValid = true;
        if ( (parmAge < 20 || parmAge > 100) ||
             (!StringUtils.containsAny(parmGender, new char[]{'M', 'F'}))) {
             bIsValid=false;
        }
        return bIsValid;
    }
}
