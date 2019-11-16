package com.example.buypool;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowCardDetailActivity extends AppCompatActivity {
    TextView mTitleTv, mDesTv,mDateTv, mAddressmDateTv, mUserNameOnCardTv,mPhoneNumberTv;
    ImageView mImageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_card_detail);


//Set Tool Bar starts here

//        Toolbar toolbar = findViewById(R.id.CardDetailToolBarId);
//        setSupportActionBar(toolbar);
//        TextView textView = toolbar.findViewById(R.id.toolbar_title);
//        textView.setText("Card Details");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);

//Sets tool bar ends here


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);



        //in this activity we will use a back button

        ActionBar actionBar = getSupportActionBar();
        mTitleTv = findViewById(R.id.TitleAnotherPage);
        mDesTv = findViewById(R.id.descriptionAnotherPage);
        mImageTv = findViewById(R.id.imageAnotherPage);
        mAddressmDateTv=findViewById(R.id.DetailsAddress);
        mPhoneNumberTv=findViewById(R.id.DetailsPhoneNumber);
        mUserNameOnCardTv=findViewById(R.id.CardPublisherName);


        //Part 2 Get activity
//        iAddress
        String mTitle = getIntent().getStringExtra("iTitle");
        String mDes = getIntent().getStringExtra("iDesc");
        String mPhoneNumber = getIntent().getStringExtra("iPhoneNumber");
        String mAdress = getIntent().getStringExtra("iAddress");
        String mUserNameOnCard = getIntent().getStringExtra("iUserNameOnCard");

        byte[] mBytes = getIntent().getByteArrayExtra("iImage");
        Bitmap bitmap = BitmapFactory.decodeByteArray(mBytes , 0 , mBytes.length);

//        actionBar.setTitle(mTitle);

        mTitleTv.setText(mTitle);
        mDesTv.setText(mDes);
        mPhoneNumberTv.setText(mPhoneNumber);
        mUserNameOnCardTv.setText(mUserNameOnCard);
        mAddressmDateTv.setText(mAdress);
        mImageTv.setImageBitmap(bitmap);

    }
}
