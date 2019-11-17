package com.example.buypool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowCardDetailActivity extends AppCompatActivity {
    TextView mTitleTv, mDesTv,mDateTv, mAddressmDateTv, mUserNameOnCardTv,mPhoneNumberTv, mCallPhoneNumber;
    ImageView mImageTv;
    private static final int REQUEST_CALL = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_detail);


        mCallPhoneNumber = findViewById(R.id.DetailsPhoneNumber);
        Button imageCall = findViewById(R.id.Make_Call);

        imageCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);



        //in this activity we will use a back button

        mTitleTv = findViewById(R.id.TitleAnotherPage);
        mDesTv = findViewById(R.id.descriptionAnotherPage);
        mImageTv = findViewById(R.id.imageAnotherPage);
        mAddressmDateTv=findViewById(R.id.DetailsAddress);
        mPhoneNumberTv=findViewById(R.id.DetailsPhoneNumber);
        mUserNameOnCardTv=findViewById(R.id.CardPublisherName);
        mDateTv=findViewById(R.id.detailsDate);




        //Part Get activity and put into form
//
        String mTitle = getIntent().getStringExtra("iTitle");
        String mDes = getIntent().getStringExtra("iDesc");
        String mPhoneNumber = getIntent().getStringExtra("iPhoneNumber");
        String mAdress = getIntent().getStringExtra("iAddress");
        String mUserNameOnCard = getIntent().getStringExtra("iUserNameOnCard");
        String mDate = getIntent().getStringExtra("iDate");


        byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes , 0 , mBytes.length);

//        actionBar.setTitle(mTitle);

        mTitleTv.setText(mTitle);
        mDesTv.setText(mDes);
        mPhoneNumberTv.setText(mPhoneNumber);
        mUserNameOnCardTv.setText(mUserNameOnCard);
        mDateTv.setText(mDate);

        mAddressmDateTv.setText(mAdress);
        mImageTv.setImageBitmap(bitmap);


    }

    private void makePhoneCall() {
        String number = mCallPhoneNumber.getText().toString();
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(ShowCardDetailActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ShowCardDetailActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(ShowCardDetailActivity.this, "Sorry No Phone Number Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] permissionResults) {
        if (requestCode == REQUEST_CALL) {
            if (permissionResults.length > 0 && permissionResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
