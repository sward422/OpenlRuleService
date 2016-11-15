package openl.dao;

import com.lmb.openl.config.AppConfig;
import com.lmb.openl.domain.*;
import com.lmb.openl.exception.RuleServiceException;
import com.lmb.openl.service.IRule;
import com.lmb.openl.service.RuleExecutor;
import com.lmb.openl.service.RuleFactory;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by n0292928 on 11/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class RuleExecutorTest {
   private static final Logger logger = LoggerFactory.getLogger(RuleExecutorTest.class);

   public static final String   RULES_FILE_GOOD = "/Users/n######/EIS/ABC_Workspace/SimpleOpenl/src/main/openl/rules/TemplateRules_Multiple.xls";
   public static final String   RULES_FILE_BAD = "/Users/n#######/EIS/ABC_Workspace/SimpleOpenl/src/main/openl/rules/FILE_DOES_NOT_EXIST.xls";

   private static final Object[]  HELLO_RULE_ARGVALUES = {15};
   private static final Object[]  HELLO_RULE_ARGVALUES_NOMATCH = {99};
   private static final Object[]  MEDICALPREM_RULE_ARGVALUES = {55,"M",false};
   private static final Object[]  MEDICALPREM_RULE_ARGVALUES_NOMATCH = {55,"G",false};
   private static final Object[]  TERMLIFE_RULE_ARGVALUES = {42,"M",true,"WC",true,false,"NY","LIFEPLAN2"};
    private static final Object[] TERMLIFE_RULE_ARGVALUES_NOMATCH = {42,"G",true,"WC",true,false,"FL","LIFEPLAN84"};

   private RuleFactory ruleFactory;
   private IRule helloRule;
   private IRule helloRuleNoMatch;
   private IRule helloRuleInvalidName;
   private IRule medicalRule;
   private IRule medicalRuleNoMatch;
   private IRule termLifeRule;
   private IRule termLifeRuleNoMatch;
   private RuleExecutor ruleExec;

   @Before
   public void init() {
       ruleFactory              = RuleFactory.createRuleFactory(RULES_FILE_GOOD);
       helloRule                = new HelloRule(IRule.RULE_HELLO, HELLO_RULE_ARGVALUES );
       helloRuleInvalidName     = new HelloRule("InvalidRuleName", HELLO_RULE_ARGVALUES );
       helloRuleNoMatch         = new HelloRule(IRule.RULE_HELLO, HELLO_RULE_ARGVALUES_NOMATCH);
       medicalRule              = new MedicalPremiumRule(IRule.RULE_MEDICAL,MEDICALPREM_RULE_ARGVALUES);
       medicalRuleNoMatch       = new MedicalPremiumRule(IRule.RULE_MEDICAL,MEDICALPREM_RULE_ARGVALUES_NOMATCH);
       termLifeRule             = new TermLifeRule(IRule.RULE_LIFE, TERMLIFE_RULE_ARGVALUES);
       termLifeRuleNoMatch      = new TermLifeRule(IRule.RULE_LIFE, TERMLIFE_RULE_ARGVALUES_NOMATCH);

       // The one and only....
       ruleExec = new RuleExecutor(ruleFactory);
   }

    @Test
    public void testCreateRuleExecutor() {
        Assert.assertNotNull(ruleExec);
    }

    @Test
    public void testExecHelloRuleValidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(helloRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertTrue(StringUtils.equalsIgnoreCase(objResponse.toString(),"Good Afternoon"));
        } catch ( Exception ex ) {
            Assert.assertNull(ex);
        }
    }

    @Test
    public void testExecHelloRuleInvalidMethodName() {
        try {
            Object objResponse = ruleExec.executeRule(helloRuleInvalidName);
        } catch(Exception ex ) {
            logger.error(ex.getMessage());
            Assert.assertTrue(ex instanceof NoSuchMethodException );
        }
    }

    @Test
    public void testExecHelloRuleInvalidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(helloRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertFalse(StringUtils.equalsIgnoreCase(objResponse.toString(),"Good Bye to You!!!"));
        } catch ( Exception ex ) {
            Assert.assertNull(ex);
        }
    }

    @Test
    public void testExecMedicalPremiumRuleValidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(medicalRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertTrue(Double.valueOf(objResponse.toString()) == 85.20);
        } catch ( Exception ex ) {
            Assert.assertNull(ex);
        }
    }

    @Test
    public void testExecMedicalPremiumRuleNoMatchNullResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(medicalRuleNoMatch);
            Assert.assertNull(objResponse);
        } catch ( Exception ex ) {
            Assert.assertNull(ex);
        }
    }
    @Test
    public void testExecMedicalPremiumRuleInvalidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(medicalRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertFalse(Double.valueOf(objResponse.toString()) == 1085.20);
        } catch ( Exception ex ) {
            logger.error( ex.getMessage());
            Assert.assertNull(ex);
        }
    }

    @Test
    public void testExecTermLifeRuleValidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(termLifeRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertTrue(Double.valueOf(objResponse.toString()) == 78.22);
        } catch ( Exception ex ) {
            logger.error( ex.getMessage());
            Assert.assertNull(ex);
        }
    }

    @Test
    public void testExecTermLifeRuleInvalidResponseValue() {
        try {
            Object objResponse = ruleExec.executeRule(termLifeRule);
            Assert.assertNotNull(objResponse);
            Assert.assertFalse(StringUtils.isEmpty(objResponse.toString()));
            Assert.assertFalse(Double.valueOf(objResponse.toString()) == 1078.22);
        } catch ( Exception ex ) {
            logger.error( ex.getMessage());
        }
    }

    @Test
    public void testExecTermLifeRuleFailsInvalidMethodName() {
        TermLifeRule localTermLifeRule = new TermLifeRule("TermLife_InvalidMethodName", TERMLIFE_RULE_ARGVALUES);
        try {
            Object objResponse = ruleExec.executeRule(localTermLifeRule);
            Assert.assertNull(objResponse);
        } catch ( Exception ex) {
            Assert.assertTrue(ex instanceof NoSuchMethodException);
            logger.error( ex.getMessage());
        }
    }

    @Test(expected=ClassCastException.class)
    public void testExecTermLifeRuleIllegalArgException() {
        TermLifeRule localTermLifeRule = new TermLifeRule(IRule.RULE_LIFE, new Object[] {"AGE","M",true,"WC",true,false,"NY","LIFEPLAN2"});
    }

    @Test
    public void testExecTermLifeRuleNoArgMatch() {
        try {
            Object objResponse  = ruleExec.executeRule(termLifeRuleNoMatch);
            Assert.assertNull(objResponse);
        } catch ( Exception ex ) {
            logger.error( ex.getMessage());
        }
    }
}
