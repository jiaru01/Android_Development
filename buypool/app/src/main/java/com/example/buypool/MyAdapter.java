package com.example.buypool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;
//     this array list create a list of array which parameter define in our model class

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null); //this line inflate our row
        return new MyHolder(view);//this will return our view to holder class
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, int position) {
        myHolder.mTitle.setText(models.get(position).getTitle());
        myHolder.mDes.setText(models.get(position).getDesription());

//        This is a way to get image from Resource drawable ,
//        but just leave it ;if we want to return image fro data base the this like should be change
        myHolder.mImaeView.setImageResource(models.get(position).getImg());



    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
