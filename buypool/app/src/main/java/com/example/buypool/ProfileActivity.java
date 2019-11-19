package com.example.buypool;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class ProfileActivity extends AppCompatActivity {
    //This class associate with profile acitivity ,
    //what is does
        //1. Set Action bar heading
        //2. go into other activity i.e Card Your collected and Cards you Upload Activity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //tool bar start here
        Toolbar toolbar = findViewById(R.id.ProfileToolBarId);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Profile");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
//too bar ends here

        //to card collection activity
        ImageView toCardCollectionPage = findViewById(R.id.toCardCollectionPage);
        toCardCollectionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCardCollectionActivity();
            }
        });
        //ends here


        //to send card activity
        ImageView toCardSendPage = findViewById(R.id.toSendCard);
        toCardSendPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCardSendActivity();
            }
        });
        //end here
    }

    //function move to a collection card
    public void toCardCollectionActivity() {
        Intent intent = new Intent(this, CardCollectionActivity.class);
        startActivity(intent);
    }

    public void toCardSendActivity() {
        //function move to a send card
        Intent intent = new Intent(this, SendCardActivity.class);
        startActivity(intent);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//       Go into different activity page if a user clicked onto given icons

        int id = item.getItemId();

        if (id == R.id.share) {
            //change page should be here
            Toast.makeText(getApplicationContext(), "Your click share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.about) {
            Toast.makeText(getApplicationContext(), "Your click about", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.Notification) {
            Intent intent = new Intent(this, NoticeActivity.class);
            startActivity(intent);
        }
        return true;

    }
}
