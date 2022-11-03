package com.android.omdapitest.Services.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.android.omdapitest.Services.Model.Data_Model;
import com.android.omdapitest.Services.Repository.DataRepository;

import java.util.List;

public class LifeViewModel extends AndroidViewModel {
    private DataRepository mRepo;
    public LifeViewModel(@NonNull Application application) {
        super(application);
        mRepo = DataRepository.getmLifeDataRepository(application);
    }
    public LiveData<List<Data_Model>> GetMovieList(String url){
        return mRepo.GetMovieList(url);
    }
}
