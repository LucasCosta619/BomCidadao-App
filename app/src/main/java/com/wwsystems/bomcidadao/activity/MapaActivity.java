package com.wwsystems.bomcidadao.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.wwsystems.bomcidadao.R;

import java.util.List;
import java.util.Locale;

public class MapaActivity extends AppCompatActivity implements OnMapReadyCallback, SearchView.OnQueryTextListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    ObtainGPS gps;
    private FloatingActionButton fabSalvarDenuncia;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiCliente;
    private String endereco = "";
    private String rua, bairro, cidade, estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapa);
        mapFragment.getMapAsync(this);

        fabSalvarDenuncia = findViewById(R.id.fabSalvarDenuncia);

        fabSalvarDenuncia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapaActivity.this, DenunciaActivity.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarmapa);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void getLocalization() {
        gps = new ObtainGPS(MapaActivity.this);
        final Intent intent = new Intent(this, MenuPrincipalActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (GetLocalization(MapaActivity.this)) {
            //Verifica se o gps está ligago
            if (gps.canGetLocation()) {

                LatLng cidade = new LatLng(gps.getLatitude(), gps.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(cidade));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                mMap.setMyLocationEnabled(true);
                mMap.setTrafficEnabled(true);

                final Marker meuMarcadorMarker = mMap.addMarker(new MarkerOptions().position(cidade).draggable(true));
                meuMarcadorMarker.setTitle("Minha Casa");
                meuMarcadorMarker.setSnippet("Esta é minha residência...");

                final Geocoder geocoder;
                final List<Address> addresses;
                geocoder = new Geocoder(this, Locale.getDefault());


                mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                    @Override
                    public void onMarkerDragStart(Marker marker) {

                    }

                    @Override
                    public void onMarkerDrag(Marker marker) {

                    }

                    @Override
                    public void onMarkerDragEnd(Marker marker) {


                        String latlangMarcado = marker.getPosition().toString();

                        String arrayLatLangMarcado [] = new String[1];
                        arrayLatLangMarcado = latlangMarcado.split(",");

                        Toast.makeText(MapaActivity.this, arrayLatLangMarcado[0] + arrayLatLangMarcado[1], Toast.LENGTH_SHORT).show();

                        //addresses = geocoder.getFromLocation()

                    }
                });



            } else {
                AlertDialog erroLocation = new AlertDialog.Builder(this).create();
                erroLocation.setTitle("Localização não encontrada!");
                erroLocation.setMessage("Sua Localização não foi encontrada!! Tente novamente!");
                erroLocation.show();
                gps.showSettingsAlert();
            }
        }
    }

    public boolean GetLocalization(Context context) {
        int REQUEST_PERMISSION_LOCALIZATION = 221;
        boolean res = true;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                res = false;
                ActivityCompat.requestPermissions((Activity) context, new String[]{
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_LOCALIZATION);

            }
        }
        return res;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);


        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!enabled) {
            finish();
            Toast.makeText(MapaActivity.this, "Para utilizar o mapa, por favor, ative o GPS", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        } else {

            getLocalization();

        }


    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
