package vttp2023.batch3.ssf.frontcontroller.error;

public class CaptchaFailException extends Exception{
    
    public CaptchaFailException(){}
    
    public CaptchaFailException(String errorMessage) {
        super(errorMessage);
    }
    
}
