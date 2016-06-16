package com.rs.house1;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

public class Main2Activity extends FragmentActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;


    private ViewPager mViewPager;
    private ImageView imageViewBackArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Main2 On Create","");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        imageViewBackArrow=(ImageView)findViewById(R.id.backArrowAccount);
        imageViewBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMainBAck=new Intent(Main2Activity.this,MainActivity.class);
                startActivityForResult(intentMainBAck,678);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentMainBAck=new Intent(Main2Activity.this,MainActivity.class);
        intentMainBAck.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentMainBAck.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      startActivity(intentMainBAck);
finish();
       // super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            switch (position) {
                case 0:
                   // fragment = new Tab1();
                    fragment = new Tab2();

                    return fragment;
                case 1:
            //fragment = new Tab2();
                 fragment = new Tab3();

                    return fragment;
//                case 2:
//           // fragment = new Tab3();
//                    return fragment;

            }

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            android.app.Fragment fragment = null;
            switch (position) {
                case 0:
                    return "Accepted";
                case 1:
                    return "Completed";
//                case 2:
//                    return "Completed";

            }
return "";
    }


        }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main 2 OnResume","");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main2 OnPause","");
    }
}

