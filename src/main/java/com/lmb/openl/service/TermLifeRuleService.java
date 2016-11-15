package com.lmb.openl.service;

import com.lmb.openl.domain.MedicalPremiumRule;
import com.lmb.openl.domain.TermLifeRule;
import com.lmb.openl.exception.RuleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by n0292928 on 11/9/16.
 */
@Service
public class TermLifeRuleService extends AbstractRuleService {

    private static final Logger logger = LoggerFactory.getLogger(TermLifeRuleService.class);

    public Object execTermLifeRule(final String parmRulesPathFileName, final Object[] parmArgs) throws RuleServiceException {
        Object objResponse=null;

        // Create TermLife Rule
        IRule ruleTermLife = new TermLifeRule(IRule.RULE_LIFE, parmArgs);

        super.createRuleFactory(parmRulesPathFileName);
        objResponse = super.execRule(ruleTermLife,parmArgs);

        return objResponse;
    }
}
