package openl.domain;

import com.lmb.openl.config.AppConfig;
import com.lmb.openl.service.RuleFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by n0292928 on 11/7/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class RuleFactoryTest {

    public static final String   RULES_FILE_GOOD = "/Users/n0292928/EIS/LMB_Workspace/SimpleOpenl/src/main/openl/rules/TemplateRules_Multiple.xls";
    public static final String   RULES_FILE_BAD = "/Users/n0292928/EIS/LMB_Workspace/SimpleOpenl/src/main/openl/rules/FILE_DOES_NOT_EXIST.xls";

    @Before
    public void init() {

    }

    @Test
    public void testCreateRulesFactorySuccess() {
        RuleFactory localRuleFactory = RuleFactory.createRuleFactory(RULES_FILE_GOOD);

        Assert.assertNotNull(localRuleFactory);
        Assert.assertNotNull(localRuleFactory.getRuleEngineInstance());
        Assert.assertNotNull(localRuleFactory.getRulesEngineFactory());
    }

    @Test
    public void testCreateRulesFactoryCheckRulesFileName() {
        RuleFactory localRuleFactory = RuleFactory.createRuleFactory(RULES_FILE_GOOD);
        Assert.assertEquals("TemplateRules_Multiple",localRuleFactory.getRulesEngineFactory().getInterfaceClass().getName());

    }

    @Test
    public void testCreateRulesFactoryFailed() {
        RuleFactory localRuleFactory = RuleFactory.createRuleFactory(RULES_FILE_BAD);
        Assert.assertNull(localRuleFactory);
    }



}
