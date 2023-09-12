package com.example.apparcar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btAparcar, btBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BUTTONAPARCAR
        btAparcar = findViewById(R.id.buttonAparcar);
        btAparcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAparcar();
            }
        });
        //BUTTONBUSCAR
        btBuscar = findViewById(R.id.buttonBuscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuscar();
            }
        });
    }

    //Creación de menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //gestión de eventos del menú
    public boolean onOptionsItemSelected(MenuItem item) {
        //Manejo del item seleccionado
        switch (item.getItemId()) {
            case R.id.ItemAparcar:
                openAparcar();
                return true;
            case R.id.ItemBuscar:
                openBuscar();
                return true;
            case R.id.ItemDatos:
                openDatos();
                return true;
            case R.id.ItemSalir:
                closeAPP();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //open other activities
    private void openAparcar(){
        Intent intent = new Intent(this, AparcarActivity.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    private void openBuscar(){
        Intent intent = new Intent(this, BuscarActivity.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    private void openDatos(){
        Intent intent = new Intent(this, DatosCoche.class);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }


    //close Application
    private void closeAPP(){
        finish();
    }

}