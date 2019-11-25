package com.aryan.android.oyo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HotelActivity_1 extends AppCompatActivity {

    Toolbar toolbar;
    private ImageView mHotelImageView;
    private TextView mHotelId;
    private TextView mHotelName;
    private TextView mHotelLocation;
    private TextView mHotelCity;
    private TextView mHotelRating;
    private TextView mHotelDescription;
    private TextView mRating;
    private TextView mOriginalCost;
    private TextView mReducedCost;

    int mHotelImage;
    RecyclerView mFacilitiesRecyclerView;
    RecyclerView OYORecyclerView;
    NewAdapter mFacilityAdapter;
    HotelAdapter mOYOAdapter;
    String mCity;
    private TextView mPersonName;
    private TextView mPleaseReadTextView;

    TypedArray mHotelImages;
    ArrayList<Integer> mFavouriteImages = new ArrayList<Integer>(100);
    List<String> mRoomIds;
    List<String> mHotelNames;
    List<String> mHotelLocations;
    List<String> mOriginalRates;
    List<String> mReducedCosts;
    List<String> mDiscounts;
    List<String> mHotelRatings;
    List<String> Descriptions;
    List<String> mRatings;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
            overridePendingTransition(R.anim.activit_back_in, R.anim.activity_back_out);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel_1);
        ImageView maps=(ImageView) findViewById(R.id.location_img);
        maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        //Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                //startActivity(intent);
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("google.navigation:q=The Manali Inn+Manali"));
                startActivity(intent);
            }
        });
        Button book=(Button)findViewById(R.id.book_Room);
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create the object of
                // AlertDialog Builder class
                AlertDialog.Builder builder
                        = new AlertDialog
                        .Builder(HotelActivity_1.this);

                // Set the message show for the Alert time
                builder.setMessage("Are you sure that you want to book this room ?");

                // Set Alert Title
                builder.setTitle("Alert !");

                // Set Cancelable false
                // for when the user clicks on the outside
                // the Dialog Box then it will remain show
                builder.setCancelable(false);

                // Set the positive button with yes name
                // OnClickListener method is use of
                // DialogInterface interface.

                builder
                        .setPositiveButton(
                                "Confirm",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        // When the user click Confirm button
                                        // then app will close
                                        //shared preferences for putting the book icon
                                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                                .putString("show","true").apply();
                                        Set<String> setnames = new LinkedHashSet<>();
                                        setnames=getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                                                .getStringSet("names", null);
                                        if(setnames==null) {
                                            setnames = new LinkedHashSet<>();
                                            setnames.add(mHotelName.getText()+"");
                                        }
                                        else
                                        setnames.add(mHotelName.getText()+"");
                                        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                                                .putStringSet("names",setnames).apply();

                                        finish();
                                    }
                                });

                // Set the Negative button with No name
                // OnClickListener method is use
                // of DialogInterface interface.
                builder
                        .setNegativeButton(
                                "Cancel",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which)
                                    {

                                        // If user click Cancel
                                        // then dialog box is canceled.
                                        dialog.cancel();
                                    }
                                });

                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar_transparent);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        int[] mfacilitiesImages = {
                R.drawable.ic_ac_black,
                R.drawable.ic_monitor,
                R.drawable.ic_sofa,
                R.drawable.ic_table,
                R.drawable.ic_free_wifi_black,
                R.drawable.ic_breakfast_black,
                R.drawable.ic_car_parking,
                R.drawable.ic_elevator,
                R.drawable.ic_washing_machine,
                R.drawable.ic_video_camera,
        };

        String[] mFacilitiesNames = { "AC", "TV", "Sofa", "DiningTable", "Free Wifi", "Breakfast",
        "Car Parking", "Elevator", "Washing Machine","CCTV"};

        mHotelImages = getResources().obtainTypedArray(R.array.room_imgs);
        mRoomIds = Arrays.asList(getResources().getStringArray(R.array.room_ids));
        mHotelNames = Arrays.asList(getResources().getStringArray(R.array.hotel_names));
        mHotelLocations = Arrays.asList(getResources().getStringArray(R.array.hotel_locations));
        mOriginalRates = Arrays.asList(getResources().getStringArray(R.array.original_rates));
        mReducedCosts = Arrays.asList(getResources().getStringArray(R.array.reduced_rates));
        mDiscounts = Arrays.asList(getResources().getStringArray(R.array.discounts));
        mHotelRatings = Arrays.asList(getResources().getStringArray(R.array.hotel_ratings));
        Descriptions = Arrays.asList(getResources().getStringArray(R.array.hotel_descriptions));
        mRatings = Arrays.asList(getResources().getStringArray(R.array.ratings));
        /*int[] mHotelImages= {
                R.drawable.room,
                R.drawable.room1,
                R.drawable.room2,
                R.drawable.room3,
                R.drawable.room4,
                R.drawable.room5,
                R.drawable.room6,
                R.drawable.room7,
                R.drawable.room8 };*/

        /*int[] mFavouriteImages= {
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp,
                R.drawable.ic_favorite_white_24dp
        };*/
        for(int i=0;i<100;i++) {
            mFavouriteImages.add(R.drawable.ic_favorite_white_24dp);
        }

        /*int[] mRoomIds = { 2286, 2267, 2289, 2258, 2268, 2264, 2214, 2205, 2290,};*/

        /*String[] mHotelNames = {
                "Aaradhya Residency",
                "Hotel White Inn",
                "Sun Hotel",
                "Hotel Admiral Suites",
                "Hotel Sky Court",
                "Hotel Riviera",
                "Hotel Starlight",
                "Hotel Alankar",
                "Hotel Maple Green Suites"
        };

        String[] mHotelLocations = {
                "Next to Maharaja Signal",
                "M.V Extension",
                "Next to Majestic Railway Station",
                "Near K.B circle",
                "New Osampura",
                "Jalan Road",
                "Nirala Bazar",
                "Mondha Road",
                "Opp. Railway Station"
        };

        int[] mOriginalRates = { 1929, 1626, 1751, 1983, 1606, 1865, 1564, 1593, 1570 };
        int[] mReducedCosts = { 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 727 };
        int[] mDiscounts = { 48, 38, 43, 50, 38, 46, 36, 37, 54 };
        Double[] mHotelRatings = {4.2, 4.4, 3.6, 4.8, 4.3, 4.3, 3.9, 4.1, 3.0 };
        String[] Descriptions = {
                "Very Good",
                "Very Good",
                "Good",
                "Excellent",
                "Very Good",
                "Very Good",
                "Good",
                "Very Good",
                "Average"
        };

        int[] mRatings = { 600, 620, 200, 1160, 550, 404, 320, 589, 101};*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(linearLayoutManager.HORIZONTAL);

        mHotelImageView = (ImageView) findViewById(R.id.hotel_img_1) ;
        mHotelId = (TextView) findViewById(R.id.room_id_1);
        mHotelName = (TextView) findViewById(R.id.hotel_name_1) ;
        mHotelLocation = (TextView) findViewById(R.id.hotel_location_1);
        mHotelCity = (TextView) findViewById(R.id.hotel_city_1) ;
        mHotelRating = (TextView) findViewById(R.id.hotel_rating_1);
        mHotelDescription = (TextView)findViewById(R.id.hotel_description_1);
        mRating = (TextView) findViewById(R.id.rating_count_1) ;
        mOriginalCost = (TextView) findViewById(R.id.room_original_cost_1);
        mReducedCost = (TextView) findViewById(R.id.room_cost_after_discount_1) ;

        OYORecyclerView = (RecyclerView) findViewById(R.id.similar_oyo_recycler_view) ;
        mFacilityAdapter = new NewAdapter(this,mFacilitiesNames,mfacilitiesImages,getResources());
        mFacilitiesRecyclerView = (RecyclerView) findViewById(R.id.room_facilities_recycler_view);
        mFacilitiesRecyclerView.setAdapter(mFacilityAdapter);
        mFacilitiesRecyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        mHotelImage =  intent.getIntExtra(HotelAdapter.EXTRA_IMAGE_ID,0);
        mCity = intent.getStringExtra(HotelAdapter.EXTRA_CITY);

        mHotelImageView.setImageResource(mHotelImage);
        mHotelId.setText(intent.getStringExtra(HotelAdapter.EXTRA_ROOM_ID));
        mHotelName.setText(intent.getStringExtra(HotelAdapter.EXTRA_HOTEL_NAME));
        mHotelLocation.setText(intent.getStringExtra(HotelAdapter.EXTRA_HOTEL_LOCATION));
        mHotelCity.setText(mCity);
        mHotelRating.setText(intent.getStringExtra(HotelAdapter.EXTRA_HOTEL_RATING));
        mHotelDescription.setText(intent.getStringExtra(HotelAdapter.EXTRA_DESCRIPTION));
        mRating.setText(intent.getStringExtra(HotelAdapter.EXTRA_RATING));
        mOriginalCost.setText(intent.getStringExtra(HotelAdapter.EXTRA_ORIGINAL_COST));
        mReducedCost.setText(intent.getStringExtra(HotelAdapter.EXTRA_REDUCED_COST));

        mOYOAdapter = new HotelAdapter(this,mHotelImages,mFavouriteImages,mCity,mRoomIds,mHotelNames,
                mOriginalRates, mReducedCosts, mDiscounts, mHotelRatings, Descriptions, mRatings, mHotelLocations);
        OYORecyclerView.setAdapter(mOYOAdapter);
        OYORecyclerView.setLayoutManager(linearLayoutManager1);

        mPersonName = (TextView) findViewById(R.id.person_name);
        mPersonName.setText( getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("name", "xyz"));

        mPleaseReadTextView = (TextView) findViewById(R.id.please_read_text_view);
        Animation slide = AnimationUtils.loadAnimation(this,R.anim.activit_back_in);
        slide.setStartTime(5000);
        mPleaseReadTextView.startAnimation(slide);
    }
}
