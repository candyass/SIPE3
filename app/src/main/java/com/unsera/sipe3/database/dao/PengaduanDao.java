package com.unsera.sipe3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.unsera.sipe3.model.*;

import java.util.List;
@Dao
public abstract class PengaduanDao {

    @Insert
    abstract  long insertPengaduan(Pengaduan pengaduan);

    @Insert
    abstract  void insertPelanggaran(List<Pelanggaran> pelanggaran);

    @Query("SELECT p.id, k.nama, namaWakil, kota, provinsi, tanggal, status, terpilih, pidana, ps.jumlahPasal, u.nama as namaPelapor, u.noKTP, u.email " +
            "FROM (SELECT COUNT(*) as jumlahPasal, idPengaduan FROM Pelanggaran GROUP BY idPengaduan) as ps " +
            "LEFT OUTER JOIN Pengaduan p  ON ps.idPengaduan = p.id JOIN Kandidat k ON p.idKandidat = k.id JOIN Daerah d ON k.daerahId = d.id JOIN User u ON p.noKTP = u.noKTP WHERE p.terpilih = 1")
    abstract  public LiveData<List<PengaduanView>> getListPengaduanViewTerpilih();

    @Query("SELECT p.id, k.nama, namaWakil, kota, provinsi, tanggal, status, terpilih, pidana, ps.jumlahPasal, u.nama as namaPelapor, u.noKTP, u.email " +
            "FROM (SELECT COUNT(*) as jumlahPasal, idPengaduan FROM Pelanggaran GROUP BY idPengaduan) as ps " +
            "LEFT OUTER JOIN Pengaduan p  ON ps.idPengaduan = p.id JOIN Kandidat k ON p.idKandidat = k.id JOIN Daerah d ON k.daerahId = d.id JOIN User u ON p.noKTP = u.noKTP")
    public abstract LiveData<List<PengaduanView>> getListPengaduanView();

    @Query("SELECT p.id, k.nama, namaWakil, kota, provinsi, tanggal, status, terpilih, pidana, ps.jumlahPasal, u.nama as namaPelapor, u.noKTP, u.email " +
            "FROM (SELECT COUNT(*) as jumlahPasal, idPengaduan FROM Pelanggaran GROUP BY idPengaduan) as ps " +
            "LEFT OUTER JOIN Pengaduan p  ON ps.idPengaduan = p.id JOIN Kandidat k ON p.idKandidat = k.id JOIN Daerah d ON k.daerahId = d.id JOIN User u ON p.noKTP = u.noKTP WHERE p.terpilih = 0")
    public abstract LiveData<List<PengaduanView>> getListPengaduanViewDiproses();

    @Query("SELECT p.id, k.nama, namaWakil, kota, provinsi, tanggal, status, terpilih, pidana, ps.jumlahPasal, u.nama as namaPelapor, u.noKTP, u.email " +
            "FROM (SELECT COUNT(*) as jumlahPasal, idPengaduan FROM Pelanggaran GROUP BY idPengaduan) as ps " +
            "LEFT OUTER JOIN Pengaduan p  ON ps.idPengaduan = p.id JOIN Kandidat k ON p.idKandidat = k.id JOIN Daerah d ON k.daerahId = d.id JOIN User u ON p.noKTP = u.noKTP WHERE p.id =:id")
    public abstract LiveData<PengaduanView> getPengaduanView(long id);

    @Query("SELECT idPengaduan, idPasal, namaPasal, keterangan " +
            "FROM Pelanggaran p JOIN Pasal s ON p.idPasal = s.id WHERE idPengaduan =:id")
    public abstract LiveData<List<PasalView>> getListPasalView(long id);

    @Query("UPDATE Pengaduan SET pidana = 2, status ='Pengajuan Ditolak', terpilih = 1 WHERE id =:idPengaduan")
    public abstract void tolakPengaduan(long idPengaduan);

    @Query("UPDATE Pengaduan SET pidana = 3, status ='Pengajuan Diterima' , terpilih = 1  WHERE id =:idPengaduan")
    public abstract void terimaPengaduan(long idPengaduan);

    @Transaction
    public void pengajuanPengaduan(Pengaduan pengaduan, List<Pelanggaran> list) {
        long id = insertPengaduan(pengaduan);
        for(Pelanggaran p : list) {
            p.setIdPengaduan(id);
        }
        insertPelanggaran(list);
    }

    @Query("SELECT k.noUrut, d.kota, d.provinsi, k.nama, k.namaWakil, kp.totalAduan FROM Kandidat k LEFT OUTER JOIN (SELECT idKandidat, COUNT(idKandidat)as totalAduan FROM Pengaduan GROUP BY idKandidat ) as kp ON k.id = kp.idKandidat JOIN Daerah d ON d.id = k.daerahId")
    public abstract LiveData<List<KandidatChart>> getChartkandidat();
}
