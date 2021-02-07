package com.nazmul.bottomanddrawernav.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.tabs.TabLayout;
import com.nazmul.bottomanddrawernav.R;
import com.nazmul.bottomanddrawernav.adapter.MusicListAdapter;
import com.nazmul.bottomanddrawernav.databinding.ActivityMainBinding;
import com.nazmul.bottomanddrawernav.databinding.TabItemTopBinding;
import com.nazmul.bottomanddrawernav.fragments.HomeFragment;
import com.nazmul.bottomanddrawernav.fragments.ProfileFragment;
import com.nazmul.bottomanddrawernav.fragments.UserFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        WindowManager windowManager = (WindowManager) getBaseContext().getSystemService(Context.WINDOW_SERVICE);

        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);

        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = point.x;

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        Picasso.get().load(R.drawable.clound_background).transform(transformation).into(activityMainBinding.ivCloud);


        //Use Fragment to viewpager

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> title = new ArrayList<>();

        title.add("Home");
        title.add("User");
        title.add("Profile");

        fragments.add(new HomeFragment());
        fragments.add(new ProfileFragment());
        fragments.add(new UserFragment());

        activityMainBinding.vpContainer.setAdapter(new MusicListAdapter(getSupportFragmentManager(),fragments));
        activityMainBinding.bottomNav.setupWithViewPager(activityMainBinding.vpContainer);


        //initialize top app bar
        for(int i = 0; i < 3; i++){
            TabItemTopBinding topBinding = DataBindingUtil.bind(View.inflate(this,R.layout.tab_item_top,null));
            assert topBinding != null;
            switch (i){
                default:
                case 0:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_home_24));
                    topBinding.text1.setText(title.get(0));
                    break;

                case 1:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_person_24));
                    topBinding.text1.setText(title.get(1));
                    break;

                case 2:
                    topBinding.icon.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.ic_baseline_settings_24));
                    topBinding.text1.setText(title.get(2));
                    break;

            }
            TabLayout.Tab tabAt = activityMainBinding.bottomNav.getTabAt(i);
            if (tabAt != null){
                tabAt.setCustomView(topBinding.getRoot());
            }


        }

    }
}