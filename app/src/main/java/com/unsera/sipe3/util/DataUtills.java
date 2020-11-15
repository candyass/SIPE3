package com.unsera.sipe3.util;

import com.unsera.sipe3.database.AppDatabase;
import com.unsera.sipe3.model.*;

import java.util.ArrayList;
import java.util.List;

public class DataUtills {




    public static List<Daerah> getListDaerah() {
        List<Daerah> listDaerah = new ArrayList<>();
        listDaerah.add(new Daerah(1, "Serang", "Banten"));
        listDaerah.add(new Daerah(2, "Pandeglang", "Banten"));
        listDaerah.add(new Daerah(3, "Cilegon", "Banten"));
        listDaerah.add(new Daerah(4, "Tangerang", "Banten"));
        return listDaerah;
    }

    public static List<Kandidat> getListKandidat() {
        List<Kandidat> listKandidat = new ArrayList<>();
        listKandidat.add(new Kandidat(1, "Hj.Dicky Saputra S.Kom", "Moh.Sigit S.Kom", 1));
        listKandidat.add(new Kandidat(2, "Budi Santoso", "Erni Suherni", 1));
        listKandidat.add(new Kandidat(4, "Ari Munandar S.Kom", "Imannudin S.Kom", 2));
        return listKandidat;
    }

    public static List<Pasal> getListPasal() {
        List<Pasal> list = new ArrayList<>();
        list.add(new Pasal("488","Memberikan keterangan yang tidak benar"));
        list.add(new Pasal("489","Tidak Mengumumkan Dan/Atau Memperbaiki Daftar Pemilih Sementara"));
        list.add(new Pasal("490","Membuat Keputusan Dan/Atau Melakukan Tindakan Yang Menguntungkan Atau Merugikan Salah Satu Peserta Pemilu"));
        list.add(new Pasal("491","mengacaukan, menghalangi, atau mengganggu jalannya Kampanye Pemilu"));
        list.add(new Pasal("511","Menghalangi Seseorang Untuk Terdaftar Sebagai Pemilih"));
        list.add(new Pasal("512","Tidak Menindaklanjuti Temuan Bawaslu, Bawaslu Provinsi, Bawaslu Kabupaten / Kota,Panwaslu Kecamatan, Panwaslu Kelurahan/Desa, Dan/Atau Panwaslu LN"));
        list.add(new Pasal("513","Tidak Memberikan Salinan Daftar Pemilih Tetap Kepada Partai Politik Peserta Pemilu Ketentuan Pidana"));
        list.add(new Pasal("544","Memalsukan Data dan Daftar Pemilih"));
        list.add(new Pasal("545","Menambah Atau Mengurangi Daftar Pemilih Dalam Pemilu Setelah Ditetapkannya Daftar Pemilih Tetap"));
        return list;
    }

    public static List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User("111111", "admin", "Dicky", User.USER_PEMOHON, "dicky@gmail.com", "Komp.Taman Angsoka Permar RT 02 RW 08"));
        list.add(new User("222222", "admin", "Mamat", User.USER_PENERIMA, "admin@gmail.com", "Komp.Taman Angsoka Permar RT 02 RW 08"));
        return list;
    }
}
