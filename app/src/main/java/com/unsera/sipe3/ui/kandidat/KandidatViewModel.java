package com.unsera.sipe3.ui.kandidat;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.database.dao.KandidatDao;
import com.unsera.sipe3.model.Kandidat;
import com.unsera.sipe3.model.KandidatView;

import java.util.List;

public class KandidatViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;

    public KandidatViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<KandidatView>> getAllKandidat() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllKandidat();
    }
}