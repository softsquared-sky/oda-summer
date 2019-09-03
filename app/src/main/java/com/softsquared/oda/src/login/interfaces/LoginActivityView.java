package com.softsquared.oda.src.login.interfaces;

public interface LoginActivityView {
    void validateSuccess(String text,String jwt);

    void validateFailure(String message);
}
