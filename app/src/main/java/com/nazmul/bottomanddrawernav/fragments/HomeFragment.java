package com.nazmul.bottomanddrawernav.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.nazmul.bottomanddrawernav.R;
import com.nazmul.bottomanddrawernav.databinding.FragmentHomeBinding;
import com.nazmul.bottomanddrawernav.databinding.TabItemTopBinding;


public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    private Context context;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_home, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        for(int i = 0; i < 3; i++){
            TabItemTopBinding topBinding = DataBindingUtil.bind(View.inflate(context,R.layout.tab_item_top,null));
            assert topBinding != null;
            switch (i){
                default:
                case 0:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_local_florist_24));
                    topBinding.text1.setText("All");
                    break;

                case 1:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_favorite_border_24));
                    topBinding.text1.setText("My");
                    break;

                case 2:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_baseline_night_shelter_24));
                    topBinding.text1.setText("Anxious");
                    break;

            }
            TabLayout.Tab tabAt = binding.topTab.newTab();
            tabAt.setCustomView(topBinding.getRoot());
            binding.topTab.addTab(tabAt);



        }

    }
}