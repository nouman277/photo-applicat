package com.example.photoapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class UloadView extends AppCompatActivity {

    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uload_view);

        mImage = findViewById(R.id.imageViewETU);

        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/photo-application-37386.appspot.com/o/images%2F6f249e0c-8093-469c-aacb-aac94d2ce59c?alt=media&token=b0f98aa2-4932-4350-b915-235c2c9c07e5").into(mImage);
    }
}