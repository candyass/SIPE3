package com.unsera.sipe3.detailpengaduan;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.PasalView;
import com.unsera.sipe3.model.PengaduanView;

import java.util.List;

public class DetailPengaduanViewModel extends AndroidViewModel {

    public DetailPengaduanViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<PengaduanView> getPengaduanView(long id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getPengaduanView(id);
    }

    public LiveData<List<PasalView>> getListPasalView(long id) {
        MyApplication app = (MyApplication) getApplication();
        return app.getListPasalView(id);
    }
}
