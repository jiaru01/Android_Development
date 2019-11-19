package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;

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
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "User", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor users = db.query("userslocal", null, null, null, null, null, null);
            if (users.moveToLast()){
                while (true){
                    int checked = users.getInt(3);
                    if (checked == 1) {
                        this.remember.setChecked(true);
                        String email = users.getString(1);
                        String password = users.getString(2);
                        this.email.setText(email);
                        this.password.setText(password);
                        break;
                    }{
                        if (!users.moveToPrevious())
                            break;
                    }
                }
            }

    }

    public void storeUser(){
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "User", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String email = this.email.getText().toString();
        String password = this.password.getText().toString();
        Cursor usersremote = db.query("usersremote", null, "email=? and password=?", new String[]{email, password}, null, null, null);
        if (usersremote.moveToLast()){
            Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            if (this.remember.isChecked()){
                Cursor user = db.query("userslocal", null, "email=?", new String[]{email}, null, null, null);
                if (user.moveToLast()){
                    Cursor isSame = db.query("userslocal", null, "email = ? and password=?", new String[]{email,password}, null, null, null);
                    if(!isSame.moveToLast()){
                        ContentValues content = new ContentValues();
                        content.put("password", password);
                        db.update("userslocal", content, "email = ？",new String[]{email});
                    }
                }else {
                    ContentValues contenValuses = new ContentValues();
                    contenValuses.put("email", email);
                    contenValuses.put("password", password);
                    contenValuses.put("is_remember", 1);
                    db.insert("userslocal", null, contenValuses);
                }

            }
        }else {
            Toast.makeText(getApplicationContext(), "Username or Password Wrong!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(this, PublicBuyPoolDisplayPageActivity.class);
        startActivity(intent);

    }
}
