package com.line.krishna.investmentapp.design;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.line.krishna.investmentapp.R;

/**
 * Created by Admin on 04-06-2015.
 */
public class ContentFragment extends Fragment {
 
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.design_content_fragment,container,false);
        return v;
    }
}