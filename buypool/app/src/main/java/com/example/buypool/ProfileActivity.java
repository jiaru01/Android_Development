package com.example.buypool;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    CurrentUserInfo userInfo;
    TextView userName,cardNumberCollected,CardPulished;
    EditText createFormTitleInput,createFormPhoneNumberInput,createFormAddressInput,createFormDiscriptionTextInput;
    Button formSubmit;
    LocalDatabase helper ;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userInfo = (CurrentUserInfo) getApplicationContext();
        String name = userInfo.getUserName();

        userName = findViewById(R.id.UserNameOnCard);
        cardNumberCollected = findViewById(R.id.cardNumberCollected);
        CardPulished = findViewById(R.id.CardPulished);
        //set profile name
        userName.setText(name);
        helper = new LocalDatabase(getApplicationContext(), "Cards", null, 1);
       db = helper.getWritableDatabase();

        //get collected cards count
        String sql = "SELECT count(*) FROM orders where order_userID = ?;";
        Cursor collected = db.rawQuery(sql, new String[]{""+userInfo.getID()});
        collected.moveToNext();
        int number = collected.getInt(0);
        collected.close();
        cardNumberCollected.setText(number<=1?number+" card":number+" cards");

        //get sent cards count
        String sql1 = "SELECT count(*) FROM cards where create_userID = ?;";
        Cursor sent = db.rawQuery(sql1, new String[]{""+userInfo.getID()});
        sent.moveToNext();
        final int numbers = sent.getInt(0);
        sent.close();
        CardPulished.setText(numbers<=1?numbers+" card":numbers+" cards");

        //get the corresponding view
        createFormAddressInput = findViewById(R.id.createFormAddressInput);
        createFormTitleInput = findViewById(R.id.createFormTitleInput);
        createFormPhoneNumberInput = findViewById(R.id.createFormPhoneNumberInput);
        createFormDiscriptionTextInput = findViewById(R.id.createFormDiscriptionTextInput);
        formSubmit = findViewById(R.id.FormSubmit);
        createFormPhoneNumberInput.setText(userInfo.getPhoneNumber());

        //set submission onclick
        formSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard(numbers);

            }
        });




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
    public void addCard(int numbers ){
        String address = createFormAddressInput.getText().toString();
        String title = createFormTitleInput.getText().toString();
        String phoneNumber = createFormPhoneNumberInput.getText().toString();
        String description = createFormDiscriptionTextInput.getText().toString();
        if (address.equals("") || title.equals("") ||phoneNumber.equals("")|| description.equals("")){
            String text = "";
            int previous = 0;
            if (title.equals("")){
                text+= "title";
                previous = 1;
            }
            if (phoneNumber.equals(""))
                if (previous == 0 ){
                    text+= "phoneNumber";
                    previous = 1;
                }else{
                    text+=" and phoneNumber";
                }
            if (address.equals(""))
                if (previous == 0 ){
                    text+= "address";
                    previous = 1;
                }else{
                    text+=" and address";
                }
            if (description.equals(""))
                if (previous == 0 ){
                    text+= "description";
                    previous = 1;
                }else{
                    text+=" and description";
                }
            text+= " can not be empty";
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
            return;

        }
        ContentValues contentValue = new ContentValues();
        contentValue.put("address", address);
        contentValue.put("title", title);
        contentValue.put("phone_number", phoneNumber);
        contentValue.put("description", description);
        contentValue.put("cardStatus", "0");
        contentValue.put("create_userID", ""+userInfo.getID());
        long cards = db.insert("cards", null, contentValue);
        if (cards > 0){
            CardPulished.setText(numbers+1<=1?(numbers+1)+" card":(numbers+1)+" cards");
            Toast.makeText(getApplicationContext(), "Order is created successfully", Toast.LENGTH_LONG).show();
        }

    }
}
