package com.team295.ezyagric.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.team295.ezyagric.R;
import com.team295.ezyagric.model.LandShape;
import com.team295.ezyagric.util.DataMapper;
import com.team295.ezyagric.model.Land;
import com.team295.ezyagric.adapter.LandShapeAdapter;
import com.team295.ezyagric.viewModel.ShapeViewModel;
import com.team295.ezyagric.viewModel.ShapeViewModelFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.land_shape_spinner)
    Spinner landShapeSpinner;
    @BindView(R.id.input_amount_tv)
    TextView landShapeInputAmountTv;
    @BindView(R.id.save_shape_btn)
    Button saveShapeInputBtn;
    @BindView(R.id.view_shape_btn)
    Button viewLandShapesBtn;
    @BindView(R.id.prod_list_recycler_view)
    RecyclerView songsRecyclerView;
    private List<Land> landShapeList;
    String selectedShape;
    String landShapeInputAmount;
    private ShapeViewModel shapeViewModel;
    private DataMapper dataMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ShapeViewModelFactory shapeViewModelFactory = ShapeViewModelFactory
                .returnFactoryInstance(getApplication());
        shapeViewModel = new ViewModelProvider(this, shapeViewModelFactory)
                .get(ShapeViewModel.class);
        dataMapper = new DataMapper();
        populateSpinner();
        saveShapeInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToRoomDB();
            }
        });
        viewLandShapesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveAllLandShapes();
            }
        });
        landShapeList = new ArrayList<>();

    }

    private void populateSpinner() {
        final ArrayAdapter<CharSequence> landShapeAdapter = ArrayAdapter.createFromResource(
                this, R.array.land_shape_array, R.layout.support_simple_spinner_dropdown_item);
        landShapeSpinner.setAdapter(landShapeAdapter);
        landShapeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedShape = parent.getItemAtPosition(position).toString();
                switch (selectedShape) {
                    case "One Acre Land":
                        landShapeInputAmount = "650 KG";
                        landShapeInputAmountTv.setText(landShapeInputAmount);
                        break;
                    case "Five Acre Land":
                        landShapeInputAmount = "950 KG";
                        landShapeInputAmountTv.setText(landShapeInputAmount);
                        break;
                    case "Seven Acre Land":
                        landShapeInputAmount = "1050 KG";
                        landShapeInputAmountTv.setText(landShapeInputAmount);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void saveToRoomDB() {
        Land myLand = new Land(selectedShape, landShapeInputAmount);
        shapeViewModel.postShape(dataMapper.mapToEntity(myLand)).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (!s.isEmpty()) {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed To Save Shape", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void retrieveAllLandShapes() {
        shapeViewModel.returnLandShapes().observe(this, new Observer<List<LandShape>>() {
            @Override
            public void onChanged(List<LandShape> myLandShapeList) {
                if (myLandShapeList.size() > 0) {
                    landShapeList = dataMapper.mapFromEntityList(myLandShapeList);
                }
                initializeRecyclerView();
            }
        });
    }

    private void initializeRecyclerView() {
        songsRecyclerView.setHasFixedSize(true);
        LandShapeAdapter landShapeAdapter = new LandShapeAdapter(this, landShapeList);
        landShapeAdapter.notifyDataSetChanged();
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songsRecyclerView.setAdapter(landShapeAdapter);
    }
}