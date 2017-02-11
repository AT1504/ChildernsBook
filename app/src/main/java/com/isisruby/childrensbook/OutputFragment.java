package com.isisruby.childrensbook;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class OutputFragment extends Fragment {
    String texttodisplay;
    TextView output;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.output_fragment, viewGroup, false);
        output = (TextView) view.findViewById(R.id.word);
        display("caca");
        return view;
    }

    public void display(String txt) {
        output.setText(txt);
    }

    public void replaceFragment(Fragment fragment) {
/*        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();*/
    }
}
