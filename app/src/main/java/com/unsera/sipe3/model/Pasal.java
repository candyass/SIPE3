package com.unsera.sipe3.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Pasal {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String namaPasal;
    private String keterangan;

    public Pasal() {

    }

    @Ignore
    public Pasal(long id, String namaPasal, String keterangan) {
        this.id = id;
        this.namaPasal = namaPasal;
        this.keterangan = keterangan;
    }

    @Ignore
    public Pasal(String namaPasal, String keterangan) {
        this.namaPasal = namaPasal;
        this.keterangan = keterangan;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNamaPasal() {
        return namaPasal;
    }

    public void setNamaPasal(String namaPasal) {
        this.namaPasal = namaPasal;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    @Override
    public boolean equals(Object obj) {
        Pasal p = (Pasal) obj;
        return this.id == p.id;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }
}
