package com.theolaperrouse.carnetdevoyage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    TextView dateTextView;
    Button newTrav;
    RecyclerView voyageRecyclerView;
    private ArrayList<Voyage> voyages;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Mise en place du RecyclerView
        voyageRecyclerView = findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        voyageRecyclerView.setLayoutManager(llm);

        //Initialise les données
        initializeData();

        //Adapter pour transcrire les données en carteView
        VoyageAdapter adapter = new VoyageAdapter(voyages);
        voyageRecyclerView.setAdapter(adapter);

        //Affecte les éléments du layout au java pour pouvoir les manipuler
        dateTextView = findViewById(R.id.Date);
        newTrav = findViewById(R.id.parcours);


        //Affiche la date
        String dateText = new SimpleDateFormat("EEEE, d MMMM yyyy").format(new Date(System.currentTimeMillis()));
        dateTextView.setText(dateText);

        //Accès à ParamVoyage
        newTrav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newVoy = new Intent().setClass(getApplicationContext(), ParamVoyage.class);
                startActivity(newVoy);
            }
        });
    }

    //Test Data
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void initializeData() {
        voyages = new ArrayList<>();
        String dataPath = getExternalFilesDir(Environment.getDataDirectory().getAbsolutePath()).getAbsolutePath();
        File data = new File(dataPath);
        File[] nomVoy = data.listFiles();
            if (nomVoy != null) {
                for (File f : nomVoy) {
                    Uri photo = Uri.parse(dataPath + "/" + f.getName() + "/couv.jpg");
                    voyages.add(new Voyage(f.getName(), f.getName(), photo));

                }
            }
        }
    }



