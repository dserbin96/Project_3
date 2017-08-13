package com.example.dns.project_3.presenter;

import com.example.dns.project_3.ui.view.PhoneView;

public class PhonePresenterInit implements PhonePresenter{

    private PhoneView view;

    public PhonePresenterInit(PhoneView view) {
        this.view = view;
    }

    @Override
    public void onSuccess(String s) {
        view.show();
    }

    @Override
    public void onError(String s) {
        view.showError(s);
    }
}
