package com.example.buypool;

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
        SendCardAdapter myadapter = new SendCardAdapter( this,getMyList());
        recyclerView.setAdapter(myadapter);



    }


    //modifiy the return data as and set model
    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();//We built this model
        m.setTitle("New 33 Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("14/11/2019");
        m.setAddress("roaThis is newsfeed descripstiond");
        m.setUserNameOnCard("Jack");
        m.setPhoneNumber("1231234");
        m.setCardStatus("on Public");

        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New 44Feeds");
        m.setDesription("This is newsfeed descripstiLorem ipsum dolor sit " +
                "\n amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uon");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("morephy");
        m.setPhoneNumber("087929292");
        m.setCardStatus("on Public");


        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("16/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("wahhha");
        m.setPhoneNumber("087929292");
        m.setCardStatus("on Public");


        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("wahhha");
        m.setPhoneNumber("087929292");
        m.setCardStatus("on Public");


        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setUserNameOnCard("wahhha");
        m.setPhoneNumber("087929292");

        m.setAddress("Grove");
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setUserNameOnCard("wahhha");
        m.setPhoneNumber("1231234");
        m.setCardStatus("on Public");

        m.setAddress("Grove");
        models.add(m);


        return models;
    }

}
