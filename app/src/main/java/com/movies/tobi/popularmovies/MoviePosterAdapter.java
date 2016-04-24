package com.movies.tobi.popularmovies;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movies.tobi.popularmovies.popularstream.MoviePoster;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private final List<MoviePoster> moviePosters;

    public MoviePosterAdapter(List<MoviePoster> moviePosters) {
        this.moviePosters = moviePosters;
    }

    @Override
    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View moviePosterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_poster, parent, false);
        return new MoviePosterViewHolder(moviePosterView);
    }

    @Override
    public void onBindViewHolder(MoviePosterViewHolder holder, int position) {
        MoviePoster moviePoster = moviePosters.get(position);

        ImageView posterImage = holder.posterImage;
        Glide
                .with(posterImage.getContext())
                .load(moviePoster.getPosterPath())
                .centerCrop()
                .crossFade()
                .into(posterImage);

    }

    @Override
    public int getItemCount() {
        return moviePosters.size();
    }

    public static class MoviePosterViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.posterImage)
        ImageView posterImage;

        public MoviePosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
