package com.unsera.sipe3.login;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.User;

import java.util.concurrent.ExecutionException;

public class LoginViewModel extends AndroidViewModel {

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public User getUser(String noKTP, String password) {
        MyApplication app = (MyApplication) getApplication();
        User user = null;
        try {
            user = app.getUser(noKTP, password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return user;
    }
}
