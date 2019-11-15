package com.example.buypool;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView mImaeView;
    TextView mTitle, mDes, mDate, mAddress, mUserNameOnCard;

    ItemClickListener itemClickListener;


    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImaeView = itemView.findViewById(R.id.imageTv);
        this.mTitle = itemView.findViewById(R.id.TitleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.mDate = itemView.findViewById(R.id.date_idTv);
        this.mAddress = itemView.findViewById(R.id.address_idTv);
        this.mUserNameOnCard = itemView.findViewById(R.id.UserNameOnCard);

        //this is part2 , add listener
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.itemClickListener.onItemClickListener(v, getLayoutPosition());

    }

    //THIS IS PART 2 , add listener
    public void setItemClickListener(ItemClickListener ic) {
        this.itemClickListener = ic;
    }
}
