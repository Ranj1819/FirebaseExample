package com.example.firebaseexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {
    ArrayList<InfoModel> infoModelArrayList;

    public InfoAdapter(ArrayList<InfoModel> infoModelArrayList) {
        this.infoModelArrayList = infoModelArrayList;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.info_recycler, parent, false);
        return new InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        InfoModel infoModel=infoModelArrayList.get(position);

        holder.name.setText(infoModel.getName());
        holder.age.setText(String.valueOf(infoModel.getAge()));
    }

    @Override
    public int getItemCount() {
        return infoModelArrayList.size();
    }
}
