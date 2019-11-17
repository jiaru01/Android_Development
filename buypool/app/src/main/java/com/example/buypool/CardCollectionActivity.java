package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardCollectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_collection);


//Set Tool Bar starts here

        Toolbar toolbar = findViewById(R.id.CardCollectionTooBar);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Card Collection");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

//Sets tool bar ends here


        RecyclerView recyclerView = findViewById(R.id.recyclerViewCardCollection);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        MyAdapter Myadapter = new MyAdapter( this,getMyList());
        recyclerView.setAdapter(Myadapter);




    }
    //modifiy the return data as and set model
    private ArrayList<Model> getMyList(){

        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();//We built this model
        m.setTitle("here is card collection baby ");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("14/11/2019");
        m.setAddress("road");
        m.setUserNameOnCard("Jack");
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New 44Feeds");
        m.setDesription("This is newsfeed descripstiLorem ipsum dolor sit " +
                "\n amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uon");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("morephy");
        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("16/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("wahhha");

        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setAddress("Grove");
        m.setUserNameOnCard("wahhha");

        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setUserNameOnCard("wahhha");

        m.setAddress("Grove");
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        m.setDate("15/11/2019");
        m.setUserNameOnCard("wahhha");

        m.setAddress("Grove");
        models.add(m);


        return models;
    }


}