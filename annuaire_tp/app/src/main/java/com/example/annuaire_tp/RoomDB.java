package com.example.annuaire_tp;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Database(entities = {Contact.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    //create database instance
    private static RoomDB database;
    //define database name
    private static String DATABASE_NAME = "database";

    private static Context activity;

    public synchronized static RoomDB getInstance(Context context){
        activity = context.getApplicationContext();
        //check condition
        if (database == null){
            //initialize database
            database= Room.databaseBuilder(context, RoomDB.class, "Contact")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build();
        }
        //return database
        return database;
    }

    //Create DAO
    public abstract ContactDao contactDao();


    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(database).execute();
        }
    };

    public static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{
        private ContactDao contactDao;
        public PopulateDBAsyncTask(RoomDB db){
            contactDao = db.contactDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            contactDao.insert(new Contact("Fatima Zahra Hasbi", "photo2", "engineer", "hasbi@gmail.com", "0693833002"));
            contactDao.insert(new Contact("Hasnaa Hasbi", "photo2", "engineer", "hasbi@gmail.com", "0669286427"));
            FillwithStartingData(activity);
            return null;
        }
    }

    private static void FillwithStartingData(Context context){
        ContactDao dao = getInstance(context).contactDao();

        JSONArray contacts = loadJsonArray(context);
        try {
            for (int i=0; i<contacts.length(); i++){
                JSONObject contact = contacts.getJSONObject(i);
                String name = contact.getString("name");
                String email = contact.getString("email");
                String phone = contact.getString("phone");
                String job = contact.getString("job");
                String photo = contact.getString("photo");

                dao.insert(new Contact(name, photo, job, email, phone));
            }
        }catch (JSONException exception){
            exception.printStackTrace();
        }
    }

    private static JSONArray loadJsonArray(Context context){
        StringBuilder stringBuilder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.contact_data);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;

        try {
            while ((line = reader.readLine()) != null){
                stringBuilder.append(line);
            }
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());

            return jsonObject.getJSONArray("contacts");
        }catch (IOException | JSONException exception){
            exception.printStackTrace();
        }
        return null;
    }


}











