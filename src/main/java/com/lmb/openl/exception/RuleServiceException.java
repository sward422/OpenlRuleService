package com.lmb.openl.exception;

import com.lmb.openl.domain.AbstractRule;
import com.lmb.openl.service.IRule;

/**
 * Created by n0292928 on 11/11/16.
 */
public class RuleServiceException  extends Exception {
    private static final long serialVersionUID = 1L;

    private Exception               rootException;
    private IRule                   rule;
    private String                  errorMessage;

    public IRule getRule() {  return rule; }
    public void setRule(IRule rule) {  this.rule = rule; }
    public String getErrorMessage() {  return errorMessage; }
    public void setErrorMessage(String errorMessage) {  this.errorMessage = errorMessage; }
    public Exception getRootException() {   return rootException;  }

    public RuleServiceException(Exception parmEx, IRule parmRule) {
        super(parmEx);
        this.rootException = parmEx;
        this.rule = parmRule;
        this.errorMessage = parmEx.getMessage();
    }
    public RuleServiceException(Exception parmEx,String parmErrorMsg,IRule parmRule) {
        super(parmErrorMsg,parmEx);
        this.rule = parmRule;
        this.rootException = parmEx;
        this.errorMessage = parmErrorMsg;
    }

}
