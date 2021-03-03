package com.team295.ezyagric;

import androidx.appcompat.app.AppCompatActivity;
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

import com.team295.ezyagric.RoomDB.LandShape;
import com.team295.ezyagric.RoomDB.LandShapeDB;
import com.team295.ezyagric.adapter.LandShapeAdapter;

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

    private List<LandShape> landShapeList;

    String selectedShape;
    String landShapeInputAmount;
    private LandShapeDB landShapeDB;

    private LandShapeAdapter landShapeAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        landShapeSpinner.setAdapter(landShapeAdapter );

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
        LandShape landShape = new LandShape();
        landShape.landShape = selectedShape;
        landShape.inputAmount = landShapeInputAmount;
        landShapeDB = LandShapeDB.getLandShapeDBInstance(getApplicationContext());
        landShapeDB.getLandShapeDAO().insertlandShape(landShape);

    }

    private void retrieveAllLandShapes() {
        landShapeDB = LandShapeDB.getLandShapeDBInstance(getApplicationContext());
        if (!landShapeDB.getLandShapeDAO().fetchAllLandShapes().isEmpty()) {
            landShapeList = landShapeDB.getLandShapeDAO().fetchAllLandShapes();
            initializeRecyclerView(landShapeList);
        } else {
            Toast.makeText(MainActivity.this, "No Saved Land Shapes", Toast.LENGTH_LONG).show();
        }

    }

    private void initializeRecyclerView(List<LandShape> myShapeList) {
        songsRecyclerView.setHasFixedSize(true);
        landShapeAdapter = new LandShapeAdapter(this, myShapeList);
        landShapeAdapter.notifyDataSetChanged();
        songsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        songsRecyclerView.setAdapter(landShapeAdapter);
    }

}