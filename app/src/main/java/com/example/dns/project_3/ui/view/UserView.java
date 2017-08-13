package com.example.dns.project_3.ui.view;

import com.example.dns.project_3.entity.News;

import java.util.List;

public interface UserView {
    void setListAdapter(List<News> list);
    void showError(String s);
}
