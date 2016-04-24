package com.movies.tobi.popularmovies.popularstream;

import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {
    public void loadWebImageInto(Uri imageUrl, ImageView imageView) {
        Glide
                .with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }
}
