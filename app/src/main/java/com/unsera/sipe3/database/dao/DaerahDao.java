package com.unsera.sipe3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.unsera.sipe3.model.Daerah;

import java.util.List;

@Dao
public interface DaerahDao {

    @Query("SELECT * FROM daerah")
    LiveData<List<Daerah>> findAll();

    @Query("SELECT * FROM Daerah WHERE id =:id")
    LiveData<Daerah> findOne(long id);

    @Insert
    void insert(List<Daerah> list);
}
