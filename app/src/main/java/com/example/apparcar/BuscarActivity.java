package com.example.apparcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BuscarActivity extends AppCompatActivity {

    Button btBuscarCoche, btMaps;
    TextView tvDirInfo;

    //Recibidor de intent
    Intent intent;
    String ac;
    String mens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        btBuscarCoche = findViewById(R.id.buttonBuscarCoche);
        btBuscarCoche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDir();
            }
        });
        btMaps = findViewById(R.id.buttonAbrirMaps);
        btMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMaps();
            }
        });
        tvDirInfo = findViewById(R.id.textViewInfoBuscar);

        //Recibidor de intent
        Intent intent = getIntent();
        String ac = intent.getAction();
        String mens;
    }

    private void openMaps() {
        //lat --> 42.2170900     long--> -8.9082300
        String lat="42.2170900";
        String longi="-8.9082300";
        Intent i = new Intent();
        Intent chooser = null;
        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse("geo:" + lat + "," + longi));
        chooser = i.createChooser(i, "Lanzar Maps");
        startActivity(chooser);
        Toast.makeText(this, "Abriendo Maps...", Toast.LENGTH_SHORT).show();
    }

    private void getDir(){
        if(ac.equals(Intent.ACTION_SEND)){
            mens = intent.getStringExtra(Intent.EXTRA_TEXT);
            if(mens!=null){
                tvDirInfo.setText(mens);
            }else{
                tvDirInfo.setText("Ubicación vacía");
            }
        }
    }
}