package com.team295.ezyagric.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ShapeViewModelFactory implements ViewModelProvider.Factory {
    private static Application application;

    private static ShapeViewModelFactory shapeViewModelFactoryInstance;

    public static ShapeViewModelFactory returnFactoryInstance(Application appln) {
        application = appln;
        if (shapeViewModelFactoryInstance == null) {
            synchronized (ShapeViewModelFactory.class) {
                if (shapeViewModelFactoryInstance == null) {
                    shapeViewModelFactoryInstance = new ShapeViewModelFactory();
                }
            }
        }
        return shapeViewModelFactoryInstance;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ShapeViewModel.class)) {
            return (T) new ShapeViewModel(application);
        }
        throw new IllegalArgumentException("ViewModel Not Found");

    }
}
