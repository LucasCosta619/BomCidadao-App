package com.wwsystems.bomcidadao.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.wwsystems.bomcidadao.R;

import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class DenunciaActivity extends AppCompatActivity {

    private Button btAdicionarMidia;
    private TextView tvLocalizacaoDenuncia;
    private BootstrapButton btSalvarDenunciar;

    private static final int TAKE_PICTURE = 1;
    private static  final int RESULT_LOAD_IMAGE = 0;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia);

        tvLocalizacaoDenuncia = findViewById(R.id.tvLocalizacaoDenuncia);

        Bundle extra = getIntent().getExtras();

        if(extra!= null){

            String localizacao = extra.getString("localizacao");

            tvLocalizacaoDenuncia.setText(localizacao);

        }

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

                Button btAddFoto = view.findViewById(R.id.btAddFoto);
                Button btAddGaleria = view.findViewById(R.id.btAddGaleria);

                btAddFoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                imageUri);
                        imageUri = Uri.fromFile(photo);
                        startActivityForResult(intent, TAKE_PICTURE);

                    }
                });

                btAddGaleria.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);

                    }
                });

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
