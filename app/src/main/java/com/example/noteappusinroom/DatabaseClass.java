package com.example.noteappusinroom;

import android.content.ContentValues;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(version = 1 , exportSchema = false ,entities = {TaskModel.class})
public abstract class DatabaseClass extends RoomDatabase {
    private static DatabaseClass databaseClass;


    public static DatabaseClass getDatabaseClass(Context context) {
        if (databaseClass==null){
            databaseClass = Room.databaseBuilder(context.getApplicationContext(),DatabaseClass.class,"db_task").
                    allowMainThreadQueries()
                    .build();
        }
        return databaseClass;
    }
    public abstract TaskDao getTaskDao();
}
