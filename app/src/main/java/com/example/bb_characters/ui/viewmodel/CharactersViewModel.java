package com.example.bb_characters.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bb_characters.data.api.model.CharacterDetails;
import com.example.bb_characters.data.repository.CharacterDisplayRepository;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterDetailsViewItem;
import com.example.bb_characters.ui.characterdisplay.adapter.CharacterViewItem;
import com.example.bb_characters.ui.characterdisplay.mapper.CharacterDetailsToViewItem;
import com.example.bb_characters.ui.characterdisplay.mapper.CharacterToViewModelMapper;

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
    private CharacterDetailsToViewItem characterDetailsToViewItemMapper;

    public CharactersViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.characterToViewModelMapper = new CharacterToViewModelMapper();
        this.characterDetailsToViewItemMapper = new CharacterDetailsToViewItem();
    }

    private MutableLiveData<List<CharacterViewItem>> characters = new MutableLiveData<>();

    private MutableLiveData<List<CharacterDetailsViewItem>> character = new MutableLiveData<>();

    public MutableLiveData<List<CharacterViewItem>> getCharacters(){
        return characters;
    }

    public MutableLiveData<List<CharacterDetailsViewItem>> getCharacter(){
        return character;
    }

    public void getAllCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CharacterDetails>>() {

                    @Override
                    public void onSuccess(@NonNull List<CharacterDetails> characterDetailsList) {
                        characters.setValue(characterToViewModelMapper.map(characterDetailsList));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app

                        System.out.println(e.toString());
                    }
                }));
    }

    public void getCharacterById(int id){
        Log.i("DANS LE VIEW MODEL","Récupération du personnage id = " + id);
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CharacterDetails>>(){

                    @Override
                    public void onSuccess(@NonNull List<CharacterDetails> characterDetails) {
                        Log.i("DANS LE VIEW MODEL","Succès !");
                        character.setValue(characterDetailsToViewItemMapper.map(characterDetails));
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("DANS LE VIEW MODEL", "Une erreur s'est produite " + e.getMessage());
                    }
                }));
    }
}