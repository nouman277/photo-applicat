package com.example.photoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

public class UloadView extends AppCompatActivity {




    ImageView imageView;
     String strImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uload_view);
        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Fetching Datta....");
        progressDialog.show();

        Gettingdata();

        if (progressDialog.isShowing()) progressDialog.dismiss();



    }

    private void Gettingdata(){
        Intent intent= getIntent();
        imageView =  findViewById(R.id.imageViewETMI);
        strImage= String.valueOf(intent.getStringExtra("URL"));

        Picasso.get()
                .load(strImage)
                .into(imageView);}

    }
