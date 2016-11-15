package com.lmb.openl.service;

import com.lmb.openl.domain.HelloRule;
import com.lmb.openl.domain.MedicalPremiumRule;
import com.lmb.openl.exception.RuleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by n0292928 on 11/9/16.
 */
@Service
public class MedicalRuleService extends AbstractRuleService {
    private static final Logger logger = LoggerFactory.getLogger(MedicalRuleService.class);

    public Object execMedicalRule(final String parmRulesPathFileName, final Object[] parmArgs) throws RuleServiceException {
        Object objResponse=null;

        IRule ruleMedical = new MedicalPremiumRule(IRule.RULE_MEDICAL, parmArgs);

        super.createRuleFactory(parmRulesPathFileName);
        objResponse = super.execRule(ruleMedical,parmArgs);

        return objResponse;
    }

}
