package com.lmb.openl.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by n0292928 on 11/1/16.
 */
@Component
public class TermLifeRule extends AbstractRule {
    private static final Logger logger = LoggerFactory.getLogger(TermLifeRule.class);

    public static final Class<?>[]    REQUIRED_TERM_ARG_TYPES = {Integer.class, String.class, Boolean.class,String.class,Boolean.class,Boolean.class, String.class,String.class };
    public static final Integer       REQUIRED_TERM_ARG_COUNT = REQUIRED_TERM_ARG_TYPES.length;

    private Integer     age;
    private String      gender;
    private Boolean     smoker;
    private String      empType;
    private Boolean     ciHist;
    private Boolean     retired;
    private String      state;
    private String      termLifePlan;
    private Double      premium;

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Boolean getSmoker() {
        return smoker;
    }
    public void setSmoker(Boolean smoker) {
        this.smoker = smoker;
    }
    public String getEmpType() {
        return empType;
    }
    public void setEmpType(String empType) {
        this.empType = empType;
    }
    public Boolean getCiHist() {
        return ciHist;
    }
    public void setCiHist(Boolean ciHist) {
        this.ciHist = ciHist;
    }
    public Boolean getRetired() {
        return retired;
    }
    public void setRetired(Boolean retired) {
        this.retired = retired;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getTermLifePlan() {
        return termLifePlan;
    }
    public void setTermLifePlan(String termLifePlan) {
        this.termLifePlan = termLifePlan;
    }
    public Double getPremium() { return premium; }
    public void setPremium(Double premium) { this.premium = premium;}


    public TermLifeRule(){super();}
    public TermLifeRule(final String parmMethodName, final Object[] parmArgValues ) {
        super(parmMethodName, REQUIRED_TERM_ARG_TYPES, parmArgValues);
        this.setAge((Integer)parmArgValues[0]);
        this.setGender((String)parmArgValues[1]);
        this.setSmoker((Boolean)parmArgValues[2]);
        this.setEmpType((String)parmArgValues[3]);
        this.setCiHist((Boolean)parmArgValues[4]);
        this.setRetired((Boolean)parmArgValues[5]);
        this.setState((String)parmArgValues[6]);
        this.setTermLifePlan((String)parmArgValues[7]);
    }
}
