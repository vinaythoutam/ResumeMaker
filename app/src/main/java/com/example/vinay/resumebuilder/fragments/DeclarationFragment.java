package com.example.vinay.resumebuilder.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.Declaration;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class DeclarationFragment extends Fragment {


    EditText dDeclaration, dDate, dPlace;
    Button dcSave, dcUpdate;
    DatabaseHandler dbHandler;
    Context context;
    Calendar myCalendar;
    DatePickerDialog date;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_declaration, container, false);

        dcSave = (Button) rootView.findViewById(R.id.saveDCBtn);
        dcUpdate = (Button) rootView.findViewById(R.id.updateDCBtn);
        dDeclaration = (EditText) rootView.findViewById(R.id.dDeclaration);
        dDate = (EditText) rootView.findViewById(R.id.dDate);
        dPlace = (EditText) rootView.findViewById(R.id.dPlace);

          myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        dDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
//
//        dDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

//        dDate.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                new DatePickerDialog(getContext(), date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//                return true;
//            }
//        });


        int cid = NavigationActivity.cid;
        context = getContext();
        dbHandler = new DatabaseHandler(context);

        Declaration declaration = dbHandler.getSingleDC(cid);
        if (declaration != null) {
            dDeclaration.setText(declaration.getdDeclaration());
            dDate.setText(declaration.getdDate());
            dPlace.setText(declaration.getdPlace());

            dcSave.setVisibility(View.GONE);
            dcUpdate.setVisibility(View.VISIBLE);
            dcUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Declaration declaration1 = new Declaration();
                    declaration1.setdCid(NavigationActivity.cid);
                    declaration1.setdDeclaration(dDeclaration.getText().toString());
                    declaration1.setdDate(dDate.getText().toString());
                    declaration1.setdPlace(dPlace.getText().toString());

                    long n = dbHandler.updateDC(declaration1);
                    //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                    if (n > 0) {
                        Toast.makeText(context, "DC updated", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        } else {
            dcSave.setVisibility(View.VISIBLE);
            dcUpdate.setVisibility(View.GONE);

            dcSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Declaration declaration = new Declaration();
                    declaration.setdCid(NavigationActivity.cid);
                    declaration.setdDeclaration(dDeclaration.getText().toString());
                    declaration.setdDate(dDate.getText().toString());
                    declaration.setdPlace(dPlace.getText().toString());

                    long n = dbHandler.addDeclaration(declaration);
                    //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                    if (n > 0) {
                        Toast.makeText(context, "DC saved", Toast.LENGTH_SHORT).show();
                        dcSave.setVisibility(View.GONE);
                        dcUpdate.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }


        return rootView;
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dDate.setText(sdf.format(myCalendar.getTime()));
    }



}
