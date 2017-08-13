package com.example.dns.project_3.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dns.project_3.R;
import com.example.dns.project_3.entity.News;
import com.example.dns.project_3.presenter.LentaPresenterInit;
import com.example.dns.project_3.ui.adapter.LentaAdapter;
import com.example.dns.project_3.ui.view.FillView;
import com.example.dns.project_3.usecase.ListUseCaseCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LentaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FillView<List<News>> {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @BindView(R.id.lenta_recycler) RecyclerView recyclerView;
    private LentaAdapter lentaAdapter;

    LentaPresenterInit presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenta);
        ButterKnife.bind(this);
        initial();

        presenter = new LentaPresenterInit(this);
        ListUseCaseCallback requestUseCaseCollback = new ListUseCaseCallback();

        requestUseCaseCollback.execute(presenter);
    }

    private void initial() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        lentaAdapter = new LentaAdapter(new LentaAdapter.OnClickHolder() {
            @Override
            public void onClickLike() {
                Toast.makeText(LentaActivity.this,"Нажат",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickUser() {
                Intent intent = new Intent(LentaActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(lentaAdapter);

        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> presenter.click(this));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        if (header != null) {
            ImageView imageView = (ImageView) header
                    .findViewById(R.id.novig_img);

            imageView.setOnClickListener(v -> {
                Intent intent = new Intent(this,ProfileActivity.class);
                startActivity(intent);
            });

            Glide.with(this)
                    .load(R.drawable.stark)
                    .bitmapTransform( new jp.wasabeef.glide
                            .transformations.BlurTransformation( this, 5 ) )
                    .into(imageView);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lenta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.top) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_my_post) {

        } else if (id == R.id.nav_settings) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void fill(List<News> list) {
        lentaAdapter.setList(list);
    }

    @Override
    public void showError(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void createPost() {
        Intent intent = new Intent(this, PostActivity.class);
        startActivity(intent);
    }

}