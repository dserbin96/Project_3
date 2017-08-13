package com.example.dns.project_3.presenter;

import android.content.Context;

import com.example.dns.project_3.entity.News;
import com.example.dns.project_3.ui.view.FillView;
import com.example.dns.project_3.usecase.UseCaseCallback;

import java.util.List;

public class LentaPresenterInit implements LentaPresenter,UseCaseCallback<List<News>> {

    private FillView<List<News>> fillView;


    public LentaPresenterInit(FillView<List<News>> fillView)
    {
        this.fillView = fillView;
    }

    @Override
    public void onSuccess(List<News> list) {
        fillView.fill(list);
    }

    @Override
    public void onError(String s) {
        fillView.showError(s);
    }

    @Override
    public void click(Context context) {
        fillView.createPost();
    }
}
