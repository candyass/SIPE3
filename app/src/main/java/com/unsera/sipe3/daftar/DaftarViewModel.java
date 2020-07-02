package com.unsera.sipe3.daftar;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.User;

import java.util.concurrent.ExecutionException;

public class DaftarViewModel extends AndroidViewModel {

    public DaftarViewModel(@NonNull Application application) {
        super(application);
    }

    public int saveUser(User user) throws ExecutionException, InterruptedException {
        MyApplication app = (MyApplication) getApplication();
        return app.insertUser(user);
    }
}
