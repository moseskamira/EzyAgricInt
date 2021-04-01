package com.team295.ezyagric.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.team295.ezyagric.model.LandShape;
import com.team295.ezyagric.repository.LandShapeDB;

import java.util.List;

public class ShapeViewModel extends AndroidViewModel {
    private MutableLiveData<List<LandShape>> landShapeMutableLiveData;
    private MutableLiveData<String> landShapePostResponse;
    private LandShapeDB landShapeDB;

    public ShapeViewModel(Application application) {
        super(application);
        landShapeMutableLiveData = new MutableLiveData<>();
        landShapePostResponse = new MutableLiveData<>();
        landShapeDB = LandShapeDB.getLandShapeDBInstance(getApplication().getApplicationContext());
    }

    public LiveData<List<LandShape>> returnLandShapes() {
        List<LandShape> landShapes = landShapeDB.getLandShapeDAO().fetchAllLandShapes();
        if (!landShapes.isEmpty()) {
            landShapeMutableLiveData.postValue(landShapes);
        } else {
            landShapeMutableLiveData.postValue(null);
        }
        return landShapeMutableLiveData;
    }

    public LiveData<String> postShape(LandShape shape) {
        landShapeDB.getLandShapeDAO().insertLandShape(shape);
        landShapePostResponse.postValue("Successfully Saved Data");
        return landShapePostResponse;
    }
}
