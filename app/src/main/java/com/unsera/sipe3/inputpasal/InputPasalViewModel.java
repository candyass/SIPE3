package com.unsera.sipe3.inputpasal;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.Pasal;

public class InputPasalViewModel extends AndroidViewModel {

    public InputPasalViewModel(@NonNull Application application) {
        super(application);
    }

    public void insertPasal(Pasal pasal) {
        MyApplication app = (MyApplication) getApplication();
        app.insertPasal(pasal);
    }

    public void updatePasal(long id, String namaPasal, String keterangan) {
        MyApplication app = (MyApplication) getApplication();
        app.updatePasal(id, namaPasal, keterangan);
    }

    public void updatePasal(Pasal pasal) {
        MyApplication app = (MyApplication) getApplication();
        app.updatePasal(pasal);
    }

    public void deletePasal(long id) {
        MyApplication app = (MyApplication) getApplication();
        app.deletePasal(id);
    }
}
