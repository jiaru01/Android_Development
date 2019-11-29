package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    //This class used for signUp acitivity
    //what it does
        //Signup and store the information into database

    private Button btnLogin , btnSignup, getBtnSignup;
    private EditText userName,email,password,phoneNumber,rePassword;
    private Switch gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        getBtnSignup = findViewById(R.id.SignButton);

        userName = findViewById(R.id.signup_name);
        email = findViewById(R.id.EnterEmail);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.EnterEmail);
        phoneNumber = findViewById(R.id.PhoneNumber);
        rePassword = findViewById(R.id.ConfirmPassword);
        gender = findViewById(R.id.MaleFemale);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLoginActivity();
            }
        });
        getBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }
    public void OpenLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signUp(){
        LocalDatabase helper = new LocalDatabase(getApplicationContext(), "User", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();

        String email = this.email.getText().toString();
        String password = this.password.getText().toString().trim();
        String rePassword = this.rePassword.getText().toString().trim();
        String userName = this.userName.getText().toString();
        String phoneNumber = this.phoneNumber.getText().toString();
        String gender = this.gender.isChecked()?"1":"0";
        if (email.equals("") || password.equals("") || rePassword.equals("") || userName.equals("") || phoneNumber.equals("")){
            Toast.makeText(getApplicationContext(), "All information can not be empty!", Toast.LENGTH_LONG).show();
            return;
        }
        if (!password.equals(rePassword)){
            Toast.makeText(getApplicationContext(), "Passwords are different!", Toast.LENGTH_LONG).show();
            return;
        }

        Cursor hasEmail= db.query("usersremote", null, "email =? ", new String[]{email}, null, null, null);
        if (hasEmail.moveToLast()){
            Toast.makeText(getApplicationContext(), "The email has been registered", Toast.LENGTH_LONG).show();
        }else{
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", userName);
            contentValues.put("email", email);
            contentValues.put("password", password);
            contentValues.put("phone_number", phoneNumber);
            contentValues.put("gender", gender);
            db.insert("usersremote", null, contentValues);
            Toast.makeText(getApplicationContext(), "Ok,please login", Toast.LENGTH_LONG).show();
        }

    }


}
