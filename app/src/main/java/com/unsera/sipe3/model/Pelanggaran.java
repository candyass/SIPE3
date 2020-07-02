package com.unsera.sipe3.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(entity = Pasal.class, parentColumns = "id", childColumns = "idPasal"),
        @ForeignKey(entity = Pengaduan.class, parentColumns = "id", childColumns = "idPengaduan")}, primaryKeys = {"idPengaduan","idPasal"})
public class Pelanggaran {


    private long idPengaduan;
    private long idPasal;

    public Pelanggaran() {

    }

    @Ignore
    public Pelanggaran(long idPasal) {
        this.idPasal = idPasal;
    }


    public long getIdPengaduan() {
        return idPengaduan;
    }

    public void setIdPengaduan(long idPengaduan) {
        this.idPengaduan = idPengaduan;
    }

    public long getIdPasal() {
        return idPasal;
    }

    public void setIdPasal(long idPasal) {
        this.idPasal = idPasal;
    }
}
