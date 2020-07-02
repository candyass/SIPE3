package com.unsera.sipe3.inputpelanggaran;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.PasalView;
import com.unsera.sipe3.model.PengaduanView;

import java.util.List;

public class InputPelanggaranViewModel extends AndroidViewModel {

    public InputPelanggaranViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<PasalView>> getListPasalView(long id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPasalView(id);
    }

    public LiveData<PengaduanView> getPengaduanView(long id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getPengaduanView(id);
    }

    public void tolakPengaduan(long idPengaduan) {
        MyApplication app = (MyApplication) getApplication();
        app.tolakPengaduan(idPengaduan);
    }

    public void terimaPengaduan(long idPengaduan) {
        MyApplication app = (MyApplication) getApplication();
        app.terimaPengaduan(idPengaduan);
    }
}
