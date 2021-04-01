package com.team295.ezyagric.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.team295.ezyagric.model.LandShape;

@Database(entities = {LandShape.class}, version = 1, exportSchema = false)
public abstract class LandShapeDB extends RoomDatabase {

    public abstract LandShapeDAO getLandShapeDAO();
    private static LandShapeDB landShapeDBInstance;

    public static LandShapeDB getLandShapeDBInstance(Context context) {
        if (landShapeDBInstance == null) {
            synchronized(LandShapeDB.class) {
                if(landShapeDBInstance == null) {
                    landShapeDBInstance = Room.databaseBuilder(context.getApplicationContext(),
                            LandShapeDB.class, "LandShapeDatabase").allowMainThreadQueries()
                            .build();
                }
            }
        }
        return landShapeDBInstance;
    }

    public void destroyDatabase() {
        landShapeDBInstance = null;
    }

}
