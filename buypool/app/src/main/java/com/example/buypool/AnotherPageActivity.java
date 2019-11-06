package com.example.buypool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AnotherPageActivity extends AppCompatActivity {
    TextView mTitleTv, mDesTv;
    ImageView mImageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_page);

        //in this activity we will use a back button

        ActionBar actionBar = getSupportActionBar();

        mTitleTv = findViewById(R.id.TitleAnotherPage);
        mDesTv = findViewById(R.id.descriptionAnotherPage);
        mImageTv = findViewById(R.id.imageAnotherPage);


        //Part 2 Get activity

        String mTitle = getIntent().getStringExtra("mTitle");
        String mDes = getIntent().getStringExtra("iDesc");

        byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes , 0 , mBytes.length);

//        actionBar.setTitle(mTitle);

        mTitleTv.setText(mTitle);
        mDesTv.setText(mDes);
        mImageTv.setImageBitmap(bitmap);

    }
}
