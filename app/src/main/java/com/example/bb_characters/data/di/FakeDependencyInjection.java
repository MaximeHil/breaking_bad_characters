package com.example.bb_characters.data.di;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.example.bb_characters.data.api.CharacterDisplayService;
import com.example.bb_characters.data.repository.CharacterDisplayDataRepository;
import com.example.bb_characters.data.repository.CharacterDisplayRepository;
import com.example.bb_characters.data.repository.remote.CharacterDisplayRemoteDataSource;
import com.example.bb_characters.ui.CharacterApplication;
import com.google.gson.Gson;

/**
 * Cette classe permet de simuler l'utilisation d'un système d'injection de dépendances
 * Les repository sont initialisés une seule fois au lancement de l'application
 */
public class FakeDependencyInjection {

    private static Context applicationContext;
    private static Retrofit retrofit;
    private static Gson gson;
    private static CharacterDisplayRepository characterDisplayRepository;
    private static CharacterDisplayService characterDisplayService;

    //Création d'une instance pour utiliser l'interface Retrofit
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.breakingbadapi.com/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static CharacterDisplayRepository getCharacterDisplayRepository() {
        if (characterDisplayRepository == null) {
            characterDisplayRepository = new CharacterDisplayDataRepository(
                    new CharacterDisplayRemoteDataSource(getCharacterDisplayService())
            );
        }
        return characterDisplayRepository;
    }

    public static CharacterDisplayService getCharacterDisplayService() {
        if (characterDisplayService == null) {
            characterDisplayService = getRetrofit().create(CharacterDisplayService.class);
        }
        return characterDisplayService;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }
}
