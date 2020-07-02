package com.unsera.sipe3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.unsera.sipe3.model.Kandidat;
import com.unsera.sipe3.model.KandidatView;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface KandidatDao {

    @Query("SELECT t.id, noUrut, nama, namaWakil, kota, provinsi FROM Kandidat t JOIN Daerah d ON t.daerahId = d.id")
    LiveData<List<KandidatView>> findAll();

    @Query("SELECT k.id, noUrut, nama, namaWakil, foto, d.kota, d.provinsi FROM Kandidat k " +
            "JOIN Daerah d ON k.daerahId = d.id WHERE k.id =:id")
    LiveData<KandidatView> findOne(long id);

    @Insert
    void insert(List<Kandidat> list);

    @Insert
    void insertKandidat(Kandidat kandidat);


}
