package com.unsera.sipe3.ui.profil;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.User;

public class ProfilViewModel extends AndroidViewModel {

    public ProfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser(String noKTP) {
        MyApplication app = (MyApplication) getApplication();
        return app.getUser(noKTP);
    }
}
