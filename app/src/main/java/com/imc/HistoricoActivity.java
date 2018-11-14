package com.imc;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.imc.model.HistoricoIMC;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ListView dataListView;
    private List<HistoricoIMC> mHistoricoIMCS = new ArrayList<>();
    private ArrayAdapter<HistoricoIMC> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        dataListView = findViewById(R.id.lv_historico_resultados);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference historicoReference = mDatabase.child("historico");

        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                mHistoricoIMCS);

        dataListView.setAdapter(mAdapter);

        buscarTodos(historicoReference);
    }

    private void buscarTodos(DatabaseReference historicoReference) {
        historicoReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> contactChildren = dataSnapshot.getChildren();
                mHistoricoIMCS.clear();

                for (DataSnapshot historico : contactChildren) {
                    HistoricoIMC historicoIMC = historico.getValue(HistoricoIMC.class);

                    mHistoricoIMCS.add(historicoIMC);
                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
