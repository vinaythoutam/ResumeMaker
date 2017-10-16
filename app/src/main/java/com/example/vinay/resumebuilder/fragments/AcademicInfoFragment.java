package com.example.vinay.resumebuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.AcademicInfo;
import com.example.vinay.resumebuilder.model.PersonalInfo;

import java.util.Calendar;
import java.util.Date;


public class AcademicInfoFragment extends Fragment {

    Context context;
    DatabaseHandler dbHandler;
    EditText gName, gYear, gPercentage, sName, sYear, sPercentage, cName, cYear, cPercentage;
    Button aiSave, aiUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_academic_info, container, false);


        context = getContext();
        dbHandler= new DatabaseHandler(context);
        gName = (EditText) rootView.findViewById(R.id.schoolYearET);
        gYear = (EditText) rootView.findViewById(R.id.schoolYearET);
        gPercentage = (EditText) rootView.findViewById(R.id.schoolYearET);
        sName = (EditText) rootView.findViewById(R.id.schoolYearET);
        sYear = (EditText) rootView.findViewById(R.id.schoolYearET);
        sPercentage = (EditText) rootView.findViewById(R.id.schoolYearET);
        cName = (EditText) rootView.findViewById(R.id.schoolYearET);
        cYear = (EditText) rootView.findViewById(R.id.schoolYearET);
        cPercentage = (EditText) rootView.findViewById(R.id.schoolYearET);
        aiSave=(Button) rootView.findViewById(R.id.aiSaveBtn);
        aiUpdate=(Button) rootView.findViewById(R.id.aiUpdateBtn);


        aiSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AcademicInfo academicInfo = modelAI();
                long n = dbHandler.addAcademicInfo(academicInfo);
                //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                if (n > 0)
                {
                    Toast.makeText(context, "AI saved", Toast.LENGTH_SHORT).show();
                    aiSave.setVisibility(View.GONE);
                    aiUpdate.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                }

            }
        });
        aiUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AcademicInfo academicInfo = modelAI();
                //long n = dbHandler.updateAcademicInfo(academicInfo);
                //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                if (1 > 0) {
                    Toast.makeText(context, "AI updated", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Problem in updating", Toast.LENGTH_SHORT).show();

                }

            }
        });

        return rootView;
    }

    public AcademicInfo modelAI()
    {
        AcademicInfo academicInfo= new AcademicInfo();

        academicInfo.setCid(NavigationActivity.cid);
        academicInfo.setGname(gName.getText().toString());
        academicInfo.setGyear(gYear.getText().toString());
        academicInfo.setGpercentage(gPercentage.getText().toString());
        academicInfo.setSname(sName.getText().toString());
        academicInfo.setSyear(sYear.getText().toString());
        academicInfo.setSpercentage(sPercentage.getText().toString());
        academicInfo.setCname(cName.getText().toString());
        academicInfo.setCyear(cYear.getText().toString());
        academicInfo.setCpercentage(cPercentage.getText().toString());

        return academicInfo;
    }
}