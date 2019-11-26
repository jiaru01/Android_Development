package com.example.buypool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PorterDuff;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PublicBuyPoolDisplayPageActivity extends AppCompatActivity {
    //    This Class uses on Card Your BuyPoolDsplay activitys or Public gallery activitys ,
//    This class used to control
    //1. Action bar
    //2.Implement returned information from database and fill it into each cards
    //3. go to other activity page i.e profile

    CurrentUserInfo userInfo;
    ArrayList<Model> models = new ArrayList<>();
    LocalDatabase helper;
    SQLiteDatabase db;


    TextView view_temp;
    TextView view_desc;
    TextView view_city;


    ImageView view_weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_pool_display_page);

        helper= new LocalDatabase(getApplicationContext(), "Cards", null, 1);
        db = helper.getWritableDatabase();
        //Tool Bar starts from here
        Toolbar toolbar = findViewById(R.id.buyPool_toolbar_title);
        setSupportActionBar(toolbar);
        TextView textView = toolbar.findViewById(R.id.toolbar_title);
        textView.setText("Public Card Gallery");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        Change color of the overflow 'The three dot' on Tool bar
        toolbar.getOverflowIcon().setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP);
// Tool Bars ends here


        RecyclerView recyclerView = findViewById(R.id.recyclerViewBuyPoolPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//create a recycling view in a linear layout
        PublicCardAdapter myadapter = new PublicCardAdapter( this,getMyList(),userInfo);
        recyclerView.setAdapter(myadapter);

        //Recyckerview end here

//        weather api starts here

        view_city=findViewById(R.id.town);
        view_city.setText("If not show weather ");

         view_temp=findViewById(R.id.temp);
        view_temp.setText("This is due api delay by asking permssion Please restart the app ");
          view_desc=findViewById(R.id.desc);
        view_desc.setText("");

         view_weather=findViewById(R.id.wheather_image);

        ActivityCompat.requestPermissions(PublicBuyPoolDisplayPageActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 123);

        api_key();
        onBackPressed();

    }

    @Override
    public void onBackPressed() {
        //Provent go back to login page
        return;
    }
    //modifiy the return data as and set model
    private ArrayList<Model> getMyList(){


//        Cursor cardlist = db.query("cards", null, null, null, null, null, null);
        userInfo = (CurrentUserInfo) getApplicationContext();
        String sql = "SELECT title,description,address,date,cards.phone_number,username,gender,cardStatus,cards.id FROM cards,usersremote where create_userID = usersremote.id AND cardStatus != 1 AND cardStatus != 2 AND create_userID != ?;";
        Cursor cardlist = db.rawQuery(sql, new String[]{""+userInfo.getID()});
        while (cardlist.moveToNext()){
            String title = cardlist.getString(0);
            String description = cardlist.getString(1);
            String address = cardlist.getString(2);
            String time = cardlist.getString(3).split(" ")[0];
            String phoneNumber = cardlist.getString(4);
            String username = cardlist.getString(5);
            int gender = cardlist.getInt(6);
            int cardStatus = cardlist.getInt(7);
            int cardID = cardlist.getInt(8);
            Model m = new Model();
            m.setTitle(title);
            m.setDesription(description);
            m.setImg(gender == 0?R.drawable.male:R.drawable.female);
            m.setDate(time);
            m.setAddress(address);
            m.setUserNameOnCard(username);
            m.setPhoneNumber(phoneNumber);
            m.setCardStatus(cardStatus);
            m.setCardID(cardID);
            models.add(m);
        }

        return models;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
//        push menu icons onto the toolbars
        getMenuInflater().inflate(R.menu.buypooldisplaypage, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Logout) {
            //change page should be here
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);}
       else if (id == R.id.toProfile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }


        return true;

    }


    private void api_key( ) {
        Gps gt = new Gps(getApplicationContext());
        Location l = gt.getLocation();
        if( l == null){
            Toast.makeText(getApplicationContext(),"GPS unable to get Value",Toast.LENGTH_SHORT).show();
        }else {
            double lat = l.getLatitude();
            double lon = l.getLongitude();


            OkHttpClient client=new OkHttpClient();
            Request request=new Request.Builder()
                    .url("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid=a6f41d947e0542a26580bcd5c3fb90ef&units=metric")

                    .get()
                    .build();
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Response response= client.newCall(request).execute();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String responseData= response.body().string();
                        try {
                            JSONObject json=new JSONObject(responseData);
                            JSONArray array=json.getJSONArray("weather");
                            JSONObject object=array.getJSONObject(0);

                            String description=object.getString("description");
                            String icons = object.getString("icon");

                            JSONObject temp1= json.getJSONObject("main");
                            Double Temperature=temp1.getDouble("temp");

                            String City=json.getString("name");
                            setText(view_city,City);

                            String temps=Math.round(Temperature)+" °C";
                            setText(view_temp,temps);
                            setText(view_desc,description);
                            setImage(view_weather,icons);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch (IOException e){
                e.printStackTrace();
            }


        }



    }


    private void setText(final TextView text, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(value);
            }
        });
    }
    private void setImage(final ImageView imageView, final String value){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //paste switch
                switch (value){
                    case "01d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "01n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d01d));
                        break;
                    case "02d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "02n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d02d));
                        break;
                    case "03d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "03n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d03d));
                        break;
                    case "04d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "04n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d04d));
                        break;
                    case "09d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "09n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d09d));
                        break;
                    case "10d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "10n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d10d));
                        break;
                    case "11d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "11n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d11d));
                        break;
                    case "13d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    case "13n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.d13d));
                        break;
                    default:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.wheather));

                }
            }
        });
    }

}
