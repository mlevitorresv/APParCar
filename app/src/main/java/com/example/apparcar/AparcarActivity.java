package com.example.apparcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class AparcarActivity extends AppCompatActivity {

    TextView tvInfo, tvLoc;
    Button btAparcar;
    String lat, longi, resp, textoMostrar;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private FusedLocationProviderClient fusedLocationClient;
    public static final String EXTRA_MESSAGE = "com.example.apparcar.MESSAGE";
    private String MENSAJE_FIJO="La localización de tu coche es: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparcar_activity);
        tvInfo = findViewById(R.id.textViewInfoAparcar);
        tvLoc = findViewById(R.id.textViewLoc);
        btAparcar = findViewById(R.id.buttonAparcarCoche);
        btAparcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUbi();
            }
        });
        //instancia del cliente de proveedor de ubicación combinada
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //PREFERENCIAS
        pref = getSharedPreferences("datos_loc", Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    private void saveUbi() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(AparcarActivity.this, "Sin permisos", Toast.LENGTH_SHORT).show();

            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            String lat= String.valueOf(location.getLatitude());
                            String longi= String.valueOf(location.getLongitude());

                            editor.putString("latitud", lat);
                            editor.putString("longitud", longi);
                            editor.commit();
                            textoMostrar=MENSAJE_FIJO + "\nlatitud: " + pref.getString("latitud", "") + "\nlongitud: " + pref.getString("longitud","");
                            tvLoc.setText(textoMostrar);
                            sendMessage();
                        }else{
                            tvLoc.setText(MENSAJE_FIJO + "Ubicación nula, es posible que este desactivada");
                        }
                    }
        });
    }

    public void sendMessage() {
        String message=MENSAJE_FIJO + "\nlatitud: " + pref.getString("latitud", "") + "\nlongitud: " + pref.getString("longitud","");
        Intent i = new Intent();
        Intent chooser = null;
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, message);
        chooser = i.createChooser(i, "Lanzar Maps");
        startActivity(chooser);

//        String message=MENSAJE_FIJO + "\nlatitud: " + pref.getString("latitud", "") + "\nlongitud: " + pref.getString("longitud","");
//        Bundle extras = new Bundle();
//        extras.putString("DIR", message);
//        Intent intent = new Intent(this, BuscarActivity.class);
//        intent.putExtras(extras);
//        startActivity(intent);
    }


}