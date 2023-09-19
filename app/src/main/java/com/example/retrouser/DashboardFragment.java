package com.example.retrouser;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DashboardFragment extends Fragment {
    TextView nameT,emailT;
    sharedPrefManager sharedPrefManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        nameT=view.findViewById(R.id.nameT);
        emailT=view.findViewById(R.id.emailT);

        sharedPrefManager=new sharedPrefManager(getActivity());

        String username = "Welcome "+sharedPrefManager.getUser().getUsername();
        String email = "Your email is : "+sharedPrefManager.getUser().getEmail();

        nameT.setText(username);
        emailT.setText(email);


        return view;
    }
}