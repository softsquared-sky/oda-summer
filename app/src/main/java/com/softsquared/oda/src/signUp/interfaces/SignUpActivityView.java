package com.softsquared.oda.src.signUp.interfaces;

public interface SignUpActivityView {
    void validateSuccess(String text,int code);
    void validateFailure(String message);
}
