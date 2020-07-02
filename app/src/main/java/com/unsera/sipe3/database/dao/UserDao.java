package com.unsera.sipe3.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.unsera.sipe3.model.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUser(User user);

    @Insert
    void inserUserList(List<User> list);

    @Query("SELECT * FROM User WHERE noKTP =:noKTP AND password =:password")
    User getUser(String noKTP, String password);

    @Query("SELECT * FROM User WHERE noKTP =:noKTP")
    LiveData<User> getUser(String noKTP);


}
