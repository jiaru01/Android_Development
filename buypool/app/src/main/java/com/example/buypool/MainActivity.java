package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
//    This class is associate with Login activity
    //what it does is that it is able to implenement login function i.e verifys user details with database

    private Button btnLogin , btnSignup, getBtnLogin;
    private EditText email, password;
    private CheckBox remember;
    CurrentUserInfo userInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        getBtnLogin = findViewById(R.id.ButtonLoginInto);
        email =  findViewById(R.id.EnterEmail);
        password = findViewById(R.id.Password);
        remember = findViewById(R.id.checkBox);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });
        getBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUser();
            }
        });
        getUser();

    }
    public void openSignUpActivity() {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void getUser(){
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor users = db.query("userslocal", null, null, null, null, null, null);
            if (users.moveToLast()){
                    int checked = users.getInt(3);
                    if (checked == 1) {
                        this.remember.setChecked(true);
                    }
                String email = users.getString(1);
                String password = users.getString(2);
                this.email.setText(email);
                this.password.setText(password);
            }

    }

    public void storeUser(){
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString().trim();

        Cursor usersremote = db.query("usersremote", null, "email=? and password=?", new String[]{email, password}, null, null, null);
        if (usersremote.moveToNext()){
            userInfo = (CurrentUserInfo) getApplicationContext();
            userInfo.setID(usersremote.getInt(0));
            userInfo.setUserName(usersremote.getString(1));
            userInfo.setPhoneNumber(usersremote.getString(4));
            ContentValues contenValuses = new ContentValues();
            contenValuses.put("email", email);
            if (this.remember.isChecked()){
                contenValuses.put("is_remember", 1);
                contenValuses.put("password", password);

            }else {
                contenValuses.put("is_remember", 0);
                contenValuses.put("password", "");
            }
            int user = db.update("userslocal", contenValuses, "id = 1", null);
        }else {
            Toast.makeText(getApplicationContext(), "Username or Password Wrong!", Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(this, PublicBuyPoolDisplayPageActivity.class);
        startActivity(intent);

    }
}
