package com.lmb.openl.exception;

import com.lmb.openl.domain.AbstractRule;
import java.util.List;

/**
 * Created by n0292928 on 11/11/16.
 */
public class RuleServiceErrorDto {
    private List<String>    lstErrorMessage;
    private String          errorCause;
    private String          rootClassName;
    private int             errorCode;
    private String          userDisplayMessage;

    public String getErrorCause() { return errorCause; }
    public void setErrorCause(String errorCause) { this.errorCause = errorCause;  }
    public String getRootClassName() { return rootClassName; }
    public void setRootClassName(String rootClassName) { this.rootClassName = rootClassName; }
    public List<String> getErrorMessage() { return lstErrorMessage; }
    public void setErrorMessage(List<String> lstErrorMessage) { this.lstErrorMessage = lstErrorMessage; }
    public int getErrorCode() { return errorCode; }
    public void setErrorCode(int errorCode) { this.errorCode = errorCode; }
    public String getUserDisplayMessage() { return userDisplayMessage; }
    public void setUserDisplayMessage(String userDisplayMessage) { this.userDisplayMessage = userDisplayMessage; }

}
