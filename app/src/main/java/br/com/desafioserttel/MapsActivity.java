package br.com.desafioserttel;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import br.com.desafioserttel.model.Record;
import br.com.desafioserttel.model.Semaforo;
import br.com.desafioserttel.service.SerttelAPI;
import br.com.desafioserttel.service.SerttelService;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    private Float ZOOM = 12f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        pedirPermissoes();
        carregarMarcadores();
    }

    private void pedirPermissoes() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS } , 9999);
        } else {
            mirarUsuario();
        }
    }

    @SuppressLint("MissingPermission")
    private void mirarUsuario(){
        mMap.setMyLocationEnabled(true);

        Criteria criteria = new Criteria();

        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0f, this);

        Location myLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));

        if(myLocation != null){
            CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(myLocation.getLatitude() , myLocation.getLongitude())).zoom(ZOOM).build();
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }


    private void carregarMarcadores() {
        new Thread() {
            @Override
            public void run() {

                try {
                    Response<Record> response = SerttelService.retrofit.create(SerttelAPI.class).getSemaforos().execute();

                    if(response.isSuccessful()){

                        final List<Semaforo> semaforos = response.body().getRecords();

                        if(!semaforos.isEmpty()){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    CameraPosition cameraPosition = CameraPosition.builder().target(new LatLng(semaforos.get(0).getLatitude() , semaforos.get(0).getLongitude())).zoom(ZOOM).build();
                                    mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                                    for(Semaforo semaforo : semaforos){
                                        adicionarMarcador(semaforo);
                                    }

                                }
                            });
                        }

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MapsActivity.this, "Ocorreu um erro ao consultar o serviço da Serttel", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MapsActivity.this, "Ocorreu um erro ao consultar o serviço da Serttel", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        }.start();
    }


    private void adicionarMarcador(Semaforo semaforo){
        LatLng marcador = new LatLng(semaforo.getLatitude(), semaforo.getLongitude());
        mMap.addMarker(new MarkerOptions().position(marcador).title(semaforo.getUtilizacao()).snippet(semaforo.getLocalizacao1() + " " + semaforo.getLocalizacao2()));
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
