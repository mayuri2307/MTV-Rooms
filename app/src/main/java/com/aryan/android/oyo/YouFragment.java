package com.aryan.android.oyo;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class YouFragment extends Fragment {


    private TextView mTitleTextView;
    private FirebaseAuth mAuth;
    public static YouFragment newInstance() {
        // Required empty public constructor
        YouFragment fragment = new YouFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("abc","hey");
        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_you, container, false);
        TextView btn=(TextView) view.findViewById(R.id.chat);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //DO you work here
                String email="gupt_mayuri@yahoo.in";
                String password="mayuri99";
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(getActivity(),new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Log.d("abc","not completed");
                            //showErrorDialog(task.getException().getMessage());
                        }
                        else{
                            Log.d("abc","completed");
                            Intent intent=new Intent(getActivity(),MainChatActivity.class);
                            startActivity(intent);
                        }
                    }
                });
            }
        });
        Toolbar toolbar = view.findViewById(R.id.my_you_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);

        if(((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mTitleTextView = view.findViewById(R.id.you_title);
        mTitleTextView.setText(HomeFragment.mwelcomeMessageTxtView.getText());
        return view;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                View view = FirstActivity.bottomNavigationView.findViewById(R.id.home_menu_item);
                view.performClick();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}