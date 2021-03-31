package com.team295.ezyagric.repository;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.team295.ezyagric.model.LandShape;

import java.util.List;

@Dao
public interface LandShapeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertLandShape(LandShape landShape);

    @Query("SELECT * FROM landShapeTable")
    List<LandShape> fetchAllLandShapes();
}
