package com.example.buypool;

import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SendCardActivity extends AppCompatActivity {
//    This Class uses on Card Your sent activitys ,
//    This class used to control
    //1. Action bar
    //2.Implement returned information from database and fill it into each cards
    CurrentUserInfo currentUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_send);

        //Set Tool Bar starts here

        Toolbar toolbar = findViewById(R.id.SendCardTooBar);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Cards Your Uploaded");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

//Sets tool bar ends here

        RecyclerView recyclerView = findViewById(R.id.recyclerViewSendCard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        SendCardAdapter myadapter = new SendCardAdapter( this,getMyList(),currentUserInfo);
        recyclerView.setAdapter(myadapter);



    }


    //modifiy the return data as and set model
    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        //GET ALL THE cards the current_user collected
        String sql = "SELECT title,description,address,date,cards.phone_number,username,gender,cardStatus,cards.id FROM cards,usersremote where create_userID = usersremote.id AND create_userID = ?;";
         currentUserInfo= (CurrentUserInfo) getApplicationContext();
        Cursor cardlist = db.rawQuery(sql, new String[]{""+currentUserInfo.getID()});
        while (cardlist.moveToNext()){
            String title = cardlist.getString(0);
            String description = cardlist.getString(1);
            String address = cardlist.getString(2);
            String time = cardlist.getString(3);
            String phoneNumber = cardlist.getString(4);
            String username = cardlist.getString(5);
            int gender = cardlist.getInt(6);
            int cardStatus = cardlist.getInt(7);
            int cardID = cardlist.getInt(8);
            Model m = new Model();
            m.setTitle(title);
            m.setDesription(description);
            m.setImg(gender == 0?R.drawable.male:R.drawable.female);
            m.setDate(time);
            m.setAddress(address);
            m.setUserNameOnCard(username);
            m.setPhoneNumber(phoneNumber);
            m.setCardStatus(cardStatus);
            m.setCardID(cardID);
            models.add(m);
        }

        return models;
    }

}
