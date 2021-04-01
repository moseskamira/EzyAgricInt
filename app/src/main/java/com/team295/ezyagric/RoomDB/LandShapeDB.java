package com.team295.ezyagric.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.team295.ezyagric.model.LandShape;
import com.team295.ezyagric.repository.LandShapeDAO;

@Database(entities = {LandShape.class}, version = 1, exportSchema = false)
public abstract class LandShapeDB extends RoomDatabase {

    public abstract LandShapeDAO getLandShapeDAO();

    private static LandShapeDB landShapeDB;

    public static LandShapeDB getLandShapeDBInstance(Context context) {

        if (landShapeDB == null) {
            synchronized(LandShapeDB.class) {
                if(landShapeDB == null) {
                    landShapeDB = Room.databaseBuilder(context.getApplicationContext(),
                            LandShapeDB.class, "LandShapeDatabase")
                            .allowMainThreadQueries().build();
                }
            }
        }
        return landShapeDB;
    }

    public void destroyDatabase() {
        landShapeDB = null;
    }

}
