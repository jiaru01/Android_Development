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

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.ProfileToolBarId);
//        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);


        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Profile");
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.share) {

            //change page should be here
            Toast.makeText(getApplicationContext(), "Your click share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "Your click about", Toast.LENGTH_SHORT).show();
        }  else if (id == R.id.Notification) {
            Toast.makeText(getApplicationContext(), "Your click go to notification page", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(this, ProfileActivity.class);
//            startActivity(intent);
        }
        return true;

    }
}
