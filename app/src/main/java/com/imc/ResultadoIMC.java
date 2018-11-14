package com.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoIMC extends AppCompatActivity {

    private TextView tvNome, tvImc, tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_imc);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String nome = bundle.getString("nome");
        Double imc = bundle.getDouble("imc");
        String resultado = bundle.getString("resultado");

        tvNome = findViewById(R.id.tv_nome);
        tvImc = findViewById(R.id.tv_imc);
        tvResultado = findViewById(R.id.tv_resultado);

        tvNome.setText(nome);
        tvImc.setText(String.format("%.2f", imc));
        tvResultado.setText(resultado);
    }

    public void historicoResultado(View view) {
        startActivity(new Intent(this, HistoricoActivity.class));
    }

}
