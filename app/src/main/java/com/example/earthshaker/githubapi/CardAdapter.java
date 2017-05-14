package com.example.earthshaker.githubapi;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by earthshaker on 14/5/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<GithubResponseConfig> mItems;

    public CardAdapter(List<GithubResponseConfig> item) {
        super();
        mItems = new ArrayList<>(item);
    }

    public void setData(List<GithubResponseConfig> githubResponseConfigList) {
        if (githubResponseConfigList == null) {
            mItems = new ArrayList<>();
        } else {
            mItems = githubResponseConfigList;
        }
        notifyDataSetChanged();
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        GithubResponseConfig githubResponseConfig = mItems.get(i);
        viewHolder.title.setText(githubResponseConfig.getTitle());
        viewHolder.url.setText(String.format("Url: %s", githubResponseConfig.getUrl()));
        viewHolder.body.setText(String.format("Body: %s", githubResponseConfig.getBody()));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView url;
        public TextView title;
        public TextView body;

        public ViewHolder(View itemView) {
            super(itemView);
            url = (TextView) itemView.findViewById(R.id.url);
            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
        }
    }
}