package com.team295.ezyagric.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "landShapeTable", indices = {@Index(value = {"landShape", "inputAmount"}, unique = true)})
public class LandShape {
    @PrimaryKey
    @NonNull
    public String landShape;

    @ColumnInfo(name = "inputAmount")
    public String inputAmount;

}
