package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class CardCollectionActivity extends AppCompatActivity {
//    This Class uses on Card Your collected activitys ,
//    This class used to control
    //1. Action bar
    //2.Implement returned information from database and fill it into each cards

    CurrentUserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);


//Set Tool Bar starts here

        Toolbar toolbar = findViewById(R.id.CardCollectionTooBar);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Cards You Collected");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

//Sets tool bar ends here

//Create of RecyclerView - starts here
        RecyclerView recyclerView = findViewById(R.id.recyclerViewCardCollection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        CardCollectionAdapter Myadapter = new CardCollectionAdapter(this, getMyList(), userInfo);
        recyclerView.setAdapter(Myadapter);

//RecyclerView Ends Here


    }

    //modifiy the return data as and set model
    private ArrayList<Model> getMyList() {

        ArrayList<Model> models = new ArrayList<>();
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        //GET ALL THE cards the current_user collected
        userInfo = (CurrentUserInfo) getApplicationContext();
        String sql = "SELECT title,description,address,date,cards.phone_number,username,gender,cardStatus,cards.id FROM cards,orders,usersremote where cards.id = orders.cardID AND orders.order_userID = ? AND cards.create_userID = usersremote.id;";
        Cursor cardlist = db.rawQuery(sql, new String[]{"" + userInfo.getID()});
        cardlist.moveToLast();
        cardlist.moveToNext();
        while (cardlist.moveToPrevious()) {
            String title = cardlist.getString(0);
            String description = cardlist.getString(1);
            String address = cardlist.getString(2);
            String time = cardlist.getString(3).split(" ")[0];
            String phoneNumber = cardlist.getString(4);
            String username = cardlist.getString(5);
            int gender = cardlist.getInt(6);
            int cardStatus = cardlist.getInt(7);
            int cardID = cardlist.getInt(8);
            Model m = new Model();
            m.setTitle(title);
            m.setDesription(description);
            m.setImg(gender == 0 ? R.drawable.male : R.drawable.female);
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
