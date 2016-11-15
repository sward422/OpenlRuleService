package com.lmb.openl.domain;

public class MedicalPremiumRuleDto  {
    private Integer     age;
    private String      gender;
    private Boolean     smoker;
    private Double      premium;
    private String      rulename;

    public String getRulename() { return rulename; }
    public void setRulename(String rulename) { this.rulename = rulename; }
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
    public Double getPremium() { return premium; }
    public void setPremium(Double premium) { this.premium = premium;}

}
