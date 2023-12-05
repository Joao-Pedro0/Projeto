package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivityPrincipal extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    private boolean running;
    private long startTime;
    public Button btnParar;
    public Button btnInicio;
    public TextView txtTempo;
    private GoogleMap mMap;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_principal);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnParar = findViewById(R.id.btnParar);
        btnInicio = findViewById(R.id.btnInicio);
        txtTempo = findViewById(R.id.textViewTempo);

        startTime = 0;
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running == false) {
                    Iniciar();
                }
            }
        });

        btnParar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (running == true) {
                    Parar();
                }
                txtTempo.setText("00:00:00:000");
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, 100);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, this);

    }

    public void checkPermission(String permission, int requestCode) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivityPrincipal.this,new String[]{permission}, requestCode);
        } else {
            Toast.makeText(this, "Permissão já feita", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng aqui = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(aqui).title("Aqui"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(aqui));

        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(aqui);
        circleOptions.radius(10);
        mMap.addCircle(circleOptions);
    }

    private void Iniciar() {
        running = true;
        startTime = System.currentTimeMillis();
        Atualiza();
    }

    private void Parar() {
        running = false;
    }

    private void Atualiza() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long time = System.currentTimeMillis() - startTime;
                int hours = (int) (time / 1000) / 3600;
                int minutes = (int) ((time / 1000) % 3600) / 60;
                int seconds = (int) (time / 1000) % 60;
                int milliseconds = (int) (time % 1000);

                String timeFormatted = String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
                txtTempo.setText(timeFormatted);

                if (running) {
                    handler.postDelayed(this, 0);
                }
            }
        });

    }
}