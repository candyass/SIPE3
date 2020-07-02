package com.unsera.sipe3.model;

import java.util.Date;

public class PengaduanView {

    private long id;
    private String nama;
    private String namaWakil;
    private String kota;
    private String provinsi;
    private Date tanggal;
    private String status;
    private int terpilih;
    private int pidana;
    private int jumlahPasal;
    private String namaPelapor;
    private String noKTP;
    private String email;



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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJumlahPasal() {
        return jumlahPasal;
    }

    public void setJumlahPasal(int jumlahPasal) {
        this.jumlahPasal = jumlahPasal;
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

    public int getTerpilih() {
        return terpilih;
    }

    public void setTerpilih(int terpilih) {
        this.terpilih = terpilih;
    }

    public int getPidana() {
        return pidana;
    }

    public void setPidana(int pidana) {
        this.pidana = pidana;
    }


    public String getNamaPelapor() {
        return namaPelapor;
    }

    public void setNamaPelapor(String namaPelapor) {
        this.namaPelapor = namaPelapor;
    }


    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(String noKTP) {
        this.noKTP = noKTP;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
