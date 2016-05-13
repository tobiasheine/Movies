package com.movies.tobi.popularmovies.popularstream;

import android.net.Uri;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.movies.tobi.popularmovies.ImageLoader;
import com.movies.tobi.popularmovies.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private final List<MoviePoster> moviePosters;
    private final ImageLoader imageLoader;

    @VisibleForTesting
    MoviePosterAdapter(List<MoviePoster> moviePosters, ImageLoader imageLoader) {
        this.moviePosters = moviePosters;
        this.imageLoader = imageLoader;
    }

    public MoviePosterAdapter(List<MoviePoster> moviePosters) {
        this(moviePosters, new ImageLoader());
    }

    @Override
    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View moviePosterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_poster, parent, false);
        return new MoviePosterViewHolder(moviePosterView);
    }

    @Override
    public void onBindViewHolder(MoviePosterViewHolder holder, int position) {
        MoviePoster moviePoster = moviePosters.get(position);
        String posterPath = moviePoster.getPosterPath();
        imageLoader.loadWebImageInto(Uri.parse(posterPath), holder.posterImage);
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

