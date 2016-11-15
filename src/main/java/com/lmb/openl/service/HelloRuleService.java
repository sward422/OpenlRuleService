package com.lmb.openl.service;

import com.lmb.openl.domain.AbstractRule;
import com.lmb.openl.domain.HelloRule;
import com.lmb.openl.exception.RuleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by n0292928 on 11/9/16.
 */
@Service
public class HelloRuleService extends AbstractRuleService {
    private static final Logger logger = LoggerFactory.getLogger(HelloRuleService.class);

    public Object execHelloRule(final String parmRulesPathFileName, final Object[] parmArgs) throws RuleServiceException {
        Object objResponse=null;

        //IRule.RULE_HELLO
        IRule ruleHello = new HelloRule(IRule.RULE_HELLO, parmArgs);

        super.createRuleFactory(parmRulesPathFileName);
        objResponse = super.execRule(ruleHello,parmArgs);

        return objResponse;
    }
}
