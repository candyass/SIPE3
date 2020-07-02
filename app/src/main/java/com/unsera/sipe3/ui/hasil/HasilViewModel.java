package com.unsera.sipe3.ui.hasil;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.unsera.sipe3.MyApplication;
import com.unsera.sipe3.model.KandidatChart;

import java.util.List;

public class HasilViewModel extends AndroidViewModel {

    public HasilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<KandidatChart>> getChartKandidat() {
        MyApplication app = (MyApplication) getApplication();
        return app.getChartKandidat();
    }
}
