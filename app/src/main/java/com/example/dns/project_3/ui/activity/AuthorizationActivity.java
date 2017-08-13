package com.example.dns.project_3.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.dns.project_3.R;
import com.example.dns.project_3.ui.view.AuthView;
import com.example.dns.project_3.ui.fragment.CodeFragment;
import com.example.dns.project_3.ui.fragment.PhoneFragment;
import com.example.dns.project_3.presenter.AuthPresenter;
import com.example.dns.project_3.presenter.AuthPresenterInit;

public class AuthorizationActivity extends AppCompatActivity implements AuthView,
        PhoneFragment.OnFragmentInteractionListener, CodeFragment.OnFragmentInteractionListener {

    private AuthPresenter authPresenterInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        authPresenterInit = new AuthPresenterInit(this);

        FragmentManager fragmentManager = getSupportFragmentManager();

        authPresenterInit.setFragmentManager(fragmentManager);

        if (savedInstanceState == null) {
            authPresenterInit.createPhone();
        }
    }

    @Override
    public void onPhoneFragmentInteraction(String phone) {
        authPresenterInit.createCode(phone);
    }

    @Override
    public void onCodeFragmentInteraction() {
        authPresenterInit.createProfile();
    }

}
