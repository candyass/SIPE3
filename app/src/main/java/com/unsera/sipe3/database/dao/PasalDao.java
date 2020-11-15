package com.unsera.sipe3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.unsera.sipe3.model.Pasal;

import java.util.List;

@Dao
public interface PasalDao {

    @Query("SELECT * FROM Pasal")
    LiveData<List<Pasal>> findAll();

    @Query("SELECT * FROM Pasal WHERE id =:id")
    LiveData<Pasal> findOne(long id);

    @Insert
    void insert(List<Pasal> list);

    @Insert
    void insertpasal(Pasal pasal);

    @Query("UPDATE Pasal SET namaPasal =:namaPasal, keterangan =:keterangan WHERE id =:id")
    void updatePasal(long id, String namaPasal, String keterangan);

    @Update
    void updatePasal(Pasal pasal);

    @Query("DELETE FROM Pasal WHERE id =:id")
    void deletePasal(long id);


}
