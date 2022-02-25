package com.example.roomcrud;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Index;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MainDAO {
    //Insert query
    @Insert(onConflict = REPLACE)
    void insert (MainData mainData);


    //delete query
    @Delete
    void delete(MainData mainData);

    //delete all querry
    @Delete
    void reset(List<MainData> mainData);

    //update query
    @Query("UPDATE records SET name = :sname, amount = :samount WHERE ID = :sID")
    void update(int sID,String sname,String samount);

    //Get all data query
    @Query("SELECT * FROM records")
    List<MainData> getAllrecords();

}
