package com.example.dns.project_3.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.dns.project_3.R;
import com.example.dns.project_3.ui.fragment.CodeFragment;
import com.example.dns.project_3.ui.fragment.PhoneFragment;
import com.example.dns.project_3.ui.activity.ProfileActivity;

public class AuthPresenterInit implements AuthPresenter{

    private Context context;
    private FragmentManager fragmentManager;

    public AuthPresenterInit(Context context)
    {
        this.context = context;
    }

    @Override
    public void createProfile() {
        Intent intent = new Intent(context,ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void createPhone() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_authorization, PhoneFragment.newInstance())
                .commit();
    }

    @Override
    public void createCode(String phone) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_authorization, CodeFragment.newInstance(phone))
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

}