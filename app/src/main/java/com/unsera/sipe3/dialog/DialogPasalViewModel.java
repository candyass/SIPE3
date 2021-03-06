package com.unsera.sipe3.dialog;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.Pasal;

import java.util.List;

public class DialogPasalViewModel extends AndroidViewModel {

    public DialogPasalViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<Pasal>> getAllPasal() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllPasal();
    }
}
