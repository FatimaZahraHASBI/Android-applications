package com.example.annuaire_tp;

import static androidx.room.OnConflictStrategy.REPLACE;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
    @Insert(onConflict = REPLACE)
    void insert(Contact contact);

    @Delete
    void delete(Contact contact);

    @Delete
    void reset(List<Contact> contacts);

    @Query("UPDATE Contact SET name=:nameText and photo=:photoText and job=:jobText and email=:emailText and phone=:phoneText WHERE id = :userID ")
    void update(int userID, String nameText, String photoText, String jobText, String emailText, String phoneText);



    @Query("SELECT * FROM Contact ORDER BY name ASC")
    LiveData<List<Contact>> getAllContacts();
}
