package com.example.apparcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DatosCoche extends AppCompatActivity {

    EditText edMarca, edModelo, edColor;
    Button btGuardar, btVolver;
    RadioGroup radioGroup;
//    RadioButton coche, moto, bicicleta, patinete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_coche2);

        //iniciación de los editText, button y radioGroup
        edMarca = findViewById(R.id.editTextMarca);
        edModelo = findViewById(R.id.editTextModelo);
        edColor = findViewById(R.id.editTextColor);
        btGuardar = findViewById(R.id.buttonGuardar);
        btVolver = findViewById(R.id.buttonPricipal);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroupVehi);
//        coche=findViewById(R.id.radioButtonCoche);
//        moto=findViewById(R.id.radioButtonMoto);
//        bicicleta=findViewById(R.id.radioButtonBici);
//        patinete=findViewById(R.id.radioButtonPatinete);

        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        showData();
    }

    private void goBack() {
        Intent intent = new Intent(this, MainActivity.class);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }

    private void save(){
        SharedPreferences pref = getSharedPreferences("datos_coche", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("marca", String.valueOf(edMarca.getText()));
        editor.putString("modelo", String.valueOf(edModelo.getText()));
        editor.putString("color", String.valueOf(edColor.getText()));
        editor.putInt("vehi", radioGroup.indexOfChild(findViewById(radioGroup.getCheckedRadioButtonId())));
        editor.commit();
        Toast.makeText(this, "Datos Guardados", Toast.LENGTH_SHORT).show();
    }

    private void showData(){
        SharedPreferences pref = getSharedPreferences("datos_coche", Context.MODE_PRIVATE);
        String marca = pref.getString("marca", "");
        String modelo = pref.getString("modelo", "");
        String color = pref.getString("color", "");
        int checked = pref.getInt("vehi", -1);
        //Muestro los datos
        edMarca.setText(marca);
        edModelo.setText(modelo);
        edColor.setText(color);
        if(checked>=0){
            ((RadioButton) ((RadioGroup)findViewById(R.id.radioGroupVehi)).getChildAt(checked)).setChecked(true);
        }
    }

}