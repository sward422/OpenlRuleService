package com.lmb.openl.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// ResponseEntityExceptionHandler

@ControllerAdvice
public class RuleServiceExceptionHandler extends ResponseEntityExceptionHandler  {
    private static final Logger logger = LoggerFactory.getLogger(RuleServiceExceptionHandler.class);

    @ExceptionHandler(value={RuleServiceException.class})
    public ResponseEntity<Object> handleRuleServiceException(RuleServiceException parmEx) {

        logger.info("Exception Type = RuleServiceException");

        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException parmEx,
                                                                         HttpHeaders headers,HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = HttpRequestMethodNotSupported");
        logger.info("*****************************************************************************");
        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);
        ruleServiceErrorDto.setRootClassName(HttpRequestMethodNotSupportedException.class.getName());
        ruleServiceErrorDto.setUserDisplayMessage("Error - HTTP request method verb is not supported. Try using GET.");

        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);

    }

   @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException parmEx,
                                                                  HttpHeaders headers,HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = MethodArgumentNotValidException");
        logger.info("*****************************************************************************");

        List<FieldError> fieldErrors = parmEx.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = parmEx.getBindingResult().getGlobalErrors();

        List<String> lstError = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            lstError.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            lstError.add(error);
        }

        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);
        ruleServiceErrorDto.setErrorMessage(lstError);
        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException parmEx, HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = MissingPathVariableException ");
        logger.info("*****************************************************************************");
        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);

        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
     }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException parmEx,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = ServletRequestBindingException");
        logger.info("*****************************************************************************");
        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);

        ruleServiceErrorDto.setRootClassName(ServletRequestBindingException.class.getName());
        ruleServiceErrorDto.setUserDisplayMessage("Error required parameter has not been supplied. Please respecify. " + request.toString());

        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException parmEx,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = MissingServletRequestParameter");
        logger.info("*****************************************************************************");
        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);

        ruleServiceErrorDto.setRootClassName(MissingServletRequestParameterException.class.getName());
        ruleServiceErrorDto.setUserDisplayMessage("Error required parameter has not been supplied. Please respecify. " + request.toString());
        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
    }

    /*
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object responseBody,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("*****************************************************************************");
        logger.info("Exception Type = handleExceptionInternal ");
        logger.info("*****************************************************************************");
        return new ResponseEntity<Object>(responseBody, headers, status);
    }
    */

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException parmEx,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = HttpMediaTypeNotAcceptableException ");
        logger.info("*****************************************************************************");
        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);

        ruleServiceErrorDto.setRootClassName(MissingServletRequestParameterException.class.getName());
        ruleServiceErrorDto.setUserDisplayMessage("Invalid Accept media type. Required application/json ");

        return new ResponseEntity<Object>(ruleServiceErrorDto,headers,HttpStatus.BAD_REQUEST);
       //return this.handleExceptionInternal(parmEx, ruleServiceErrorDto, headers, status, request);
    }

    @Override
    protected  ResponseEntity<Object> handleTypeMismatch(TypeMismatchException parmEx,HttpHeaders headers,HttpStatus status,WebRequest request) {
        logger.info("*****************************************************************************");
        logger.info("Exception Type = TypeMismatch");
        logger.info("*****************************************************************************");

        RuleServiceErrorDto     ruleServiceErrorDto = getRuleServiceDto(parmEx,HttpStatus.BAD_REQUEST);
        ruleServiceErrorDto.setRootClassName(TypeMismatchException.class.getName());
        ruleServiceErrorDto.setUserDisplayMessage("Error in the order or type of path variables provided in request. " + request.toString());

        return new ResponseEntity<Object>(ruleServiceErrorDto,HttpStatus.BAD_REQUEST);
    }

    //*********************************************************************************************************
    private RuleServiceErrorDto  getRuleServiceDto(Exception parmEx, HttpStatus parmStatus) {
        RuleServiceErrorDto    ruleServiceErrorDto = new RuleServiceErrorDto();

        ruleServiceErrorDto.setErrorCode(parmStatus.value());
        ruleServiceErrorDto.setErrorMessage(Arrays.asList(parmEx.getMessage()));
        ruleServiceErrorDto.setErrorCause(parmEx.getCause() != null ? parmEx.getCause().getMessage() : StringUtils.EMPTY);

        if ( parmEx instanceof RuleServiceException) {
            if ( ((RuleServiceException) parmEx).getRootException() != null )
                if ( ((RuleServiceException) parmEx).getRootException().getClass() != null)
                  ruleServiceErrorDto.setRootClassName(((RuleServiceException) parmEx).getRootException().getClass().getName());

            // Get User Display Error Msg Text
            if (((RuleServiceException) parmEx).getRule() != null) {
                String userErrMsg = buildUserMessage(((RuleServiceException) parmEx));
                ruleServiceErrorDto.setUserDisplayMessage(userErrMsg.toString());
            }
        }
        return ruleServiceErrorDto;
    }

    private String buildUserMessage(RuleServiceException parmEx) {
        StringBuilder  userErrMsg = new StringBuilder("Error executing rule ");
        userErrMsg.append(parmEx.getRule().getRuleMethodName());
        userErrMsg.append(" In rule class name " + parmEx.getRule().getClass().getName());
        userErrMsg.append(". Method was executed using the following parm values : ");

        for ( int nIndex=0; nIndex < parmEx.getRule().getArgValues().length; nIndex++) {
            userErrMsg.append(parmEx.getRule().getArgValues()[nIndex].toString());
            userErrMsg.append(", ");
        }
        return userErrMsg.toString();
    }
}
