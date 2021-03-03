package com.team295.ezyagric.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.team295.ezyagric.R;
import com.team295.ezyagric.RoomDB.LandShape;
import java.util.List;

public class LandShapeAdapter extends RecyclerView.Adapter<LandShapeAdapter.LandShapeViewHolder> {
    private Context context;
    private List<LandShape> landShapeList;

    public LandShapeAdapter(Context context, List<LandShape> myList) {
        this.context = context;
        this.landShapeList = myList;
    }

    @NonNull
    @Override
    public LandShapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View landShapeView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.land_shapes_view, parent, false);
        RecyclerView.LayoutParams landShapeViewParams = new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        landShapeView.setLayoutParams(landShapeViewParams);
        return new LandShapeViewHolder(landShapeView);
    }

    @Override
    public void onBindViewHolder(@NonNull LandShapeAdapter.LandShapeViewHolder holder, int position) {

        holder.landShapeNameTv.setText(landShapeList.get(position).landShape);
        holder.inputAmountTv.setText(landShapeList.get(position).inputAmount);
    }

    @Override
    public int getItemCount() {
        return landShapeList.size();
    }

    public class LandShapeViewHolder  extends RecyclerView.ViewHolder{

        TextView landShapeNameTv;
        TextView inputAmountTv;

        public LandShapeViewHolder(@NonNull View itemView) {
            super(itemView);
            landShapeNameTv = itemView.findViewById(R.id.my_land_shape_name);
            inputAmountTv = itemView.findViewById(R.id.my_input_amount);

        }
    }
}
