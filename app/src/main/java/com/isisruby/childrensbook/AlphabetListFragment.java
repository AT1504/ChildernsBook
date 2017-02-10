package com.isisruby.childrensbook;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AlphabetListFragment extends ListFragment implements FragmentChangeListener {
    String[] alphabet = new String[] { "A","B","C"};
    String[] word = new String[]{"Apple", "Boat", "Cat"};
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup viewGroup, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.menu_fragment, viewGroup, false);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_activated_1, alphabet);
        setListAdapter(adapter);
        return view;
    }
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
            OutputFragment outputFragment = new OutputFragment();
            //Bundle args = new Bundle();
            //args.putInt(word[position], position);
            //outputFragment.setArguments(args);
            replaceFragment(outputFragment, R.id.fragment_container);
    }

    @Override
    public void replaceFragment(Fragment newfragment, int mContainerId) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right);
        fragmentTransaction.replace(mContainerId, newfragment, newfragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}