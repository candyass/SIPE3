package com.unsera.sipe3.model;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {@ForeignKey(entity = Kandidat.class, parentColumns = "id", childColumns = "idKandidat")
        , @ForeignKey(entity = User.class, parentColumns = "noKTP", childColumns = "noKTP")})
public class Pengaduan {

    public static int PIDANA_PROSES = 1;
    public static int PIDANA_DITOLAK = 2;
    public static int PIDANA_DITERIMA = 3;

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long idKandidat;
    @NonNull
    private String noKTP;
    private Date tanggal;
    private int pidana;
    private String status;
    private int terpilih;
    @Nullable
    private Bitmap foto;

    public Pengaduan() {

    }

    @Ignore
    public Pengaduan(long idKandidat, String noKTP) {
        this.idKandidat = idKandidat;
        this.tanggal = new Date();
        this.pidana = PIDANA_PROSES;
        terpilih = 0;
        this.noKTP = noKTP;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdKandidat() {
        return idKandidat;
    }

    public void setIdKandidat(long idKandidat) {
        this.idKandidat = idKandidat;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public void setPidana(int pidana) {
        this.pidana = pidana;
    }

    public int getPidana() {
        return pidana;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        if(pidana == PIDANA_PROSES) {
            status = "Pengajuan Diproses";
        }else if(pidana == PIDANA_DITERIMA) {
            status = "Pengajuan Diterima";
        }else if(pidana == PIDANA_DITOLAK) {
            status = "Pengajuan Ditolak";
        }
        return status;
    }

    public int getTerpilih() {
        return terpilih;
    }

    public void setTerpilih(int terpilih) {
        this.terpilih = terpilih;
    }


    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }


    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
