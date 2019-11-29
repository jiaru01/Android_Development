package com.example.buypool;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {
    //This class is used for implementation and connect of local database

    public LocalDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    /*
    * create four tables:
    * userlocal: to remember the login in information if needed
    * usersremote: analog the remote server to store the whole clients information
    * cards: store the order that created by users.
    * orders: create relationship between users. for example: who accept the order  created by others.
    * */
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userslocal(id integer primary key autoincrement,email varchar(40), password varchar(30),is_remember int default 0);");
        db.execSQL("create table usersremote(id integer primary key autoincrement, username varchar(20),email varchar(40), password varchar(30),phone_number varchar(20),gender integer default 0);");
        db.execSQL("create table cards(id integer primary key autoincrement, title varchar(100),description varchar(1000), date timestamp default (datetime('now', 'localtime')),address varchar(1000) not null,phone_number varchar(20) not null,cardStatus integer default 0,create_userID integer not null, FOREIGN KEY (create_userID) REFERENCES usersromote(id));");
        db.execSQL("create table orders(cardID integer not null,order_userID integer not null, FOREIGN KEY (cardID) REFERENCES cards(id),FOREIGN KEY (order_userID) REFERENCES usersremote(id));");

        //initial the remote cards,just add some data to the database
        //total are 12 items.
        String[] email = {"renjie.fu@ucdconnect.ie", "jiaru.he@ucdconnect.ie", "xuewen.tan@ucdconnect.ie", "deyan.chen@ucdconnect.ie","abc@ucdconnect.ie","jone@ucdconnect.ie","phone@ucdconnect.ie","guess@ucdconnect.ie","dream@ucdconnect.ie","happy@ucdconnect.ie","people@ucdconnect.ie","hello@ucdconnect.ie"};
        String[] username = {"renjie", "jiaru", "xuewen", "deyan","abc","jone","phone","guess","dream","happy","people","hello"};
        String[] phonenumber = {"0892401111","0892400000","0892401235","0892405464","0892408764","0892406548","0892405548","0892407777","0892406666","0892408888","0892409999","0892406542"};
        String[] password = {"123456", "123456", "123456", "123456", "123456", "123456", "123456", "123456", "123456", "123456", "123456", "123456"};
        String[] title = {"get some food", "get some meat", "get some vegetable", "i want papers", "need for chocolate", "need apples", "buy some eggs", "need paper", "need drink", "get some pineapple", "apple needed", "apple needed"};
        String[] description = {"I want to get 2 chocolates", "I want to get 2 boxes of chicken", "I need the newest irish newspapers", "need 5 chocolate bars and 2 bottles of milk", "apples for 1000g", "I need 20 free range eggs and 1 boxes of cheese", "I need 20 free range eggs and 1 boxes of cheese","I need 20 free range eggs and 1 boxes of cheese", "get some pineapple for 500g", "apple needed 500g", "apple needed","apple needed"};
//        String[] address = {"15 apartment C, O'connel street, Dublin 1" ,"15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1","15 apartment C, O'connel street, Dublin 1"   };

        int[] is_remember = {1};
        int[] cardStatus = {0,0,0,0,0,1,1,2,2,1,0,0};
        int[] create_userID = {2,2,2,2,3,4,5,1,1,1,2,5};
        int[] cardid = {6,7,8,9,10};
        int[] orderUserID = {2,2,2,2,2};

        for (int i = 0; i < 1; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("email", email[i]);
            contentValues.put("password", password[i]);
            contentValues.put("is_remember", is_remember[i]);
            db.insert("userslocal", null, contentValues);
        }
        for (int i = 0; i < email.length; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("email", email[i]);
            contentValues.put("password", password[i]);
            contentValues.put("username", username[i]);
            contentValues.put("phone_number", phonenumber[i]);
            contentValues.put("gender", i%2==0?0:1);
            db.insert("usersremote", null, contentValues);
        }
        for (int i = 0; i < email.length; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("title", title[i]);
            contentValues.put("description", description[i]);
            contentValues.put("address","15 apartment C, O'connel street, Dublin 1");
            contentValues.put("phone_number", phonenumber[i]);
            contentValues.put("cardStatus", cardStatus[i]);
            contentValues.put("create_userID", create_userID[i]);
            db.insert("cards", null, contentValues);
        }
        for (int i = 0; i < cardid.length; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("cardID", cardid[i]);
            contentValues.put("order_userID", orderUserID[i]);
            db.insert("orders", null, contentValues);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
