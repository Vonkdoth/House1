package com.rs.house1;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by hp on 4/11/2016.
 */
public class HomeFragment2 extends Fragment implements ActionBar.TabListener{

//    private static RecyclerView.Adapter adapter;
//    private RecyclerView.LayoutManager layoutManager;
//    private static RecyclerView recyclerView;
//    private static ArrayList<DataObject2> data;
//    static View.OnClickListener myOnClickListener;
//    private static ArrayList<Integer> removedItems;
//    ImageView search;
//    Context context;
  //  protected OnBackPressedListener onBackPressedListener;

    private ViewPager viewPager;
    private ActionBar actionBar;
    private TabPagerAdapter tabPagerAdapter;
    private String[] tabs = { "Missed Calls", "Dialled", "Received" };
    public HomeFragment2() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.homefragment2, container, false);
      //  FragmentManager fragMan = getSupportFragmentManager();

        viewPager = (ViewPager) rootView.findViewById(R.id.pager);
        actionBar = getActivity().getActionBar();
//        tabPagerAdapter = new TabPagerAdapter(getChildFragmentManager());

        viewPager.setAdapter(tabPagerAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * on swipe select the respective tab
             * */
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) { }

            @Override
            public void onPageScrollStateChanged(int arg0) { }
        });return rootView;
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) { }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

}




       // ((BaseActivity)activity).setOnBackPressedListener(new BaseBackPressedListener(activity));

       // View view = ... ;

//        recyclerView = (RecyclerView)rootView.findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
//
//        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        data = new ArrayList<DataObject2>();
//        for (int i = 0; i < MyData2.nameArray.length; i++) {
//            data.add(new DataObject2(
//                    MyData2.nameArray[i],
//                    MyData2.providerName[i],
//                    MyData2.time[i],
//                    MyData2.date[i],
//                    MyData2.id_[i]
//            ));
//        }
//
//
//       // removedItems = new ArrayList<Integer>();
//
//        adapter = new Fragment2Adapter(getActivity().getApplicationContext(),data);
//        recyclerView.setAdapter(adapter);
//
//        return rootView;
//    }



//    public void onClick() {
//        // what you want to call onBackPressed?
////        Intent intent;
////        intent = new Intent(HomeFragment2.this,CartActivity.class);
////        startActivity(intent);
//    }
//}
//
//    @Override
//    public void onBackPressed() {
//
//        int count = getFragmentManager().getBackStackEntryCount();
//
//        if (count == 0) {
//            super.onBackPressed();
//            //additional code
//        } else {
//            getFragmentManager().popBackStack();
//        }

   // }

//    private static class MyOnClickListener implements View.OnClickListener {
//
//        private final Context context;
//
//        private MyOnClickListener(Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void onClick(View v) {
//            removeItem(v);
//        }
//
//        private void removeItem(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.textView2);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < MyData2.nameArray.length; i++) {
//                if (selectedName.equals(MyData2.nameArray[i])) {
//                    selectedItemId = MyData2.id_[i];
//                }
//            }
//            removedItems.add(selectedItemId);
//            data.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//        if (item.getItemId() == R.id.mynew) {
//            //check if any items to add
//            if (removedItems.size() != 0) {
//               // addRemovedItemToList();
//            } else {
//                //  Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return true;
//    }

//    private void addRemovedItemToList() {
//        int addItemAtListPosition = 3;
//        data.add(addItemAtListPosition, new DataObject2(
//                MyData2.nameArray[removedItems.get(0)],
////                MyData.versionArray[removedItems.get(0)],
////                MyData.time[removedItems.get(0)],
//                MyData2.providerName[removedItems.get(0)],
//                MyData2.time[removedItems.get(0)],
//                MyData2.date[removedItems.get(0)],
//                MyData2.id_[removedItems.get(0)]
//              //  MyData.drawableArray[removedItems.get(0)]
//        ));
//        adapter.notifyItemInserted(addItemAtListPosition);
//        removedItems.remove(0);
    //}



