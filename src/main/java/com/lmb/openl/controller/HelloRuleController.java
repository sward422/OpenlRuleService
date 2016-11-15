package com.lmb.openl.controller;

import com.lmb.openl.app.OpenlServiceApp;
import com.lmb.openl.domain.HelloRule;
import com.lmb.openl.domain.HelloRuleDto;
import com.lmb.openl.exception.RuleServiceException;
import com.lmb.openl.service.HelloRuleService;
import com.lmb.openl.service.IRule;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by n0292928 on 11/9/16.
 */
@RestController
@RequestMapping(value = "helloRule")
public class HelloRuleController {
    private static final Logger logger = LoggerFactory.getLogger(HelloRuleController.class);

    @Autowired
    private OpenlServiceApp     app;
    @Autowired
    private HelloRuleService    ruleService;

    // Define Get Endpoint
    @RequestMapping(value="/greeting/{hourOfDay}", method = RequestMethod.GET )
    public ResponseEntity<HelloRule> helloRule(@PathVariable Integer hourOfDay ) throws RuleServiceException {
        HelloRuleDto helloRuleDto = new HelloRuleDto();
        Object objResponse = null;

        if (hourOfDay >= 0 && hourOfDay <= 24) {
            objResponse = ruleService.execHelloRule(app.getRulesPathFileName(), new Object[]{hourOfDay});
            if (objResponse != null) {
                helloRuleDto.setHour(hourOfDay);
                helloRuleDto.setRuleName(IRule.RULE_HELLO);
                helloRuleDto.setGreeting(objResponse.toString());
            }
        } else {
            IllegalArgumentException argEx = new IllegalArgumentException("Invalid argument value. Value must be between 0 and 23 inclusive. Value = " + hourOfDay);
            throw new RuleServiceException(argEx, null);
        }
        logger.info("Response Value = [{}]", String.valueOf(objResponse));
        return new ResponseEntity(helloRuleDto, HttpStatus.OK);
    }
}

