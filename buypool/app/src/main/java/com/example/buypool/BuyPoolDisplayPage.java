package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class BuyPoolDisplayPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pool_display_page);

        Toolbar toolbar = findViewById(R.id.profile_toolbar);
//        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("BuyPool");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

//        Change color of the overflow 'The three dot' on Tool bar
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

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
        } else if (id == R.id.profile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }else if (id == R.id.Notification) {
//            Intent intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Your click about", Toast.LENGTH_SHORT).show();

        }
        return true;

    }

}
