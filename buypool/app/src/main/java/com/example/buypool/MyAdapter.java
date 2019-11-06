package com.example.buypool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {
    Context c;
    ArrayList<Model> models;
//     this array list create a list of array which parameter define in our model class

    public MyAdapter(Context c, ArrayList<Model> models) {
        this.c = c;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row, null); //this line inflate our row
        return new MyHolder(view);//this will return our view to holder class
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int position) {
        myHolder.mTitle.setText(models.get(position).getTitle());
        myHolder.mDes.setText(models.get(position).getDesription());

//        This is a way to get image from Resource drawable ,
//        but just leave it ;if we want to return image fro data base the this like should be change
        myHolder.mImaeView.setImageResource(models.get(position).getImg());


        //this is all part two
        myHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClickListener(View v, int position) {
                String gTitle = models.get(position).getTitle();
                String gDesc = models.get(position).getDesription();//get data from previous activity
                BitmapDrawable bitmapDrawable = (BitmapDrawable) myHolder.mImaeView.getDrawable(); // this will get our image from drawble

                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream(); //image will get stream and bytes;

                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);//this will compress our  image

                byte[] bytes = stream.toByteArray();

                //get out data with intent
                Intent intent = new Intent(c, AnotherPageActivity.class);
                intent.putExtra("iTitle", gTitle);
                intent.putExtra("iDesc", gDesc);
                intent.putExtra("iImage", bytes);
                //???
                c.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
