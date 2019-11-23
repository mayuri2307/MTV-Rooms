package com.aryan.android.oyo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class profile_you extends AppCompatActivity {
    String Location_Provider = LocationManager.NETWORK_PROVIDER;
    LocationManager mLocationManager;
    LocationListener mLocationListener;
    long MIN_TIME = 5000;
    float MIN_DISTANCE = 1000;
    final int REQUEST_CODE=123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_you);
        ImageView edit = (ImageView) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile_you.this, get_user_data.class);
                startActivity(intent);
            }
        });

        Intent i = getIntent();
        String hobby = i.getStringExtra("hobby");
        String age = i.getStringExtra("age");
        String number = i.getStringExtra("number");
        String gender = i.getStringExtra("gender");
        String status = i.getStringExtra("status");
        String name = i.getStringExtra("name");
        TextView hob = (TextView) findViewById(R.id.hobby_tv);
        hob.setText(hobby);
        TextView ag = (TextView) findViewById(R.id.age_tv);
        ag.setText(age);
        TextView num = (TextView) findViewById(R.id.mobilenumber_tv);
        num.setText(number);
        TextView gn = (TextView) findViewById(R.id.gender_tv);
        gn.setText(gender);
        TextView stat = (TextView) findViewById(R.id.marriage_tv);
        stat.setText(status);
        TextView nam = (TextView) findViewById(R.id.name_tv);
        if(name!=null) {
            nam.setText(name.toUpperCase());
            TextView user = (TextView) findViewById(R.id.username);
            String name1="("+name+")";
            user.setText(name1.toLowerCase());
        }
    }

    protected void onResume() {
        super.onResume();
        Log.d("Clima", "onResume() called");
        getWeatherForCurrentLocation();
    }

    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Clima", "locationlistener");
                String lon=String.valueOf(location.getLongitude());
                String lat=String.valueOf(location.getLatitude());
                Log.d("Clima",lat);
                Log.d("Clima",lon);
                Double lat1=location.getLatitude();
                Double lon1=location.getLongitude();
                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(profile_you.this, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lat1, lon1, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    Log.d("City",city);
                    Log.d("City",address);
                    Log.d("City",state);
                    Log.d("City",country);
                    Log.d("City",postalCode);
                    Log.d("City",knownName);
                    TextView location_set=(TextView)findViewById(R.id.location);
                    location_set.setText(city);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {
                Log.d("Clima", "locationlistener disconnected");
            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(REQUEST_CODE==requestCode){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Log.d("Clima","Permission granted");
                getWeatherForCurrentLocation();
            }
            else{
                Log.d("Clima","Permission denied");
            }
        }
    }
}