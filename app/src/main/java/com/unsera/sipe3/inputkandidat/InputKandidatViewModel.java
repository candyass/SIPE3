package com.unsera.sipe3.inputkandidat;

import android.app.Application;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.squareup.picasso.Picasso;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.Daerah;
import com.unsera.sipe3.model.Kandidat;
import com.unsera.sipe3.util.MyLogger;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class InputKandidatViewModel extends AndroidViewModel {


    private Bitmap foto;

    public InputKandidatViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Daerah>> getAllDaerah() {
        MyApplication app = (MyApplication) getApplication();
        return app.getAllDaerah();
    }

    public void insertKandidat(Kandidat kandidat) {
        MyApplication app = (MyApplication) getApplication();
        if(foto != null) {
            kandidat.setFoto(foto);
        }
        app.insertkandidat(kandidat);
    }




}
