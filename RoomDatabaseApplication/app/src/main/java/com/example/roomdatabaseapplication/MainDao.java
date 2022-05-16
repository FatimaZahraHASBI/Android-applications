package com.example.roomdatabaseapplication;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
    void insert(MainData mainData);

    @Delete
    void delete(MainData mainData);

    @Delete
    void reset(List<MainData> mainData);

    @Query("UPDATE User SET name = :nameText WHERE id = :userID ")
    void update(int userID, String nameText );

    @Query("SELECT * FROM User")
    List<MainData> getAll();
}
