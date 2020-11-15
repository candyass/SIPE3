package com.unsera.sipe3.model;

import android.graphics.Bitmap;

public class KandidatView {


    private long id;
    private int noUrut;
    private String nama;
    private String namaWakil;
    private String kota;
    private String provinsi;
    private Bitmap foto;
    private long daerahId;



    public KandidatView() {

    }

    public KandidatView(long id, int noUrut, String nama, String namaWakil, String kota, String provinsi, long daerahId) {
        this.setId(id);
        this.setNoUrut(noUrut);
        this.setNama(nama);
        this.setNamaWakil(namaWakil);
        this.setKota(kota);
        this.setProvinsi(provinsi);
        this.setDaerahId(daerahId);
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

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
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

    public long getDaerahId() {
        return daerahId;
    }

    public void setDaerahId(long daerahId) {
        this.daerahId = daerahId;
    }
}
