package com.example.exercice_sql_lite1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, exportSchema = false, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    private static UserDatabase instance;

    static UserDatabase getDatabase(final Context context){
        if (instance==null){
            synchronized (UserDatabase.class){
                instance= Room.databaseBuilder(context, UserDatabase.class, "DATABASE").build();
            }
        }
        return instance;
    }
}
