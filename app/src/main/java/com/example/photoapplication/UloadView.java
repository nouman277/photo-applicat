package com.example.photoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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

        Intent intent= getIntent();
        imageView =  findViewById(R.id.imageViewETMI);
        strImage= String.valueOf(intent.getStringExtra("URL"));

 Picasso.get()
           .load(strImage)
          .into(imageView);
        if (progressDialog.isShowing()) progressDialog.dismiss();



    }

    }
