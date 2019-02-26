package com.wwsystems.bomcidadao.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.wwsystems.bomcidadao.R;
import com.wwsystems.bomcidadao.model.ItemTelefone;

import java.util.List;

public class TelefoneActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button btnBuscar;
    private Spinner spinnerCidades;
    private String[] cidades = {"Crato", "Juazeiro do Norte", "Fortaleza"
            ,"Caucaia","Icó","Iguatu"
            ,"Maracanaú","Pacatuba","Quixadá","Sobral","Farias Brito"};
    private List<ItemTelefone> listaContatos;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telefone);
        recyclerView =  findViewById(R.id.recyclerContatos);
        btnBuscar =  findViewById(R.id.btnBuscarCidades);
        spinnerCidades =  findViewById(R.id.spnCidades);
        progressBar = findViewById(R.id.progressContatos);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cidades);
        spinnerCidades.setAdapter(adapter);
    }
}
