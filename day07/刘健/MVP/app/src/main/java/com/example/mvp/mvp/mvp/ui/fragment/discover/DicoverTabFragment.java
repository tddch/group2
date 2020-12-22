package com.example.mvp.mvp.mvp.ui.fragment.discover;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvp.R;

public class DicoverTabFragment extends Fragment {

   private int type;

    public DicoverTabFragment(int type) {
        this.type=type;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dicover_tab, container, false);
    }
}