package com.example.vinay.resumebuilder.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vinay.resumebuilder.AddProfilesActivity;
import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.CardDetails;
import com.example.vinay.resumebuilder.model.WorkExperience;

import java.util.Calendar;


public  class ExperienceFragment extends Fragment {
                //extends DialogFragment implements DatePickerDialog.DatePickerlistener {
        Context context;
    FloatingActionButton ex_fab;
    DatabaseHandler dbhandler;
//    CheckBox ex_cb;
//    EditText ex_et4,ex_et5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getContext();
        dbhandler = new DatabaseHandler(context);

        View rootView = inflater.inflate(R.layout.fragment_tab_experience, container, false);
        ex_fab = (FloatingActionButton) rootView.findViewById(R.id.ex_fab);
        ex_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddExperienceDialog();
            }
        });
        return rootView;
    }

    public void showAddExperienceDialog() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.ex_customdialog, null);
        dialogBuilder.setView(dialogView);

        final EditText ex_et1 = (EditText) dialogView.findViewById(R.id.ex_et1);
        final EditText ex_et2 = (EditText) dialogView.findViewById(R.id.ex_et2);
        final EditText ex_et3 = (EditText) dialogView.findViewById(R.id.ex_et3);
        final EditText ex_et4 = (EditText) dialogView.findViewById(R.id.ex_et4);
        final EditText ex_et5 = (EditText) dialogView.findViewById(R.id.ex_et5);
        final CheckBox ex_cb = (CheckBox) dialogView.findViewById(R.id.ex_cb);
        
        final String JobTitle = ex_et1.getText().toString();
        final String JobDescription = ex_et2.getText().toString();
        final String CompanyName = ex_et3.getText().toString();
        final String StartDate = ex_et4.getText().toString();
        final String EndDate = ex_et5.getText().toString();


        dialogBuilder.setTitle("Enter Company Details");
//        ex_cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked = true) {
//                    ex_et5.setEnabled(false);
//                } else {
//                    ex_et5.setEnabled(true);
//                }
//            }
//        });

        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(context, "working", Toast.LENGTH_SHORT).show();
                WorkExperience workExperience = new WorkExperience();
                workExperience.setexJobtitle(JobTitle);
                workExperience.setexJobdescription(JobDescription);
                workExperience.setexCompanyname(CompanyName);
                workExperience.setexStartdate(StartDate);
                workExperience.setexEnddate(EndDate);
               long n = dbhandler.addWorkExperience(workExperience);
                if (n > 0) {
                    Toast.makeText(context, "Details are saved Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                }


            }
        });

        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.dismiss();


            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();


    }
    
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        // Use the current date as the default date in the picker
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        // Create a new instance of DatePickerDialog and return it
//        return new DatePickerDialog(getActivity(), this, year, month, day);
//    }
//
//
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        ex_et4.setText(new StringBuilder().append(dayOfMonth).append("/").append(month).append("/").append(year));
//
//    }
}





