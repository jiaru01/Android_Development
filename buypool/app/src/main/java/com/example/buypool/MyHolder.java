package com.example.buypool;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    ImageView mImaeView;
    TextView mTitle , mDes;
    public MyHolder(@NonNull View itemView){
        super(itemView);
        this.mImaeView= itemView.findViewById(R.id.imageTv);
        this.mTitle= itemView.findViewById(R.id.TitleTv);
        this.mDes= itemView.findViewById(R.id.descriptionTv);

    }
}
