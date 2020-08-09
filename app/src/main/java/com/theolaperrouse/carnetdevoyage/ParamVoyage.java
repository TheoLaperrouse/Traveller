package com.theolaperrouse.carnetdevoyage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ParamVoyage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param_voyage);
        Button butValider = findViewById(R.id.valider);
        Button photo = findViewById(R.id.photo);
        final EditText lieu = findViewById(R.id.lieu);
        final EditText nom = findViewById(R.id.nom);

        butValider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String nomLieu;
                String nomNom;
                nomLieu = String.valueOf(lieu.getText());
                nomNom = String.valueOf(nom.getText());
                Intent newVoy = new Intent().setClass(getApplicationContext(), MainActivity.class);
                newVoy.putExtra("lieu0", nomLieu);
                newVoy.putExtra("nom0", nomNom);
                startActivity(newVoy);
            }
        });
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomLieu;
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                nomLieu = String.valueOf(lieu.getText());
                String repertoireLieu = "/" + nomLieu ;

                if (!(nomLieu.toString().matches(""))) {
                    File repertoire = new File(getExternalFilesDir(Environment.getDataDirectory().toString()) + repertoireLieu);
                    if (!repertoire.exists()) {
                        try {
                            repertoire.mkdir();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    File mFichier = new File(repertoire, "couv.jpg");
                    Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri fileUri = Uri.fromFile(mFichier);
                    mIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                    startActivityForResult(mIntent, 0);
                }
                else{
                    Toast.makeText(ParamVoyage.this, "Veuillez renseigner un nom pour le voyage", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
