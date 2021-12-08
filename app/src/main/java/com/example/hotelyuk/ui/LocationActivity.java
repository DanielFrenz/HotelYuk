package com.example.hotelyuk.ui;

import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.hotelyuk.R;
import com.example.hotelyuk.databinding.ActivityHotelDetailBinding;
import com.example.hotelyuk.databinding.ActivityLocationBinding;
import com.example.hotelyuk.entity.Hotel;
import com.example.hotelyuk.ui.LocationActivity;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
import com.mapbox.mapboxsdk.utils.BitmapUtils;

public class LocationActivity extends AppCompatActivity {
    private static final String ICON_LAYER_ID = "icon-layer-id";
    private static final String ICON_SOURCE_ID = "icon-source-id";
    private static final String RED_PIN_ICON_ID = "red-pin-icon-id";

    private ActivityLocationBinding binding;
    private Point locationPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Mapbox Access Token
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));

        binding = ActivityLocationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Lokasi");

        // Obtaining LatLng data from intent
        Bundle bundle = getIntent().getExtras();
        double latitude = bundle.getDouble("latitude");
        double longtitude = bundle.getDouble("longtitude");
        String alamat = bundle.getString("alamat");

        // Setup Mapbox Map
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
        binding.mapView.onCreate(savedInstanceState);
        binding.mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        // Set the destination location
                        locationPoint = Point.fromLngLat(longtitude, latitude);

                        //Set mapbox camera position to the location
                        CameraPosition position = new CameraPosition.Builder()
                                .target(new LatLng(latitude, longtitude))
                                .zoom(11)
                                .tilt(0)
                                .build();
                        mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(position), 3000);
                        initSource(style);
                        initLayers(style);
                    }
                });
            }
        });

        binding.tvHotelLocation.setText(alamat);
    }

    //  Mapbox Methods
    /**
     * Add the marker sources to the map
     */
    private void initSource(@NonNull Style loadedMapStyle) {
        GeoJsonSource iconGeoJsonSource = new GeoJsonSource(ICON_SOURCE_ID, FeatureCollection.fromFeatures(new Feature[] {
                Feature.fromGeometry(Point.fromLngLat(locationPoint.longitude(), locationPoint.latitude()))}));
        loadedMapStyle.addSource(iconGeoJsonSource);
    }

    /**
     * Add the marker icon layers to the map
     */
    private void initLayers(@NonNull Style loadedMapStyle) {
        // Add the red marker icon image to the map
        loadedMapStyle.addImage(RED_PIN_ICON_ID, BitmapUtils.getBitmapFromDrawable(
                getResources().getDrawable(R.drawable.mapbox_marker_icon_default)));

        // Add the red marker icon SymbolLayer to the map
        loadedMapStyle.addLayer(new SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID).withProperties(
                iconImage(RED_PIN_ICON_ID),
                iconIgnorePlacement(true),
                iconAllowOverlap(true),
                iconOffset(new Float[] {0f, -9f})));
    }

    @Override
    @SuppressWarnings({"MissingPermissions"})
    protected void onStart(){
        super.onStart();
        binding.mapView.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        binding.mapView.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        binding.mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        binding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }
}