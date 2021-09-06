package com.example.photoapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import java.util.UUID;



public class MainActivity extends AppCompatActivity {





    CardView btnUPLOAD,btnSELECT;
    ImageView viewP;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 22;


    FirebaseStorage storage;
    StorageReference storageReference;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        btnSELECT=findViewById(R.id.selectPhotoET);
        btnUPLOAD=findViewById(R.id.uploadPhotoET);
        viewP=findViewById(R.id.imageViewET);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        btnSELECT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SelectImage();



            }
        });


        btnUPLOAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadImage();






            }
        });





    }


    private void SelectImage () {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult ( int requestCode,
                                      int resultCode,
                                      Intent data)
    {

        super.onActivityResult(requestCode,
                resultCode,
                data);


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {





            filePath = data.getData();
            try {


                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                viewP.setImageBitmap(bitmap);
            } catch (IOException e) {

                e.printStackTrace();

            }


        }

    }

    private void dilogBox(){
        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(MainActivity.this);

        builder.setMessage("YOUR IMAGE IS UPLOADED TO FIRE STORE");

        // Set Alert Title
        builder.setTitle("IMAGE UPLOADED");
        builder.setCancelable(false);



        builder
                .setNegativeButton(
                        "VIEW IMAGE",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {
                                Intent intent = new Intent(MainActivity.this,UloadView.class);
                                startActivity(intent);

                            }
                        });

        AlertDialog alertDialog = builder.create();


        alertDialog.show();






    }

    private void uploadImage() {

        if (filePath==null){
            Toast
                    .makeText(MainActivity.this,
                            "Image Not Selected!!",
                            Toast.LENGTH_SHORT)
                    .show();

        }
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            ProgressDialog progressDialog
                    = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            StorageReference ref
                    = storageReference
                    .child(
                            "images/"
                                    + UUID.randomUUID().toString());


            ref.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()){
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String URL = uri.toString();

                                Intent i = new Intent(MainActivity.this,UloadView.class);
                                i.putExtra("URL", URL);
                                startActivity(i);
                                //This is your image url do whatever you want with it.
                            }
                        });
                    }
                }
            });

            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {


                                    progressDialog.dismiss();
                                    Toast
                                            .makeText(MainActivity.this,
                                                    "Image Uploaded!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                    dilogBox();

                                }

                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            progressDialog.dismiss();
                            Toast
                                    .makeText(MainActivity.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    })
                    .addOnProgressListener(
                            new OnProgressListener<UploadTask.TaskSnapshot>() {


                                @Override
                                public void onProgress(
                                        UploadTask.TaskSnapshot taskSnapshot) {
                                    double progress
                                            = (100.0
                                            * taskSnapshot.getBytesTransferred()
                                            / taskSnapshot.getTotalByteCount()




                                    );


                                    progressDialog.setMessage(
                                            "Uploaded "
                                                    + (int) progress + "%");


                                }
                            });






        }



    }



}








