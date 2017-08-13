package com.example.dns.project_3.presenter;

import android.view.MenuItem;

import com.example.dns.project_3.R;
import com.example.dns.project_3.ui.view.ProfileView;

public class ProfilePresenterInit implements ProfilePresenter{

    private ProfileView profileView;

    public ProfilePresenterInit(ProfileView profileView)
    {
        this.profileView = profileView;
    }

    public void create()
    {
        profileView.setDeptSpinner();
        profileView.setSexSpinner();
    }

    @Override
    public void setOptionsItem(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.profilr_edit:
                break;

            case R.id.profilr_done:
                profileView.createLenta();
                break;

            case android.R.id.home:
                profileView.finished();
                break;

            default:
                break;

        }
    }
}
