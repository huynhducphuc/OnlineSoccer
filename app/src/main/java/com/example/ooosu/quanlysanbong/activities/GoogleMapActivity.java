package com.example.ooosu.quanlysanbong.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.example.ooosu.quanlysanbong.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by oOosu on 4/26/2016.
 */
public class GoogleMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(16.073760, 108.149914);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Đại học Bách Khoa - ĐHDN"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        LatLng dhBK = new LatLng(16.073760, 108.149914);
        MarkerOptions option = new MarkerOptions();
        option.position(dhBK);
        option.title("Đại học Bách Khoa - ĐHDN").snippet("This is cool");
        option.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        option.alpha(0.8f);
        option.rotation(90);
        Marker maker = mMap.addMarker(option);
        maker.showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dhBK, 15));
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

    }
}
