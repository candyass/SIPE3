package com.unsera.sipe3.detailkandidat;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.KandidatView;
import com.unsera.sipe3.util.MyLogger;

public class DetailKandidatViewModel extends AndroidViewModel {

    public DetailKandidatViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<KandidatView> getKandidatView(long id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getKandidatView(id);
    }
}
