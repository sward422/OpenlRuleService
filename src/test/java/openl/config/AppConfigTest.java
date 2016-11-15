package openl.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by n0292928 on 11/5/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = com.lmb.openl.config.AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class AppConfigTest {

    @Autowired
    public com.lmb.openl.config.AppConfig     appConfig;

    @Before
    public void init() { }

    @Test
    public void testPropertyConfigLoad() {
        Assert.assertNotNull(appConfig.getRulesPathFileName());
        Assert.assertTrue(appConfig.getRulesPathFileName().length()  > 0);
        Assert.assertTrue(appConfig.getRulesPathFileName().contains(".xls"));
    }
}
