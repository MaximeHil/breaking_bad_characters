package com.example.bb_characters.ui;

import android.app.Application;

import com.example.bb_characters.data.di.FakeDependencyInjection;

// Cette classe doit étendre Application et être référencée dans le manifest
public class CharacterApplication extends Application {

    // Permet de simuler l'injection de dépendances au lancement de l'application
    @Override
    public void onCreate() {
        super.onCreate();
        FakeDependencyInjection.setContext(this);
    }
}
