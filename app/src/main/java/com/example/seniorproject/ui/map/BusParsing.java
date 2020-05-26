package com.example.seniorproject.ui.map;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BusParsing extends AppCompatActivity {

    private double latitude;
    private double longitude;
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


    void setLatitude(double lat) {
        latitude = lat;
    }
    void setLongitude(double lon) {
        longitude = lon;
    }
    double getLatitude(){
        return latitude;
    }
    double getLongitude(){
        return longitude;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
