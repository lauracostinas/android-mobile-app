package com.example.laura.myfirstapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Laura on 09-Nov-17.
 */

class MovieListAdapter extends BaseAdapter{
    List<Movie> movies;
    LayoutInflater layoutInflater;

    public MovieListAdapter(List<Movie> movies, LayoutInflater layoutInflater) {
        this.movies = movies;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.movie_details_list_item, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
        TextView textView = (TextView) convertView.findViewById(R.id.movieNameTextView);
        imageView.setImageResource(R.drawable.itmoviethumbnail);
        textView.setText(movies.get(position).getName());
        return convertView;
    }
}
