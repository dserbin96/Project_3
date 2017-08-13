package com.example.dns.project_3.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dns.project_3.R;
import com.example.dns.project_3.entity.News;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class LentaAdapter extends RecyclerView.Adapter<LentaAdapter.ViewHolder> {

    private final List<News> items;
    private LentaAdapter.OnClickHolder clickLenta;

    public LentaAdapter(LentaAdapter.OnClickHolder clickLenta) {
        this.clickLenta = clickLenta;
        this.items = new ArrayList<>();
    }

    public void setList(List<News> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public LentaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lenta_card, parent, false);
        return new LentaAdapter.ViewHolder(view, clickLenta);
    }

    @Override
    public void onBindViewHolder(LentaAdapter.ViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnClickHolder {
        void onClickLike();

        void onClickUser();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.post_img)
        ImageView post_img;
        @BindView(R.id.post_date)
        TextView date;
        @BindView(R.id.post_like_img)
        ImageView like;
        @BindView(R.id.post_count_like)
        TextView count_like;
        @BindView(R.id.post_user_name)
        TextView user_name;
        @BindView(R.id.post_user_img)
        CircleImageView user_img;
        @BindView(R.id.post_description)
        TextView description;
        private Context context;
        LentaAdapter.OnClickHolder clickLenta;

        public ViewHolder(View itemView, LentaAdapter.OnClickHolder clickLenta) {
            super(itemView);
            context = itemView.getContext();
            this.clickLenta = clickLenta;
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull News news) {
            date.setText(news.getDate());
            count_like.setText(String.valueOf(news.getLike()));
            user_name.setText(news.getProfile().getUserName());
            Glide.with(context).load(news.getPathImg()).into(post_img);
            Glide.with(context).load(news.getProfile().getPathImg()).into(user_img);
            description.setText(news.getDescription());
            like.setOnClickListener(v -> clickLenta.onClickLike());
            user_img.setOnClickListener(v -> clickLenta.onClickUser());
        }
    }
}