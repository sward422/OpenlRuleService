package openl.domain;

import com.lmb.openl.config.AppConfig;
import com.lmb.openl.domain.HelloRule;
import com.lmb.openl.domain.MedicalPremiumRule;
import com.lmb.openl.domain.TermLifeRule;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by n0292928 on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class RulesTest {
    private static final Object[] HELLO_RULE_ARGVALUES = {15};
    private static final Object[] MEDICALPREM_RULE_ARGVALUES = {55,"M",false};
    private static final Object[] TERMLIFE_RULE_ARGVALUES = {42,"M",true,"WC",true,false,"NY","LIFEPLAN2"};

    @Before
    public void init() {
    }
    @Test
    public void testHelloRuleConstruct() {
        HelloRule helloRule = new HelloRule("greetings",HELLO_RULE_ARGVALUES);
        Assert.assertNotNull(helloRule);
        Assert.assertTrue(StringUtils.equalsIgnoreCase(helloRule.getRuleMethodName(),"greetings"));
        Assert.assertEquals(1,helloRule.getArgTypes().length);
        Assert.assertEquals(15, helloRule.getArgValues()[0]);
    }

   @Test
    public void testMedicalPremiumRuleConstruct() {
        MedicalPremiumRule medicalRule = new MedicalPremiumRule("medicalPremium",MEDICALPREM_RULE_ARGVALUES);

        Assert.assertNotNull(medicalRule );
        Assert.assertTrue(StringUtils.equalsIgnoreCase(medicalRule.getRuleMethodName(),"medicalPremium"));
        Assert.assertEquals(3,medicalRule.getArgTypes().length);
        Assert.assertEquals(55, medicalRule.getArgValues()[0]);
        Assert.assertEquals("M", medicalRule.getArgValues()[1]);
        Assert.assertEquals(false, medicalRule.getArgValues()[2]);
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testMedicalPremiumRuleIllegalArgValues() {
        MedicalPremiumRule medicalRule = new MedicalPremiumRule("medicalPremium",HELLO_RULE_ARGVALUES);
    }

    @Test
    public void testTermLifeRuleConstruct() {
        TermLifeRule termRule = new TermLifeRule("termLifeRule",TERMLIFE_RULE_ARGVALUES);

        Assert.assertNotNull(termRule);
        Assert.assertTrue(StringUtils.equalsIgnoreCase(termRule.getRuleMethodName(),"termLifeRule"));
        Assert.assertEquals(8,termRule.getArgTypes().length);

        Assert.assertEquals(42, termRule.getArgValues()[0]);
        Assert.assertEquals("M", termRule.getArgValues()[1]);
        Assert.assertEquals(true, termRule.getArgValues()[2]);
        Assert.assertEquals("WC",termRule.getArgValues()[3].toString());
        Assert.assertEquals(true, termRule.getArgValues()[4]);
        Assert.assertEquals(false, termRule.getArgValues()[5]);
        Assert.assertEquals("NY", termRule.getArgValues()[6].toString());
        Assert.assertEquals("LIFEPLAN2", termRule.getArgValues()[7].toString());
    }

    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testTermLifeRuleIllegalArgValues() {
        TermLifeRule termRule = new TermLifeRule("termLifeRule",HELLO_RULE_ARGVALUES);
    }
}
