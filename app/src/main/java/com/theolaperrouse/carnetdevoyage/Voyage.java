package com.theolaperrouse.carnetdevoyage;

import android.net.Uri;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Voyage {
    String titre;
    String place;
    Uri photo;
    ArrayList<Voyage> mDatas;
    public Voyage(String titre,String place,Uri photo){
        this.titre = titre;
        this.place = place;
        this.photo = photo;
    }


}
