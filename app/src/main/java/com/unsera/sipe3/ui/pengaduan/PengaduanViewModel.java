package com.unsera.sipe3.ui.pengaduan;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.PengaduanView;

import java.util.List;

public class PengaduanViewModel extends AndroidViewModel {


    public PengaduanViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<List<PengaduanView>> getListPengaduanViewTerpilih() {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPengaduanViewTerpilih();
    }

    public LiveData<List<PengaduanView>> getListPengaduanViewDiproses() {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPengaduanViewDiproses();
    }

    public LiveData<List<PengaduanView>> getListPengaduanView() {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPengaduanView();
    }
}