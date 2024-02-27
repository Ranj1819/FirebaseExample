
package com.example.firebaseexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name,age;

    Button btn1,btn2;

    RecyclerView recyclerView;
    ArrayList<InfoModel> infoModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        infoModels=new ArrayList<>();
        name=findViewById(R.id.name);
        age=findViewById(R.id.age);
        btn1=findViewById(R.id.button);
        btn2=findViewById(R.id.readButton);
        recyclerView=findViewById(R.id.recyclerForInfo);
        ArrayList<InfoModel> models=new ArrayList<>();

        ArrayList<InfoModel> recyclerViewArrayList=new ArrayList<>();

        InfoAdapter infoAdapter=new InfoAdapter(recyclerViewArrayList);
        recyclerView.setAdapter(infoAdapter);

        btn1.setOnClickListener(v->{
            InfoModel infoModel=new InfoModel(name.getText().toString(), Double.parseDouble(age.getText().toString()));
            models.add(infoModel);
            DatabaseReference db1= FirebaseDatabase.getInstance().getReference();
            db1.child("userInfo").setValue(models);
            recyclerViewArrayList.add(infoModel);

            DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("userInfo");

            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    recyclerViewArrayList.clear();
                    for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                        InfoModel model=dataSnapshot.getValue(InfoModel.class);
                        recyclerViewArrayList.add(model);
                    }
                    infoAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        });



        DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("userInfo");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    InfoModel model=dataSnapshot.getValue(InfoModel.class);
                    recyclerViewArrayList.add(model);
                }
                infoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        btn2.setOnClickListener(read->{

//        });
    }
}