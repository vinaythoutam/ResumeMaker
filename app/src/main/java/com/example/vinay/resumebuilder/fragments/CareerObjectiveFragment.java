package com.example.vinay.resumebuilder.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.vinay.resumebuilder.AddProfilesActivity;
import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.CardDetails;
import com.example.vinay.resumebuilder.model.CareerObjective;
import com.example.vinay.resumebuilder.model.PersonalInfo;

import java.util.List;


public class CareerObjectiveFragment extends Fragment {

    ImageView image;
    Button b1;
    DatabaseHandler dbHandler;
    Context context;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context=getContext();
        dbHandler= new DatabaseHandler(context);
        View rootView = inflater.inflate(R.layout.fragment_tab_career_objective, container, false);

        Button dropButton= (Button)rootView.findViewById(R.id.drop_down_co);
         editText=(EditText)rootView.findViewById(R.id.coET);
        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCODialog();
            }
        });

        Button saveCO=(Button)rootView.findViewById(R.id.saveCOBtn);
        saveCO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CareerObjective careerObjective= new CareerObjective();
                careerObjective.setCid(NavigationActivity.cid);
                careerObjective.setCo(editText.getText().toString());

                long n = dbHandler.addCO(careerObjective);
                //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                if (n > 0) {
                    Toast.makeText(context, "CO saved", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return rootView;
    }

    public void showCODialog() {
        final CharSequence[] items = getResources().getStringArray(R.array.careerObjectives);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("Make your selection");
        dialogBuilder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

                // will toast your selection
                //Toast.makeText(context, "Name: " + items[item], Toast.LENGTH_SHORT).show();
                editText.setText(items[item]);
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getListView().setDividerHeight(21);
        alertDialog.show();
    }

}