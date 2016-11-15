package com.lmb.openl.domain;

public class HelloRuleDto {
    private String      ruleName;
    private Integer     hour;
    private String      greeting;

    public String getRuleName() { return ruleName; }
    public void setRuleName(String ruleName) { this.ruleName = ruleName; }
    public Integer getHour() { return hour;}
    public void setHour(Integer hour) { this.hour = hour; }
    public String getGreeting() { return greeting;}
    public void setGreeting(String greeting) { this.greeting = greeting; }
}