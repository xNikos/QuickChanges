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

    @Query("SELECT * FROM data WHERE type = :selectedType")
    List<Data> selectType(int selectedType);

    @Query("SELECT przejechano FROM data WHERE type = :selectedType ORDER BY id DESC LIMIT 1")
    float przebieg(int selectedType);

    @Query("SELECT zatankowano FROM data WHERE id = :selectedID AND type = :selectedType ORDER BY" +
            " id DESC")
    float zatankowano(int selectedID, int selectedType);

}
