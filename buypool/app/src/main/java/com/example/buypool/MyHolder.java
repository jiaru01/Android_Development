package com.example.buypool;

import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //This class used with Recycle view i.e card implementation
    //This class names a variable i.e those variable associated with find each ID on the cards ,
    //Thos variable the used in each recycler view adapter
    ImageView mImaeView;
    TextView mTitle, mDes, mDate, mAddress, mUserNameOnCard,mcardPhoneNumber,mCardStatus;
    Switch mCardCompeleted,mDontWantCard;
    FloatingActionButton addCard,DontWantCardCollected,DontWantCardSent;

    ItemClickListener itemClickListener;


    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.mImaeView = itemView.findViewById(R.id.imageTv);
        this.mTitle = itemView.findViewById(R.id.TitleTv);
        this.mDes = itemView.findViewById(R.id.descriptionTv);
        this.mDate = itemView.findViewById(R.id.date_idTv);
        this.mAddress = itemView.findViewById(R.id.address_idTv);
        this.mcardPhoneNumber = itemView.findViewById(R.id.cardPhoneNumber);
        this.mUserNameOnCard = itemView.findViewById(R.id.UserNameOnCard);
        this.mCardStatus = itemView.findViewById(R.id.CurrentCardStatues);
        this.mCardCompeleted = itemView.findViewById(R.id.CardFinishedOrNot);
        this.DontWantCardCollected = itemView.findViewById(R.id.DontWantCardCollected);
        this.DontWantCardSent = itemView.findViewById(R.id.DontWantCardSent);
        this.addCard = itemView.findViewById(R.id.floatingActionButton);

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
