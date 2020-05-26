package com.example.seniorproject.ui.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.seniorproject.BuildConfig;
import com.example.seniorproject.R;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;


public class MapFragment extends Fragment{
    private View view;
    private LinearLayout linearLayoutTmap;
    TMapView tmap;
    boolean camera = false;


    double lat;
    double lon;
//
int elementCount;
    int busStationListCount;

    public double latitude;
    public double longitude;
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

    private String apiKey = BuildConfig.API_KEY;


    private MapViewModel mapViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_map,container,false);


        linearLayoutTmap = (LinearLayout)view.findViewById(R.id.linearLayoutTmap);
        tmap = new TMapView(getActivity());
        tmap.setSKTMapApiKey(apiKey);
        linearLayoutTmap.addView(tmap);

        tmap.setIconVisibility(true);
        setGps();


        //


        //
        tmap.setOnEnableScrollWithZoomLevelListener(new TMapView.OnEnableScrollWithZoomLevelCallback() {
            @Override
            public void onEnableScrollWithZoomLevelEvent(float v, TMapPoint tMapPoint) {
                tmap.setIconVisibility(false);
            }
        });

        tmap.setOnDisableScrollWithZoomLevelListener(new TMapView.OnDisableScrollWithZoomLevelCallback() {
            @Override
            public void onDisableScrollWithZoomLevelEvent(float v, TMapPoint tMapPoint) {
                double longitude =tMapPoint.getLongitude();
                double latitude = tMapPoint.getLatitude();
                tmap.setLocationPoint(longitude, latitude);
                tmap.setCenterPoint(longitude, latitude);
                tmap.setIconVisibility(true);
                lat = latitude;
                lon = longitude;

                //
                GetXmlTask getXmlTask = new GetXmlTask();

                try {

                    list = getXmlTask.execute(lon,lat).get();
                    elementCount = 9;
                    busStationListCount = list.size()/elementCount;
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
                    }

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                //
            }
        });

        return view;
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                tmap.setLocationPoint(longitude, latitude);
                tmap.setCenterPoint(longitude, latitude);
                lat = latitude;
                lon = longitude;
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

    };


    public void setGps() {
        final LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자(실내에선 NETWORK_PROVIDER 권장)
                1000, // 통지사이의 최소 시간간격 (miliSecond)
                1, // 통지사이의 최소 변경거리 (m)
                mLocationListener);
    }

}

