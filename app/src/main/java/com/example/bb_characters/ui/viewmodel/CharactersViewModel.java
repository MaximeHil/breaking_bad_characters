package com.example.bb_characters.ui.viewmodel;

import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.bb_characters.data.api.model.CharactersResponse;
import com.example.bb_characters.data.repository.CharacterDisplayRepository;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;
import com.example.bb_characters.ui.characterdisplay.mapper.CharacterToViewModelMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CharactersViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private CharacterToViewModelMapper characterToViewModelMapper;

    public CharactersViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.characterToViewModelMapper = new CharacterToViewModelMapper();
    }

    private MutableLiveData<List<CharacterViewItem>> characters = new MutableLiveData<>();

    public MutableLiveData<List<CharacterViewItem>> getCharacters(){
        return characters;
    }

    public void getAllCharacters(){
        Log.i("VIEW MODEL", "debut get all characters");
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<CharactersResponse>() {

                    @Override
                    public void onSuccess(@NonNull CharactersResponse charactersResponse) {
                        Log.i("VIEW MODEL", "Response qui arrive : " + charactersResponse.getTotalItems());
                        characters.setValue(characterToViewModelMapper.map(charactersResponse.getCharactersList()));
                        Log.i("VIEW MODEL", "On est passé par là + size : " + characters.getValue().size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app
                        Log.i("VIEW MODEL", "Une erreur s'est produite !!!");

                        System.out.println(e.toString());
                    }
                }));
    }
}