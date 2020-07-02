package com.unsera.sipe3.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Daerah {

    @PrimaryKey
    private long id;
    private String kota;
    private String provinsi;


    public Daerah() {

    }

    @Ignore
    public Daerah(long id, String kota, String provinsi) {
        this.setId(id);
        this.setKota(kota);
        this.setProvinsi(provinsi);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    @Override
    public String toString() {
        return provinsi + "/" + kota;
    }
}
