package com.example.bb_characters.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bb_characters.data.entity.CharacterEntity;
import com.example.bb_characters.data.repository.CharacterDisplayRepository;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.adapter.CharacterFavoriteViewItem;
import com.example.bb_characters.ui.characterdisplay.favoriteCharacters.mapper.EntityToFavoriteViewMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class FavoriteViewModel extends ViewModel {

    private CharacterDisplayRepository characterDisplayRepository;
    private CompositeDisposable compositeDisposable;
    private EntityToFavoriteViewMapper entityToFavoriteViewMapper;

    public FavoriteViewModel(CharacterDisplayRepository characterDisplayRepository){
        this.characterDisplayRepository = characterDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.entityToFavoriteViewMapper= new EntityToFavoriteViewMapper();
    }

    private MutableLiveData<List<CharacterFavoriteViewItem>> favoriteCharacters = new MutableLiveData<>();

    public void getFavoriteCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(characterDisplayRepository.getFavoriteCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<CharacterEntity>>() {

                    @Override
                    public void onNext(List<CharacterEntity> characterEntities) {
                        favoriteCharacters.setValue(entityToFavoriteViewMapper.map(characterEntities));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app

                        System.out.println(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}
