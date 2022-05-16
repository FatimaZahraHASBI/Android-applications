package com.example.roomdatabaseapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //create database instance
    private static RoomDB database;
    //define database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        //check condition
        if (database == null){
            //initialize database
            database= Room.databaseBuilder(context, RoomDB.class, "User")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build();
        }
        //return database
        return database;
    }

    //Create DAO
    public abstract MainDao mainDao();
}