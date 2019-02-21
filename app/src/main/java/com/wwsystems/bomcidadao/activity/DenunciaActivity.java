package com.wwsystems.bomcidadao.activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wwsystems.bomcidadao.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DenunciaActivity extends AppCompatActivity {

    private Button btAdicionarMidia;

    private BootstrapButton btSalvarDenunciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        Toolbar toolbar = findViewById(R.id.toolbardenuncia);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btSalvarDenunciar = findViewById(R.id.btSalvarDenunciar);
        btAdicionarMidia = findViewById(R.id.btAdicionarMidia);

        btAdicionarMidia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DenunciaActivity.this);
                View view = getLayoutInflater().inflate(R.layout.dialog_denuncia, null);
                builder.setView(view);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btSalvarDenunciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*new SweetAlertDialog(DenunciaActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Denúcia salva com sucesso!")
                        .setContentText("Obrigado pela sua ajuda")
                        .show();*/

                dialogDenunciaSucesso();
            }
        });

    }

    public void dialogDenunciaSucesso() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("Denúncia salva com sucesso!")
                .setContentText("Obrigado pela sua ajuda")
                .setConfirmText("OK")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog.dismissWithAnimation();
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MenuPrincipalActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}
