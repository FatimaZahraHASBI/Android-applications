package hasbi.fatimazahra.map1;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    // l'identifiant de l'appel de l'autorisation
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //Fournisseur d'emplacement fusionné pour récupérer le dernier emplacement connu de l'appareil
        // API de localisation des services Google Play
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void fetchLocation() {
        // Vérifier est ce que l'application est autorisée à accéder à la localisation de l'appareil
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Demande d'autorisation, un pop up s'afficher pour accepter ou refuser la demande d'autorisation // le résultat de cette demande est renvoyé à la méthode onRequestPermissionsResult qui se
            //chargera de la suite
            ActivityCompat.requestPermissions(this, new
                            String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE);
            return;
        }
        // autorisation déjà accordée, on obtient le dernier emplacement
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    // Afficher la lattitude et la longitude avec un Toast
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + ""
                            + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment)
                            getSupportFragmentManager().
                                    findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MapsActivity.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // créer un objet latLng qui stocke la latitude et la longitude de la localisation actuelle
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        // Ajouter un Marker de la localisation actuelle dans la carte
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Je suis là !");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));
        googleMap.addMarker(markerOptions);

        LatLng twincenter = new LatLng(33.586685, -7.632254);
        mMap.addMarker(new MarkerOptions().position(twincenter).title("Twin Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(twincenter));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(twincenter,10));
        mMap.getUiSettings().setZoomControlsEnabled(true);
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(twincenter);
        circleOptions.radius(700);
        circleOptions.fillColor(Color.TRANSPARENT);
        circleOptions.strokeWidth(6);
        mMap.addCircle(circleOptions);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            final GoogleMap gmap=mMap;
            @Override
            public void onMapClick(LatLng latLng) {
                gmap.addMarker(new MarkerOptions().position(latLng));
                Toast.makeText(MapsActivity.this, latLng.latitude+""
                +latLng.longitude, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // Dans le cas lorsque vous cliquez sur le bouton "autoriser" du pop up ,
            // il y aura un deuxième appel de la méthode
            // fetchLocation() pour obtenir la dernière localisation
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {
                    fetchLocation();
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_options, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Change the map type based on the user&#39;s selection.
        switch (item.getItemId()) {
            case R.id.normal_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                return true;
            case R.id.hybrid_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                return true;
            case R.id.satellite_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                return true;
            case R.id.terrain_map:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}