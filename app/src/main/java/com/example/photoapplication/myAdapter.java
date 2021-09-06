package com.example.photoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


import org.jetbrains.annotations.NotNull;

public class myAdapter extends RecyclerView.Adapter<myAdapter.myViewHolder> {

        private static final String Tag = "RecycleView";
        private Context mContext;
        ArrayList<model> modelArrayList;

        public myAdapter(Context mContext,ArrayList<model> modelArrayList) {
            this.mContext = mContext;
            this.modelArrayList=modelArrayList;
        }

        @NonNull
        @NotNull
        @Override
        public myAdapter.myViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.model_item,parent,false);
            return new myViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull myAdapter.myViewHolder holder, int position) {

            Glide.with(mContext).load(modelArrayList.get(position).getImageUrl()).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return 0;
        }



        public class myViewHolder extends RecyclerView.ViewHolder {

            ImageView   imageView;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.imageViewETMI);

            }
        }

    }





