package com.aryan.android.oyo;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mancj.slideup.SlideUp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SavedHotelFragment extends Fragment {

    private Toolbar toolbar;
    private TextView mSavedHotelTextView2;
    RecyclerView mHotelImagesRecyclerView;
    RecyclerView mHotelsRecyclerView;
    private ImageButton mFavouriteIcon;
    HotelImageAdapter mAdapter;
    Booked_Room mBoooked_Room;
    List<String> mRoomIds;
    List<String> mHotelNames;
    List<String> mHotelLocations;
    List<String> mOriginalRates;
    List<String> mReducedCosts;
    List<String> mDiscounts;
    List<String> mHotelRatings;
    List<String> Descriptions;
    List<String> mRatings;

    TypedArray mHotelImages;
    private TextView mRoomOriginalCost;
    ArrayList<Integer> mFavouriteImages = new ArrayList<Integer>(100);
    FloatingActionButton fab;
    private SlideUp slideUp;
    private View dim;
    private View slideView;
    public static SavedHotelFragment newInstance() {
        // Required empty public constructor
        SavedHotelFragment fragment = new SavedHotelFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saved_hotel, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.my_toolbar);
        mSavedHotelTextView2 = (TextView)view.findViewById(R.id.saved_hotel_fragment_txt_2) ;
        mSavedHotelTextView2.setText(Html.fromHtml(getResources().getString(R.string.saved_hotel_txt_2)));

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        if (((AppCompatActivity)getActivity()).getSupportActionBar() != null){
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(" ");
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        Set<String> setnames1 = new LinkedHashSet<>();
        setnames1 =  this.getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getStringSet("names1", null);
        String show =  this.getActivity().getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getString("show1", null);
        //Log.d("may",setnames.toString());
        //Log.d("may",show);
        if(show!=null && show.equals("true1")&&setnames1!=null&&setnames1.size()>0){
            Log.d("Hidee1","hide1");
            View hide = view.findViewById(R.id.saved_hotel_fragment_txt_1);
            ((ViewGroup) hide.getParent()).removeView(hide);
            View hide1 = view.findViewById(R.id.img_saved_hotel);
            ((ViewGroup) hide1.getParent()).removeView(hide1);
            View hide2 = view.findViewById(R.id.saved_hotel_fragment_txt_2);
            ((ViewGroup) hide2.getParent()).removeView(hide2);
            View hide3 = view.findViewById(R.id.saved_hotel_fragment_txt_3);
            ((ViewGroup) hide3.getParent()).removeView(hide3);
            view = inflater.inflate(R.layout.activity_booked__room, container, false);
            // code from here
            for(int i=0;i<100;i++) {
               mFavouriteImages.add(R.drawable.ic_favorite_white_24dp);
            }
            mRoomOriginalCost = (TextView) view.findViewById(R.id.room_original_cost) ;
            mFavouriteIcon = (ImageButton) view.findViewById(R.id.favourite_symbol1);
            mHotelsRecyclerView = (RecyclerView) view.findViewById(R.id.hotels_recycler_view);
            String mCity="Faridabad";
            List<String> aList = new ArrayList<String>();
            aList.addAll(setnames1);
            int l=aList.size();
            List mFavouriteImages1=mFavouriteImages.subList(0,l+1);
            List mRoomIds1=mRoomIds.subList(0,l+1);
            List mOriginalRates1=mOriginalRates.subList(0,l+1);
            List mReducedCosts1=mReducedCosts.subList(0,l+1);
            List mDiscounts1=mDiscounts.subList(0,l+1);
            List mHotelRatings1=mHotelRatings.subList(0,l+1);
            List Descriptions1=Descriptions.subList(0,l+1);
            List mRatings1=mRatings.subList(0,l+1);
            List mHotelLocations1=mHotelLocations.subList(0,l+1);
            // need to put aList instead of mHotelNames and then make every other array of the same dimension.
            //todo: need to find how to get subset of the items present in arrays.xml or how to get the subset of tyyped array will also work
            //Log.d("len12",mHotelImages.length()+""); // length should be same
            //Log.d("len12",mRoomIds.size()+"");  // length should be same
            //Log.d("len12",mOriginalRates.size()+"");
            //Log.d("len12",mReducedCosts.size()+"");
            //Log.d("len12",mDiscounts.size()+"");
            //Log.d("len12",mHotelRatings.size()+"");
            //Log.d("len12",Descriptions.size()+"");
            //Log.d("len12",mRatings.size()+"");
            //Log.d("len12",mHotelLocations.size()+"");
            mBoooked_Room = new Booked_Room(getActivity(),mHotelImages,mFavouriteImages,mCity,mRoomIds,aList,
                    mOriginalRates, mReducedCosts, mDiscounts, mHotelRatings, Descriptions, mRatings, mHotelLocations);
            mHotelsRecyclerView.setAdapter(mBoooked_Room);
            //mHotelAdapter.setOnClick(onclick);
            mHotelsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            mHotelsRecyclerView.setMotionEventSplittingEnabled(false);
        }
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                /*getFragmentManager().popBackStack();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, HomeFragment.newInstance(),"CURRENT_FRAGMENT");
                transaction.commit();*/
                View view = FirstActivity.bottomNavigationView.findViewById(R.id.home_menu_item);
                view.performClick();
                return true; //Notice you must returning true here

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
