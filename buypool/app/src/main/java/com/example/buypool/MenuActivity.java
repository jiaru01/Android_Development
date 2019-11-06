package com.example.buypool;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pool_display_page);
    }
}
