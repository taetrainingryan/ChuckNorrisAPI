package com.example.ryan.chucknorrisapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ryan.chucknorrisapi.model.ChuckJoke;

import java.util.List;

/**
 * Created by Ryan on 22/11/2017.
 */

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyViewHolder> {

    private List<ChuckJoke> jokes;
    private int row_jokes;
    private Context applicationContext;

    public ViewAdapter(List<ChuckJoke> jokes, int row_jokes, Context applicationContext) {
        this.jokes = jokes;
        this.row_jokes = row_jokes;
        this.applicationContext = applicationContext;
    }

    @Override
    public ViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(applicationContext).inflate(row_jokes, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewAdapter.MyViewHolder holder, int position) {

        holder.id.setText(jokes.get(position).getId().toString());
        holder.joke.setText(jokes.get(position).getJoke());
        holder.category.setText(jokes.get(position).getCategories().toString());

    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id, joke, category;

        public MyViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.tvID);
            joke = (TextView) itemView.findViewById(R.id.tvJoke);
            category = (TextView) itemView.findViewById(R.id.tvCategory);
        }
    }
}
