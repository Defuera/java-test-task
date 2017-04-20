package ru.justd.backbaseassignment.common.di;

import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.justd.backbaseassignment.BuildConfig;
import ru.justd.backbaseassignment.common.APIService;
import ru.justd.backbaseassignment.index.model.MemoryCacheMembersDataSource;
import ru.justd.backbaseassignment.index.model.RetrofitMembersDataSource;
import ru.justd.backbaseassignment.index.model.MembersRepository;
import ru.justd.backbaseassignment.index.model.remote.FetchMemebersResponse;
import ru.justd.backbaseassignment.index.model.remote.FetchMemebersResponseDeserializer;

/**
 * Created by defuera on 20/04/2017.
 */
@Module
public class BackbaseModule {

    private final Context context;

    public BackbaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(FetchMemebersResponse.class, new FetchMemebersResponseDeserializer())
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    @Provides
    @Singleton
    APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {

        return new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                )
                .build();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client, Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(converterFactory)
                .client(client)
                .build();
    }

    @Provides
    @Singleton
    RetrofitMembersDataSource provideMembersRemoteDataSource(APIService apiService) {
        return new RetrofitMembersDataSource(apiService);
    }

    @Provides
    @Singleton
    MemoryCacheMembersDataSource provideMembersInMemoryDataSource() {
        return new MemoryCacheMembersDataSource();
    }

    @Provides
    @Singleton
    MembersRepository provideMembersRepository(RetrofitMembersDataSource remote, MemoryCacheMembersDataSource local) {
        return new MembersRepository(remote, local);
    }

}
