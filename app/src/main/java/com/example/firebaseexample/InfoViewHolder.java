package com.example.firebaseexample;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    TextView name,age;
    public InfoViewHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.NameText);
        age=itemView.findViewById(R.id.ageText);
    }
}
