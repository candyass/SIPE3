package com.unsera.sipe3.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.unsera.sipe3.database.converter.BitmapConverter;
import com.unsera.sipe3.database.converter.DateConverter;
import com.unsera.sipe3.database.dao.*;
import com.unsera.sipe3.model.*;

@Database(entities = {User.class, Kandidat.class, Daerah.class, Pasal.class, Pelanggaran.class, Pengaduan.class}, version = 1)
@TypeConverters({DateConverter.class, BitmapConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract KandidatDao getKandidatDao();
    public abstract DaerahDao getDaerahDao();
    public abstract PasalDao getPasalDao();
    public abstract PengaduanDao getPengaduanDao();
    public abstract UserDao getUserDao();


}
