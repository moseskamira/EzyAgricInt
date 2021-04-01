package com.team295.ezyagric.util;

import com.team295.ezyagric.model.Land;
import com.team295.ezyagric.model.LandShape;

import java.util.ArrayList;
import java.util.List;

public class DataMapper implements DataMapperInterface<LandShape, Land> {

    @Override
    public Land mapFromEntity(LandShape landShape) {
        return new Land(
                landShape.landShape,
                landShape.inputAmount
        );
    }

    @Override
    public LandShape mapToEntity(Land land) {
        return new LandShape(
                land.getShape(),
                land.getAmount()
        );
    }

    public List<Land> mapFromEntityList(List<LandShape> landShapes) {
        List<Land> lands = new ArrayList<>();
        for (LandShape landShape : landShapes) {
            lands.add(mapFromEntity(landShape));
        }
        return lands;
    }
}
