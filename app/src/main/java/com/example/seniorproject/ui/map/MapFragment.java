package com.example.seniorproject.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.seniorproject.BuildConfig;
import com.example.seniorproject.R;
import com.skt.Tmap.TMapView;


public class MapFragment extends Fragment{

    TMapView tmap;
    View view;
    LocationManager locationManager;
    String networkProvider = LocationManager.NETWORK_PROVIDER;
    String apiKey = BuildConfig.API_KEY;
    LinearLayout linearLayoutTmap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);

        linearLayoutTmap = (LinearLayout)view.findViewById(R.id.linearLayoutTmap);
        tmap = new TMapView(getActivity());
        tmap.setSKTMapApiKey(apiKey);
        linearLayoutTmap.addView(tmap);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        tmap.setOnApiKeyListener(new TMapView.OnApiKeyListenerCallback() {
            @Override
            public void SKTMapApikeySucceed() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setupMap();
                    }
                });
            }

            @Override
            public void SKTMapApikeyFailed(String s) {

            }
        });

        return view;
    }

    boolean isInitialized = false;

    private void setupMap() {
        isInitialized = true;
        tmap.setMapType(TMapView.MAPTYPE_STANDARD);
        tmap.setTrackingMode(true);
        if (cacheLocation != null) {
            moveMap(cacheLocation.getLatitude(), cacheLocation.getLongitude());
            setMyLocation(cacheLocation.getLatitude(), cacheLocation.getLongitude());
        }

    }

    @Override
    public  void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(networkProvider);
        if (location != null) {
            mListener.onLocationChanged(location);
        }
        locationManager.requestSingleUpdate(networkProvider, mListener, null);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(mListener);
    }

    Location cacheLocation = null;
    private void moveMap(double lat, double lng) {
        tmap.setCenterPoint(lng, lat);
    }

    private void setMyLocation(double lat, double lng) {
        tmap.setLocationPoint(lng, lat);
        tmap.setIconVisibility(true);
    }

    LocationListener mListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (isInitialized) {
                moveMap(location.getLatitude(), location.getLongitude());
                setMyLocation(location.getLatitude(), location.getLongitude());
            } else {
                cacheLocation = location;
            }
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
    };

}