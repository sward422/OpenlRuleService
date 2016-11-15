package com.lmb.openl.service;

import com.lmb.openl.domain.HelloRule;
import com.lmb.openl.exception.RuleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by n0292928 on 11/10/16.
 */
abstract  class AbstractRuleService {

    private static RuleFactory     ruleFactory;
    private static RuleExecutor    ruleExec;
    private static final Logger logger = LoggerFactory.getLogger(AbstractRuleService.class);

    public void createRuleFactory(final String parmRulesPathFileName) {
        ruleFactory = RuleFactory.createRuleFactory(parmRulesPathFileName);
        ruleExec = new RuleExecutor(ruleFactory);
    }

    public Object execRule(final IRule parmRule, final Object[] parmArgs) throws RuleServiceException {
       Object objResponse=null;
       try {
            objResponse = ruleExec.executeRule(parmRule);
            return objResponse;
        } catch(Exception ex ) {
            throw new RuleServiceException(ex,parmRule);
        }
    }
}
