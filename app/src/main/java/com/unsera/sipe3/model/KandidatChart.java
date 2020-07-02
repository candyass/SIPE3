package com.unsera.sipe3.model;

public class KandidatChart {

    private int noUrut;
    private String nama;
    private String namaWakil;
    private String kota;
    private String provinsi;
    private int totalAduan = 0;
    private String status;


    public KandidatChart() {

    }

    public KandidatChart(String nama, String namaWakil, int totalAduan) {
        this.nama = nama;
        this.namaWakil = namaWakil;
        this.totalAduan = totalAduan;
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

    public int getTotalAduan() {
        return totalAduan;
    }

    public void setTotalAduan(int totalAduan) {
        this.totalAduan = totalAduan;
    }

    public String getStatus() {
        if(totalAduan == 0) {
            status = "Kosong";
        }else if(totalAduan <= 2) {
            status = "Normal";
        }else if(totalAduan >= 3 ) {
            status = "Berat";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNoUrut() {
        return noUrut;
    }

    public void setNoUrut(int noUrut) {
        this.noUrut = noUrut;
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
        return "Nama : " + nama + "\n"
                + "Nama Wakil : " + namaWakil + "\n"
                + "Total Aduan : " + totalAduan + "\n"
                + "Status : " + getStatus();
    }



}
