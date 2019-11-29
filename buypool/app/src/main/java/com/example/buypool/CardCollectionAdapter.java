package com.example.buypool;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

public class CardCollectionAdapter extends RecyclerView.Adapter<MyHolder> {
    //    This Class Used as Adapter for recyclerView that located in CardCollectionAcitity class
    //what it does
    //1.receives all cards information and put into put those information on to cards
    //2. It also passes intent information of cards to activity_show_card_Details ,
    // as user clicks the card and wants to view detail description of that cards.
    Context c;
    ArrayList<Model> models;
    LocalDatabase helper;
    SQLiteDatabase db;
    CurrentUserInfo userInfo;
//     this array list create a list of array which parameter define in our model class

    public CardCollectionAdapter(Context c, ArrayList<Model> models, CurrentUserInfo userInfo) {
        this.c = c;
        this.models = models;
        helper = new LocalDatabase(c, "Cards", null, 1);
        db = helper.getWritableDatabase();
        this.userInfo = (CurrentUserInfo) userInfo;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.each_card_collected, null); //this line inflate our public_card
        return new MyHolder(view);//this will return our view to holder class
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int position) {
        final int position2 = position;
        if (models.get(position).getCardStatus() == 2) {
            myHolder.mCardCompeleted.setClickable(false);
            myHolder.DontWantCardCollected.setVisibility(View.INVISIBLE);
        } else if (models.get(position).getCardStatus() == 1) {
            myHolder.mCardCompeleted.setChecked(false);
        }
        myHolder.mTitle.setText(models.get(position).getTitle());
        myHolder.mDes.setText(models.get(position).getDesription());
        myHolder.mDate.setText(models.get(position).getDate());
        myHolder.mAddress.setText(models.get(position).getAddress());
        myHolder.mUserNameOnCard.setText(models.get(position).getUserNameOnCard());
        myHolder.mcardPhoneNumber.setText(models.get(position).getPhoneNumber());

        //add card finished function for switch
        myHolder.mCardCompeleted.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder bb = new AlertDialog.Builder(c);
                    bb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int cardID = models.get(position2).getCardID();
                            ContentValues contentValue = new ContentValues();
                            if (isChecked) {
                                contentValue.put("cardStatus", "2");
                                int cardUpdate = db.update("cards", contentValue, "id = ?", new String[]{"" + cardID});
                                if (cardUpdate == 1) {
                                    Toast.makeText(c, "Order is finished", Toast.LENGTH_LONG).show();
                                    myHolder.DontWantCardCollected.setVisibility(View.INVISIBLE);
                                    //update ui
                                    models.get(position2).setCardStatus(2);
                                    notifyDataSetChanged();
                                } else
                                    Toast.makeText(c, "Update error, please try it later!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    bb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            myHolder.mCardCompeleted.setChecked(false);
                            dialog.dismiss();
                        }
                    });
                    bb.setMessage("Are you sure you complete this order?");
                    bb.setTitle("Order Completion");
                    bb.show();
                }
            }
        });

        //drop collected card function
        myHolder.DontWantCardCollected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder bb = new AlertDialog.Builder(c);
                bb.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int cardID = models.get(position2).getCardID();
                        ContentValues contentValue = new ContentValues();
                        contentValue.put("cardStatus", "0");
                        int cardUpdate = db.update("cards", contentValue, "id = ?", new String[]{"" + cardID});
                        if (cardUpdate != 1) {
                            Toast.makeText(c, "Order is dropped unsuccessfully, please try it later!", Toast.LENGTH_LONG).show();
                            return;
                        }
                        int orderDelete = db.delete("orders", "cardID = ?", new String[]{"" + cardID});
                        if (orderDelete > 0) {
                            Toast.makeText(c, "Order is dropped successfully", Toast.LENGTH_LONG).show();
                            //update ui
                            models.remove(position2);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(c, "Order is dropped unsuccessfully, please try it later!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                bb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                bb.setMessage("Are you sure you want to drop this order?");
                bb.setTitle("Order Drop Confirmation");
                bb.show();
            }
        });


//        This is a way to get image from Resource drawable ,
//        but just leave it ;if we want to return image fro data base the this like should be change
        myHolder.mImaeView.setImageResource(models.get(position).getImg());

        //this is all part two
        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gAddress = models.get(position).getAddress();
                String gPhoneNumber = models.get(position).getPhoneNumber();
                String gUserNameOnCard = models.get(position).getUserNameOnCard();
                String gDate = models.get(position).getDate();

                String gTitle = models.get(position).getTitle();
                String gDesc = models.get(position).getDesription();//get data from previous activity
                BitmapDrawable bitmapDrawable = (BitmapDrawable) myHolder.mImaeView.getDrawable(); // this will get our image from drawble

                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream(); //image will get stream and bytes;

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);//this will compress our  image

                byte[] bytes = stream.toByteArray();

                //get out data with intent
                Intent intent = new Intent(c, ShowCardDetailActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDesc", gDesc);
                intent.putExtra("iImage", bytes);
                intent.putExtra("iAddress", gAddress);
                intent.putExtra("iPhoneNumber", gPhoneNumber);
                intent.putExtra("iUserNameOnCard", gUserNameOnCard);
                intent.putExtra("iDate", gDate);


                c.startActivity(intent);

            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }


}
