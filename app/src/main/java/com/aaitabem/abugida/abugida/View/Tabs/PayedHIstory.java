package com.aaitabem.abugida.abugida.View.Tabs;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aaitabem.abugida.abugida.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PayedHIstory extends Fragment {


    public PayedHIstory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payed_history, container, false);
    }

}
