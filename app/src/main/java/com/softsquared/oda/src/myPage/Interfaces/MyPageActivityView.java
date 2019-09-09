package com.softsquared.oda.src.myPage.Interfaces;

import com.softsquared.oda.src.myPage.MyPageListData;

import java.util.ArrayList;

public interface MyPageActivityView {

    void validateSuccess(String message, ArrayList<MyPageListData> item);

    void validateFailure(String message);
}
