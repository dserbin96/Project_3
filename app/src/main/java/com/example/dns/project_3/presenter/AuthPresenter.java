package com.example.dns.project_3.presenter;

import android.support.v4.app.FragmentManager;

public interface AuthPresenter {
    void createProfile();
    void createPhone();
    void createCode(String phone);
    void setFragmentManager(FragmentManager fragmentManager);
}