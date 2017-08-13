package com.example.dns.project_3.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dns.project_3.R;
import com.example.dns.project_3.entity.News;
import com.example.dns.project_3.presenter.UsePresenter;
import com.example.dns.project_3.presenter.UsePresenterInit;
import com.example.dns.project_3.ui.adapter.LentaAdapter;
import com.example.dns.project_3.ui.view.UserView;
import com.example.dns.project_3.usecase.ListUseCaseCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;

public class UserActivity extends AppCompatActivity implements UserView {

    private static final int RADIUS = 15;
    private LentaAdapter adapter;
    @BindView(R.id.frand_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.frand_fio)
    TextView textFio;
    @BindView(R.id.frand_dept)
    TextView textDept;
    @BindView(R.id.frand_statys)
    TextView textStatys;
    @BindView(R.id.frand_nikname)
    TextView textNick;
    @BindView(R.id.post_user_img)
    ImageView userImage;
    @BindView(R.id.frend_toolbar)
    Toolbar toolbar;
    private UsePresenter presenter;
    private ListUseCaseCallback listUseCaseCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        initial();

        presenter = new UsePresenterInit(this);
        listUseCaseCallback = new ListUseCaseCallback();
        listUseCaseCallback.execute(presenter);
    }

    private void initial() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new LentaAdapter(new LentaAdapter.OnClickHolder() {
            @Override
            public void onClickLike() {

            }

            @Override
            public void onClickUser() {

            }
        });

        ///////////////////////////////////////////////////////////////////////
        textFio.setText("Петров Петр Петрович");
        textDept.setText("Разработки");
        textStatys.setText("Быстрее, выше, сильнее");
        textNick.setText("Petrov.Petr");
        Glide.with(this).load("http://www.bearworld.ru/wp-content/uploads/bearworld/2014/" +
                "05/Медведь-среда-обитания-и-питание3.jpg")
                .bitmapTransform(new BlurTransformation(this, RADIUS))
                .into(userImage);
        ///////////////////////////////////////////////////////////////////////

        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setListAdapter(List<News> list) {
        adapter.setList(list);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
