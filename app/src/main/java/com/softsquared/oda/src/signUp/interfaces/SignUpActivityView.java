package com.softsquared.oda.src.signUp.interfaces;

public interface SignUpActivityView {
    void vaildateSignUpSuccess(String text,String id);
    void validateSignUpFailure(String message);
    void validateDupSuccess(String text);
    void validateDupFailure(String message);
    void validateFailure(String message);
}
