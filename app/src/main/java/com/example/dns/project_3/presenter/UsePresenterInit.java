package com.example.dns.project_3.presenter;

import com.example.dns.project_3.entity.News;
import com.example.dns.project_3.ui.view.UserView;

import java.util.List;

public class UsePresenterInit implements UsePresenter{
    private UserView callback;

    public UsePresenterInit(UserView callback)
    {
        this.callback = callback;
    }

    @Override
    public void onSuccess(List<News> news) {
        callback.setListAdapter(news);
    }

    @Override
    public void onError(String s) {
        callback.showError(s);
    }
}
