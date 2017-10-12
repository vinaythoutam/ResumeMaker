package com.example.vinay.resumebuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.vinay.resumebuilder.R;

import java.util.Calendar;
import java.util.Date;


public class AcademicInfoFragment extends Fragment {

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_academic_info, container, false);

        context= getContext();
        EditText year =(EditText)rootView.findViewById(R.id.schoolYearET);

        return rootView;
    }
}