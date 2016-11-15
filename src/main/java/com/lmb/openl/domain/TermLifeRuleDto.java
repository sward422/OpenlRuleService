package com.lmb.openl.domain;


public class TermLifeRuleDto {

    private Integer     age;
    private String      gender;
    private Boolean     smoker;
    private String      empType;
    private Boolean     ciHist;
    private Boolean     retired;
    private String      state;
    private String      termLifePlan;
    private Double      premium;
    private String      ruleName;

    public String getRuleName() {  return ruleName; }
    public void setRuleName(String ruleName) {  this.ruleName = ruleName; }
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

}
