package com.rinworks.nikos.fuelfullpaliwoikoszty.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DataDAO {
    @Query("SELECT * FROM data")
    List<Data> getAll();

    @Insert
    void insertAll(Data... data);
}
