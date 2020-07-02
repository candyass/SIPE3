package com.unsera.sipe3.model;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = Daerah.class, parentColumns = "id", childColumns = "daerahId"))
public class Kandidat {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private int noUrut;
    private String nama;
    private String namaWakil;
    private long daerahId;
    @Nullable
    private Bitmap foto;


    public Kandidat() {

    }

    @Ignore
    public Kandidat(int noUrut, String nama, String namaWakil, long daerahId) {
        this.noUrut = noUrut;
        this.nama = nama;
        this.namaWakil = namaWakil;
        this.daerahId = daerahId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(int noUrut) {
        this.noUrut = noUrut;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNamaWakil() {
        return namaWakil;
    }

    public void setNamaWakil(String namaWakil) {
        this.namaWakil = namaWakil;
    }

    public long getDaerahId() {
        return daerahId;
    }

    public void setDaerahId(long daerahId) {
        this.daerahId = daerahId;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
