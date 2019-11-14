package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class BuyPoolDisplayPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pool_display_page);

        //Tool Bar starts from here
        Toolbar toolbar = findViewById(R.id.buyPool_toolbar_title);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("BuyPool");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        Change color of the overflow 'The three dot' on Tool bar
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
// Tool Bars ends here


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        MyAdapter Myadapter = new MyAdapter( this,getMyList());
        recyclerView.setAdapter(Myadapter);


    }

    private ArrayList<Model> getMyList(){
        ArrayList<Model> models = new ArrayList<>();

        Model m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

         m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstiLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uLorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt uon");
        m.setImg(R.drawable.male);
        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

         m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);


        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

        m = new Model();//We built this model
        m.setTitle("New Feeds");
        m.setDesription("This is newsfeed descripstion");
        m.setImg(R.drawable.male);
        models.add(m);

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
