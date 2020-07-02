package com.unsera.sipe3.model;

public class PasalView {

    private long idPengaduan;
    private long idPasal;
    private String namaPasal;
    private String keterangan;


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
}
