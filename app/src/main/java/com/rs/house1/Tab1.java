package com.rs.house1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.rs.adapter.CustomAdapterTab1;

import java.util.ArrayList;

/**
 * Created by hp on 5/2/2016.
 */
public class Tab1 extends Fragment {

    ListView lv;
    Context context;

    ArrayList prgmName;
 //   public static int [] prgmImages={R.drawable.images,R.drawable.images1,R.drawable.images2,R.drawable.images3,R.drawable.images4,R.drawable.images5,R.drawable.images6,R.drawable.images7,R.drawable.images8};
    public static String [] prgmNameList={"Shahil","Akash","Ashok","Ashish","Rahul"};
    public static String [] address={"Plumbing", "Laundry", "Dry Cleanig", "Electrician", "Cleaning"};
    public static String [] midammount={"100","200","300","400","500"};


    public Tab1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tablist1, container, false);
        //context=this;

        CustomAdapterTab1 adapter = new CustomAdapterTab1(getActivity(), prgmNameList, address, midammount);
        ListView listView = (ListView) rootView.findViewById(R.id.mobile_list);
//        ListView listView1=(ListView)findViewById(R.id.image);

        listView.setAdapter(adapter);

        return rootView;




    }
}