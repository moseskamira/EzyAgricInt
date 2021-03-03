package com.team295.ezyagric.RoomDB;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LandShapeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertlandShape(LandShape landShape);

    @Query("SELECT * FROM landShapeTable")
    List<LandShape> fetchAllLandShapes();
}
