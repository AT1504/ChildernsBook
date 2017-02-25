package com.isisruby.childrensbook;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlphabetListFragment extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    String[] alphabet = new String[] { "A","B","C"};
    String[] word = new String[]{"Apple", "Boat", "Cat"};


    // newInstance constructor for creating fragment with arguments
    public static AlphabetListFragment newInstance(int page, String title) {
        AlphabetListFragment fragmentFirst = new AlphabetListFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }
    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.menu_fragment, container, false);
        ListView listView = (ListView)view.findViewById(R.id.list);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1, alphabet);
        listView.setAdapter(adapter);
        return view;
    }
}