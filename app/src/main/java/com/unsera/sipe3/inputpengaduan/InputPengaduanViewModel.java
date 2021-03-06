package com.unsera.sipe3.inputpengaduan;

import android.app.Application;
import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.database.converter.BitmapConverter;
import com.unsera.sipe3.model.Pelanggaran;
import com.unsera.sipe3.model.Pengaduan;

import java.util.List;

public class InputPengaduanViewModel extends AndroidViewModel {

    public InputPengaduanViewModel(@NonNull Application application) {
        super(application);
    }

    public void pengajuanPengaduan(Pengaduan pengaduan, List<Pelanggaran> list) {
        MyApplication app = (MyApplication) getApplication();
        app.pengajuanPengaduan(pengaduan, list);
    }

}
