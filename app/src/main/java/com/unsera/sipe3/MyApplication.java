package com.unsera.sipe3;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.format.DateUtils;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.unsera.sipe3.database.AppDatabase;
import com.unsera.sipe3.model.*;
import com.unsera.sipe3.util.DataUtills;

import java.util.List;
import java.util.concurrent.*;

public class MyApplication extends Application {

    private AppDatabase appDatabase;
    private ExecutorService executor;

    private static final String PREF_NAME = "com.unsera.sipe3.mypref";
    private static final String KEY_SETUP_DATA = "com.unsera.sipe3.key.setupdata";
    private static final String DATABASE_NAME = "sipe3";

    @Override
    public void onCreate() {
        super.onCreate();
        executor = Executors.newSingleThreadExecutor();
        appDatabase = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME).build();
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        boolean isDataSetup =  preferences.getBoolean(KEY_SETUP_DATA, false);
        if(!isDataSetup) {
            executor.execute(() -> appDatabase.getUserDao().inserUserList(DataUtills.getListUser()));
            executor.execute(() -> appDatabase.getDaerahDao().insert(DataUtills.getListDaerah()));
            executor.execute(() -> appDatabase.getKandidatDao().insert(DataUtills.getListKandidat()));
            executor.execute(() -> appDatabase.getPasalDao().insert(DataUtills.getListPasal()));
            preferences.edit().putBoolean(KEY_SETUP_DATA, true).commit();
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        appDatabase = null;
        executor.shutdown();
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }


    public LiveData<List<Daerah>> getAllDaerah() {
        return appDatabase.getDaerahDao().findAll();
    }

    public LiveData<List<KandidatView>> getAllKandidat() {
        return appDatabase.getKandidatDao().findAll();
    }

    public LiveData<List<Pasal>> getAllPasal() {
        return appDatabase.getPasalDao().findAll();
    }

    public void pengajuanPengaduan(Pengaduan pengaduan, List<Pelanggaran> list) {
        executor.execute(() -> {
            appDatabase.getPengaduanDao().pengajuanPengaduan(pengaduan, list);
        });
    }

    public LiveData<List<PengaduanView>> getListPengaduanViewTerpilih() {
        return appDatabase.getPengaduanDao().getListPengaduanViewTerpilih();
    }

    public LiveData<List<PengaduanView>> getListPengaduanViewDiproses() {
        return appDatabase.getPengaduanDao().getListPengaduanViewDiproses();
    }

    public LiveData<List<PengaduanView>> getListPengaduanView() {
        return appDatabase.getPengaduanDao().getListPengaduanView();
    }

    public LiveData<PengaduanView> getPengaduanView(long id) {
        return appDatabase.getPengaduanDao().getPengaduanView(id);
    }

    public LiveData<List<PasalView>> getListPasalView(long id) {
        return appDatabase.getPengaduanDao().getListPasalView(id);
    }

    public User getUser(String noKTP, String password) throws ExecutionException, InterruptedException {
        Callable<User> callable = new Callable<User>() {
            @Override
            public User call() throws Exception {
                return appDatabase.getUserDao().getUser(noKTP, password);
            }
        };
        return executor.submit(callable).get();
    }

    public void tolakPengaduan(long idPengaduan) {
        executor.execute(() -> {
            appDatabase.getPengaduanDao().tolakPengaduan(idPengaduan);
        });
    }

    public void terimaPengaduan(long idPengaduan) {
        executor.execute(() -> {
            appDatabase.getPengaduanDao().terimaPengaduan(idPengaduan);
        });
    }

    public void insertkandidat(Kandidat kandidat) {
        executor.execute(() -> {
            appDatabase.getKandidatDao().insertKandidat(kandidat);
        });
    }

    public LiveData<KandidatView> getKandidatView(long id) {
        return appDatabase.getKandidatDao().findOne(id);
    }

    public void insertPasal(Pasal pasal) {
        executor.execute(() -> {
            appDatabase.getPasalDao().insertpasal(pasal);
        });
    }

    public void updatePasal(long id, String namaPasal, String keterangan) {
        executor.execute(() -> {
            appDatabase.getPasalDao().updatePasal(id, namaPasal, keterangan);
        });
    }

    public void updatePasal(Pasal pasal) {
        executor.execute(() -> {
            appDatabase.getPasalDao().updatePasal(pasal);
        });
    }

    public int insertUser(User user) throws ExecutionException, InterruptedException {
        Callable<Integer> callable = () -> {
          try {
              appDatabase.getUserDao().insertUser(user);
              return 1;
          }catch (Exception e) {
              return -1;
          }
        };
        Future<Integer> future = executor.submit(callable);
        return future.get();
    }

    public void deletePasal(long id) {
        executor.execute(() -> {
            appDatabase.getPasalDao().deletePasal(id);
        });
    }

    public LiveData<Kandidat> getKandidat(long id) {
        return appDatabase.getKandidatDao().getKandidat(id);
    }

    public LiveData<User> getUser(String noKTP) {
        return appDatabase.getUserDao().getUser(noKTP);
    }

    public LiveData<List<KandidatChart>> getChartKandidat() {
        return appDatabase.getPengaduanDao().getChartkandidat();
    }

    public void updateKandidat(Kandidat kandidat) {
        executor.execute(() -> {
            appDatabase.getKandidatDao().updateKandidat(kandidat);
        });
    }

    public void hapusKandidat(Kandidat kandidat) {
        executor.execute(() -> {
            appDatabase.getKandidatDao().hapusKandidat(kandidat);
        });
    }


}
