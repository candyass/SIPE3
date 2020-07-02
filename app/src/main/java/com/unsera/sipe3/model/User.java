package com.unsera.sipe3.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {

    public static int USER_PEMOHON = 1;
    public static int USER_PENERIMA = 2;

    @PrimaryKey
    @NonNull
    private String noKTP;
    private String password;
    private String nama;
    private String email;
    private int userType;

    public User() {

    }

    @Ignore
    public User(String noKTP, String password, String nama, int userType, String email) {
        this.setNoKTP(noKTP);
        this.setPassword(password);
        this.setNama(nama);
        this.userType = userType;
        this.setEmail(email);
    }




    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @NonNull
    public String getNoKTP() {
        return noKTP;
    }

    public void setNoKTP(@NonNull String noKTP) {
        this.noKTP = noKTP;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
