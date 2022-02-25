package com.example.roomcrud;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Add database entities
@Database(entities = {MainData.class}, version = 1, exportSchema = false)
public abstract class Roomdb extends RoomDatabase{
    //create instancer of room database
    private static Roomdb database;
    //define database name
    private static final String DATABASE_NAME = "database";

    public synchronized static Roomdb getInstance(Context context){
        //check condition
        if (database == null){

            //when database is null
            //initialize database
            database = Room.databaseBuilder(context.getApplicationContext(),Roomdb.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        }
        //return database
        return database;
    }

    //create Dao
    public abstract MainDAO mainDAO();

}
