package com.unsera.sipe3.ui.pelanggaran;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.PengaduanView;

import java.util.List;

public class PelanggaranViewModel extends AndroidViewModel {


    public PelanggaranViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PengaduanView>> getListPengaduanViewTerpilih() {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPengaduanViewTerpilih();
    }
}