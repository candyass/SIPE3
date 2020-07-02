package com.unsera.sipe3.event;

import com.unsera.sipe3.model.Kandidat;

public class KandidatEvent {

    private long id;
    private String nama;
    private String namaWakil;
    private String kota;
    private String provinsi;

    public KandidatEvent() {

    }

    public KandidatEvent(long id, String nama, String namaWakil, String provinsi, String kota) {
        this.id = id;
        this.nama = nama;
        this.namaWakil = namaWakil;
        this.provinsi = provinsi;
        this.kota = kota;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
