package com.lmb.openl.domain;

import com.lmb.openl.exception.RuleServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by n0292928 on 11/1/16.
 */
@Component
public class HelloRule extends AbstractRule {
    private static final Logger logger = LoggerFactory.getLogger(HelloRule.class);

    public  static final Class<?>[]  REQUIRED_HELLO_ARG_TYPES = {int.class};
    public  static final Integer     REQUIRED_HELLO_ARG_COUNT = REQUIRED_HELLO_ARG_TYPES.length;

    private Integer     hour;
    private String      greeting;

    public String getGreeting() { return greeting; }
    public void setGreeting(String parmResponse) { this.greeting = parmResponse;}
    public Integer getHour() { return hour; }
    public void setHour(Integer hour) {
        this.hour = hour;
    }


    public HelloRule() { super();  }
    public HelloRule(final String parmMethodName, final Object[] parmArgValues ) {
        super(parmMethodName, REQUIRED_HELLO_ARG_TYPES, parmArgValues);
        this.setHour((Integer) parmArgValues[0]);
    }

}
