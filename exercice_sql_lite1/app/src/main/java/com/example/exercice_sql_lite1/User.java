package com.example.exercice_sql_lite1;

import androidx.room.*;

@Entity
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "full_name")
    public String name;


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}