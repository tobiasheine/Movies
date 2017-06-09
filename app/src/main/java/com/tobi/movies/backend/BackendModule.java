package com.tobi.movies.backend;

import com.tobi.movies.BuildConfig;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class BackendModule {

    @Provides
    Backend provideBackend() {

        return new Retrofit.Builder()
                .baseUrl(Backend.Companion.getSERVICE_ENDPOINT())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient())
                .build().create(Backend.class);
    }

    private OkHttpClient okHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl url = originalRequest.url();
                HttpUrl urlWithApiKey = url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build();

                Request request = originalRequest.newBuilder().url(urlWithApiKey).build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

}
