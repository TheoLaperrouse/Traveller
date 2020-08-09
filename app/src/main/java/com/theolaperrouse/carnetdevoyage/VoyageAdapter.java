package com.theolaperrouse.carnetdevoyage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VoyageAdapter extends RecyclerView.Adapter<VoyageAdapter.VoyageViewHolder> {
    public ArrayList<Voyage> mDatas;
    public VoyageAdapter(ArrayList<Voyage> datas){
        this.mDatas=datas;
    }
    @Override
    public VoyageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item,parent,false);
        VoyageViewHolder viewHolder = new VoyageViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(VoyageViewHolder holder, int position){
        holder.titre.setText(mDatas.get(position).titre);
        holder.place.setText(mDatas.get(position).place);
        holder.photo.setImageURI(mDatas.get(position).photo);

    }

    @Override
    public int getItemCount(){
        return this.mDatas.size();
    }


    public class VoyageViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView titre;
        TextView place;
        ImageView photo;

        public VoyageViewHolder(final View view) {
            super(view);
            cardView = (CardView)view.findViewById(R.id.card_view);
            titre = (TextView)view.findViewById(R.id.titre_text);
            place = (TextView)view.findViewById(R.id.place_text);
            photo = (ImageView)view.findViewById(R.id.voyage_image);
            photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent Map = new Intent().setClass(view.getContext(), Parcours.class);
                    view.getContext().startActivity(Map);

                }
            });
        }

    }
}
