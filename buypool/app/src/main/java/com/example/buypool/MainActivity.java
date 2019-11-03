package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private Button btnLogin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSignUpActivity();
            }
        });


//        This should be deleted later , used for testing the tool bar
        Toolbar toolbar = findViewById(R.id.profile_toolbar);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("String");
        getSupportActionBar().setDisplayShowTitleEnabled(false);


//        Change color of the overflow 'The three dot' on Tool bar
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
//        This should be deleted later , used for testing the tool bar

    }

//    here delete for toolbar
    public boolean onCreateOptionsMenu(Menu menu) {
//        push menu icons onto the toolbars
        getMenuInflater().inflate(R.menu.buypooldisplaypage, menu);
        return true;
    }
//    here delete for toolbar


    public void OpenSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}
