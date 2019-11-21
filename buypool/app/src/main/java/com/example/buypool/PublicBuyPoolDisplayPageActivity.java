package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PublicBuyPoolDisplayPageActivity extends AppCompatActivity {
    //    This Class uses on Card Your BuyPoolDsplay activitys or Public gallery activitys ,
//    This class used to control
    //1. Action bar
    //2.Implement returned information from database and fill it into each cards
    //3. go to other activity page i.e profile

    CurrentUserInfo userInfo;
    ArrayList<Model> models = new ArrayList<>();
    LocalDatabase helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pool_display_page);

        helper= new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        db = helper.getWritableDatabase();
        //Tool Bar starts from here
        Toolbar toolbar = findViewById(R.id.buyPool_toolbar_title);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Public Card Gallery");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        Change color of the overflow 'The three dot' on Tool bar
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
// Tool Bars ends here


        RecyclerView recyclerView = findViewById(R.id.recyclerViewBuyPoolPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        PublicCardAdapter myadapter = new PublicCardAdapter( this,getMyList(),userInfo);
        recyclerView.setAdapter(myadapter);


    }


    //modifiy the return data as and set model
    private ArrayList<Model> getMyList(){


//        Cursor cardlist = db.query("cards", null, null, null, null, null, null);
        userInfo = (CurrentUserInfo) getApplicationContext();
        String sql = "SELECT title,description,address,date,cards.phone_number,username,gender,cardStatus,cards.id FROM cards,usersremote where create_userID = usersremote.id AND cardStatus != 1 AND cardStatus != 2 AND create_userID != ?;";
        Cursor cardlist = db.rawQuery(sql, new String[]{""+userInfo.getID()});
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

    public boolean onCreateOptionsMenu(Menu menu) {
//        push menu icons onto the toolbars
        getMenuInflater().inflate(R.menu.buypooldisplaypage, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {
            //change page should be here
            Toast.makeText(getApplicationContext(), "Your click about", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "Your click about", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.toProfile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        } else if (id == R.id.Notification) {

            Intent intent = new Intent(this, NoticeActivity.class);
            startActivity(intent);
        }


        return true;

    }

}
