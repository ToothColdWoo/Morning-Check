//2번코드


package com.example.seniorproject.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;


public class MapFragment extends Fragment{

    TMapView tmap;
    View view;
    LocationManager locationManager;
    String networkProvider = LocationManager.NETWORK_PROVIDER;
    String apiKey = BuildConfig.API_KEY;
    LinearLayout linearLayoutTmap;

    //BusStationVar
    ArrayList<String> list;
    ArrayList<String> centerYn = new ArrayList();
    ArrayList<String> districtCd = new ArrayList();
    ArrayList<String> mobileNo = new ArrayList();
    ArrayList<String> regionName = new ArrayList();
    ArrayList<String> stationId = new ArrayList();
    ArrayList<String> stationName = new ArrayList();
    ArrayList<String> x = new ArrayList();
    ArrayList<String> y = new ArrayList();
    ArrayList<String> distance = new ArrayList();
    //
    boolean flag = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map,container,false);


        linearLayoutTmap = (LinearLayout)view.findViewById(R.id.linearLayoutTmap);
        tmap = new TMapView(getActivity());
        tmap.setSKTMapApiKey(apiKey);
        tmap.setHttpsMode(true);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        linearLayoutTmap.addView(tmap);

        tmap.setOnApiKeyListener(new TMapView.OnApiKeyListenerCallback() {
            @Override
            public void SKTMapApikeySucceed() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        flag = !flag;
                        setupMap();
                    }

                });
            }

            @Override
            public void SKTMapApikeyFailed(String s) {
            }
        });

        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setMyBusStation(tmap.getLongitude(), tmap.getLatitude());
            }
        }, 1000);


        tmap.setOnEnableScrollWithZoomLevelListener(new TMapView.OnEnableScrollWithZoomLevelCallback() {
            @Override
            public void onEnableScrollWithZoomLevelEvent(float v, TMapPoint tMapPoint) {

                tmap.setIconVisibility(false);
                deleteMarker();
            }
        });

        tmap.setOnDisableScrollWithZoomLevelListener(new TMapView.OnDisableScrollWithZoomLevelCallback() {
            @Override
            public void onDisableScrollWithZoomLevelEvent(float v, TMapPoint tMapPoint) {
                Log.d("tttt", "Screen Scrolled");
                Log.d("tttt", tmap.getLatitude() + " : lat ");
                Log.d("tttt", tmap.getLongitude() + " : long");
                tmap.setIconVisibility(true);
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

    public void setMyBusStation(double longitude, double latitude) {
        GetXmlTask getXmlTask = new GetXmlTask();

        try{
            list = getXmlTask.execute(longitude, latitude).get();
            int elementCount = 9;
            int busStationListCount = list.size()/elementCount;

            for(int i =0; i <list.size(); i++){
                centerYn.add(list.get(i++));
                districtCd.add(list.get(i++));
                mobileNo.add(list.get(i++));
                regionName.add(list.get(i++));
                stationId.add(list.get(i++));
                stationName.add(list.get(i++));
                x.add(list.get(i++));
                y.add(list.get(i++));
                distance.add(list.get(i));
            }

            for(int i =0; i <list.size(); i++){
                Log.d("tttt", centerYn.get(i) + " : centerYn " + i + "\n");
                Log.d("tttt", districtCd.get(i) + " : districtCd " + i + "\n");
                Log.d("tttt", mobileNo.get(i) + " : mobileNo " + i + "\n");
                Log.d("tttt", regionName.get(i) + " : regionName " + i + "\n");
                Log.d("tttt", stationId.get(i) + " : stationId " + i + "\n");
                Log.d("tttt", stationName.get(i) + " : stationName " + i + "\n");
                Log.d("tttt", x.get(i) + " : x " + i + "\n");
                Log.d("tttt", y.get(i) + " : y " + i + "\n");
                Log.d("tttt", distance.get(i) + " : distance " + i + "\n");
                Log.d("tttt", "\n");

                double lon = Double.parseDouble(x.get(i));
                double lat = Double.parseDouble(y.get(i));
                addMarker(i, lat, lon);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void addMarker(int i, double latitude, double longitude) {
                TMapPoint point = new TMapPoint(latitude,longitude);
                TMapMarkerItem marker = new TMapMarkerItem();
                marker.setTMapPoint(point);
                tmap.addMarkerItem(i+ "", marker);
    }

    private void deleteMarker() {
                tmap.removeAllMarkerItem();
    }

    @Override
    public  void onStart() {
        super.onStart();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = locationManager.getLastKnownLocation(networkProvider);
        if (location != null) {
            Log.d("tttt", "location Manageer");
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

                Log.d("tttt", location.getLatitude() + " : location latitude");
            } else {
                moveMap(location.getLatitude(), location.getLongitude());
                setMyLocation(location.getLatitude(), location.getLongitude());
                cacheLocation = location;
                Log.d("tttt", location.getLatitude() + " : location latitude");
                Log.d("tttt", location.getLongitude() + " : location Longitude");
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
