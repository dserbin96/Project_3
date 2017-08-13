package com.example.dns.project_3.usecase;

import com.example.dns.project_3.entity.News;
import com.example.dns.project_3.entity.Profile;

import java.util.ArrayList;
import java.util.List;

public class ListUseCaseCallback {

    private List<News> list = new ArrayList<>();

    Profile profile = new Profile(1,"http://www.bearworld.ru/wp-content/uploads/bearworld/2014/" +
            "05/Медведь-среда-обитания-и-питание3.jpg",
            "Миша",null,null,null);

    News news = new News("http://ic.pics.livejournal.com/tai_ray/58484024" +
            "/203771/203771_original.jpg",
            "Памятник",
            "Памятник А. В. Суворову на набережной г.Славянск-на-Кубани.",
            "12.05.2015",profile,11);

    public void execute(UseCaseCallback<List<News>> collback)
    {
        list.add(news);
        list.add(news);
        list.add(news);
        list.add(news);
        list.add(news);
        list.add(news);
        list.add(news);

        if(list.isEmpty())
        {
            collback.onError("Ошибка подключения");
        }
        else
        {
            collback.onSuccess(list);
        }
    }

}
