package com.imc;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.imc.model.HistoricoIMC;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etAltura, etPeso;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome = findViewById(R.id.et_nome);
        etAltura = findViewById(R.id.et_altura);
        etPeso = findViewById(R.id.et_peso);

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    public void calcular(View view) {
        String nome = etNome.getText().toString();
        String altura = etAltura.getText().toString();
        String peso = etPeso.getText().toString();
        String resultadoImc = "";

        if (nome.equals("")) {
            Toast.makeText(this, "Por favor, digite o seu nome", Toast.LENGTH_SHORT).show();
        } else if (altura.equals("")) {
            Toast.makeText(this, "Por favor, digite a sua altura", Toast.LENGTH_SHORT).show();
        } else if (peso.equals("")) {
            Toast.makeText(this, "Por favor, digite a seu peso", Toast.LENGTH_SHORT).show();
        } else {
            Double imc = Double.parseDouble(peso) / (Double.parseDouble(altura) * 2);

            if (imc < 18.5) {
                resultadoImc = "Abaixo do peso ideal, vamos melhorar!";
            } else if ((imc > 18.5) && (imc < 24.9)) {
                resultadoImc = "Você está no peso ideal, parabéns!";
            } else if ((imc > 25.0) && (imc < 29.9)) {
                resultadoImc = "Você está com excesso de peso, vamos melhorar!";
            } else if ((imc > 30.0) && (imc < 34.9)) {
                resultadoImc = "Obesidade Classe I, cuidado!";
            } else if ((imc > 35.0) && (imc < 39.9)) {
                resultadoImc = "Obesidade Classe II, ainda temos como mudar isso!";
            } else if (imc >= 40) {
                resultadoImc = "Obesidade Classe III, procure ajuda, nada está perdido!";
            }

            Intent intent = new Intent(this, ResultadoIMC.class);

            Bundle bundle = new Bundle();

            bundle.putString("nome", nome);
            bundle.putDouble("imc", imc);
            bundle.putString("resultado", resultadoImc);
            intent.putExtras(bundle);

            salvarHistorico(new HistoricoIMC(nome, imc, resultadoImc, altura, peso,  pegarDataAtual()));

            startActivity(intent);
        }

    }

    private String pegarDataAtual() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        return df.format(today);
    }

    private void salvarHistorico(HistoricoIMC historicoIMC) {
        DatabaseReference historico = mDatabase.child("historico");
        historico.push().setValue(historicoIMC);
    }

    public void historicoResultado(View view) {
        startActivity(new Intent(this, HistoricoActivity.class));
    }

}
