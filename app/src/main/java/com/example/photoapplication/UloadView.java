package com.example.photoapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.os.Bundle;


import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UloadView extends AppCompatActivity {

RecyclerView mRecycleView;
   private FirebaseStorage storage;
  private   StorageReference storageReference;
private ArrayList<model> modelArrayList;
private  myAdapter adapter;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uload_view);


            mRecycleView=findViewById(R.id.recycleViewEditText);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.setHasFixedSize(true);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        modelArrayList =new ArrayList<>();

        ClearALL();

        GetDataFromFirebase();


    }

    private void GetDataFromFirebase() {

        ProgressDialog progressDialog
                = new ProgressDialog(this);
        progressDialog.setTitle("Fetching...");
        progressDialog.show();

        StorageReference ref
                = storageReference
                .child("images/");

        ref.getDownloadUrl().getResult();






    }
    private void ClearALL(){

        if (modelArrayList!=null){

            modelArrayList.clear();
        }else {
            modelArrayList = new ArrayList<>();
        }


        }
    }
