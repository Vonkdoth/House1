package com.rs.house1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.rs.adapter.NavDrawerItem;
import com.rs.adapter.NavDrawerListAdapter;
import com.rs.adapter.PostAcceptedAdapter;
import com.rs.adapter.PostCompletedAdapter;
import com.rs.adapter.PostNewAdapter;
import com.rs.helper.ServiceHandler;
import com.rs.model.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    DrawerLayout mDrawerLayout;
    public ListView mDrawerListView;
    ActionBarDrawerToggle mDrawerToggle;
    CharSequence mDrawerTitle, mTitle;
    TextView tv;


    LinearLayout mainMneuLinearLayout;
    String[] navMenuTiles = {"My Account", "My Orders", "Privacy Policy", "Terms & Condition", "About Us ","Rate & Share","Partner with us"};
    int[] navMenuIcons = {R.drawable.acount, R.drawable.order, R.drawable.privacy, R.drawable.terms, R.drawable.about,R.drawable.starwhie,R.drawable.handshake};
    ArrayList<NavDrawerItem> navDrawerItems;
    NavDrawerListAdapter adapter;
    Toolbar toolbar;
    int posi = 1;


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    //  private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_drawer);

        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setIcon(R.drawable.hamburger);



        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerListView = (ListView) findViewById(R.id.listview_slidermenu);
        tv = new TextView(this);

        toolbar = new Toolbar(this);
        toolbar.setNavigationIcon(R.drawable.hamburger);
       // toolbar.inflateMenu(R.menu.main);
        View header = getLayoutInflater().inflate(R.layout.header_layout, null);
        mDrawerListView.addHeaderView(header);

//        LayoutInflater inflater = getLayoutInflater();
//        RelativeLayout container = (RelativeLayout) findViewById(R.id.RelativeLayout1);
//        inflater.inflate(R.layout.activity_cart, container);

        navDrawerItems = new ArrayList<NavDrawerItem>();




        for (int i = 0; i <= 6; i++) {
            navDrawerItems.add(new NavDrawerItem(navMenuTiles[i], navMenuIcons[i]));
        }
        mDrawerListView.setOnItemClickListener(new SlideMenuClickListener());
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerListView.setAdapter(adapter);


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                getActionBar().hide();
                super.onDrawerOpened(drawerView);

                setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();


            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
//                getActionBar().show();
                getActionBar().hide();
                setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();

            }
        };
//        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_menu);
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
//
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(1);
        }

    }

    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
            // display view for selected nav drawer item

            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActionBar().setTitle("");
        setTitle(" Home");
       // menu.add(0, 0, 0, "Quit").setIcon(R.drawable.add);
//        getActionBar().
        // getActionBar().setIcon(R.drawable.search);
//        Button btn=new Button(MainActivity.this);
//        btn.setBackground(getResources().getDrawable(R.drawable.dotmenu));
//        btn.setWidth(100);
//        btn.setPadding(2, 2, 2, 2);
//        btn.setHeight(50);
//        btn.setScaleX((float) 0.1);
//        btn.setScaleY((float) 0.45);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent=new Intent(Drawer.this,CustomeTextView.class);
////                startActivity(intent);
//            }
//        });
//        tv.setText("");
//        tv.setBackground(getDrawable(R.drawable.search));
//        tv.setTextColor(getResources().getColor(android.R.color.white));
//tv.setGravity(Gravity.CENTER_HORIZONTAL);
//        tv.setPadding(0, 0, 0, 0);
//        tv.setTypeface(null, Typeface.BOLD);
//        tv.setTextSize(16);
 //  menu.add(0, 2, 1,"xyz").setActionView(btn).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        //getMenuInflater().inflate(R.menu.main, menu);
//        getActionBar().setIcon(R.drawable.search);
        MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

      //  return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:

                Intent intent=new Intent(MainActivity.this,AccauntMy.class);
                startActivity(intent);
                // Toast.makeText(getApplicationContext(),"hiii",Toast.LENGTH_SHORT).show();

                break;
            default:break;
        }

       // Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();

        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
//        }
        // Handle action bar actions click

            return super.onOptionsItemSelected(item);


        }            return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        android.app.FragmentManager fragmentManager = getFragmentManager();

        // if nav drawer is opened, hide the action items


        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerListView);
      menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    private void displayView(int position) {
        // update the main content by replacing fragments
        posi=position;
        android.app.Fragment fragment = null;
        switch (position) {
            case 0:
                posi=0;
//                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
//                startActivity(intent);
            //fragment = new HomeFragment0();

                break;
            case 1:
                posi=1;

                fragment = new MyAccountFragment();
                break;
            case 2:

                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mDrawerLayout.closeDrawer(mDrawerListView);
                finish();
                startActivity(intent);
                // fragment = new HomeFragment2();
                break;
            case 3:
                 fragment = new HomeFragment3();
                break;
            case 4:
              fragment = new HomeFragment4();
                break;
            case 5:
              fragment = new HomeFragment5();
                break;
            case 6:
                final String appPackageName = "com.rs.house1";//"com.swarsystems.is";
                // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
                break;
                //fragment = new HomeFragment1();
            case 7:
                final String appPackageName2 = "com.rs.house1user";//"com.swarsystems.is";
                // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName2)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName2)));
                }
                //fragment = new CartActivity();
                break;
            default:
                break;
        }

        if (fragment != null) {

            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();

            // update selected item and title, then close the drawer
            mDrawerListView.setItemChecked(position, true);
            mDrawerListView.setSelection(position);
            if ((position -1)>=0)
                setTitle(navMenuTiles[position - 1]);
            else
                setTitle(navMenuTiles[0]);

            mDrawerLayout.closeDrawer(mDrawerListView);

        } else {
//            fragment = new HomeFragment1();
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {

        mTitle = title;

        // tv.setText(mTitle);
        getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + mTitle + "</font>"));
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);



    }
    @Override
    public void onBackPressed() {
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        }
        if (posi==1){
            super.onBackPressed();
        } else {
            posi=1;
            displayView(1);
//            getActionBar().setTitle("Home");
            getActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>" + "Home" + "</font>"));

            // super.onBackPressed();
        }
    }


//    @Override
//    public void onBackPressed() {
//        //
//        if (posi == 1) {
//
//            Toast.makeText(getApplicationContext(),"Possition 1 clicked",Toast.LENGTH_LONG).show();
////           Intent intent=new Intent(Intent.ACTION_MAIN);
////            intent.putExtra(Intent.)
////            Intent a = new Intent(Intent.ACTION_MAIN);
////            a.addCategory(Intent.CATEGORY_HOME);
////            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////            startActivity(a);
//            super.onBackPressed();
//        }
//        else {
//            Toast.makeText(getApplicationContext(), posi+"clicked", Toast.LENGTH_LONG).show();
//
//            //  super.onBackPressed();
//            Fragment fragment = new CartActivity();
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction()
//                    .replace(R.id.frame_container, fragment).commit();
//
//            // update selected item and title, then close the drawer
//            mDrawerListView.setItemChecked(1, true);
//            mDrawerListView.setSelection(1);
//            setTitle(navMenuTiles[1 - 1]);
//            mDrawerLayout.closeDrawer(mDrawerListView);
//            posi=1;
//        }
//        //  finish();
//    }

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);




        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_my_acc) {
            Intent intent=new Intent(MainActivity.this, MyAccountActivity.class);
            startActivityForResult(intent, 767);
            // Handle the camera action
        }else if (id == R.id.nav_my_orders) {
           *//* Intent intent=new Intent(MainActivity.this, NewOrder.class);
            startActivityForResult(intent, 755);*//*
            // Handle the camera action
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    *//**
         * A placeholder fragment containing a simple view.
         *//*
    public static class PlaceholderFragment extends Fragment {
        *//**
         * The fragment argument representing the section number for this
         * fragment.
         *//*
        private static final String ARG_SECTION_NUMBER = "section_number";
        ArrayList<Post> arrayList;
        public PlaceholderFragment() {

        }
        public void readJson(String Json){
            try {
                JSONArray array = new JSONObject(Json).getJSONArray("data");
                for(int i=0;i<array.length();i++){
                    arrayList=new ArrayList<Post>();
                    Post p=new Post();
                    p.setUserName(array.getJSONObject(i).getString("post_name"));
                    p.setUserName(array.getJSONObject(i).getString("user_name"));
                    p.setUserName(array.getJSONObject(i).getString("category_name"));
                    p.setUserName(array.getJSONObject(i).getString("sub_category"));
                    p.setUserName(array.getJSONObject(i).getString("phone"));
                    p.setUserName(array.getJSONObject(i).getString("address"));
                    p.setUserName(array.getJSONObject(i).getString("added_on"));
                    arrayList.add(p);

                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        *//**
         * Returns a new instance of this fragment for the given section
         * number.
         *//*
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public void callWebService(){
            new AsyncTask<Void, Void, String>(){

                @Override
                protected String doInBackground(Void... params) {
                    String ret="";
                    try {
                        ServiceHandler handler = new ServiceHandler(getActivity());
                        ret=handler.sendPost("http://sabkuchhai.in/home/api/v1/index.php/GetAllPost", "");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return ret;
                }

                @Override
                protected void onPostExecute(String s) {
                    if(s!=null){
                        readJson(s);
                    }
                }
            }.execute();
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView=null;
            rootView = inflater.inflate(R.layout.activity_list, container, false);
            ListView list= (ListView) rootView.findViewById(R.id.list);
            callWebService();
            if(getArguments().getInt(ARG_SECTION_NUMBER)==1) {

                PostNewAdapter adapter=new PostNewAdapter(getActivity().getApplicationContext(), arrayList);
                list.setAdapter(adapter);
            *//*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setTextColor(Color.parseColor("#888888"));
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*//*

            }else if (getArguments().getInt(ARG_SECTION_NUMBER)==2) {
                PostAcceptedAdapter adapter=new PostAcceptedAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            *//*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setTextColor(Color.parseColor("#888888"));
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*//*

            }
            else if (getArguments().getInt(ARG_SECTION_NUMBER)==3) {
                PostCompletedAdapter adapter=new PostCompletedAdapter(getActivity(), arrayList);
                list.setAdapter(adapter);
            *//*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setTextColor(Color.parseColor("#888888"));
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*//*

            }


            return rootView;
        }
    }

    *//**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         *//*
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "New Order";
                case 1:
                    return "Accepted";
                case 2:
                    return "Completed";
            }
            return null;
        }
    }*/
    }
