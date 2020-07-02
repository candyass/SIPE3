package com.unsera.sipe3.dialog;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.KandidatView;

import java.util.List;

public class DialogKandidatViewModel extends AndroidViewModel {

    public DialogKandidatViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<KandidatView>> getAllKandidat() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllKandidat();
    }
}
