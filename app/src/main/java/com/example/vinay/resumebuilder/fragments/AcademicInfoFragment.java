package com.example.vinay.resumebuilder.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.AcademicInfo;


public class AcademicInfoFragment extends Fragment {

    Context context;
    DatabaseHandler dbHandler;
    EditText pgUniversity, pgName, pgYear, pgPercentage, gUniversity, gName, gYear, gPercentage, sBoard, sName, sYear, sPercentage, cBoard, cName, cYear, cPercentage;
    Button aiSave, aiUpdate;
    CheckBox pgPersuing, pgNotYet, gPersuing;
    TextView pgTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_academic_info, container, false);


        context = getContext();
        dbHandler = new DatabaseHandler(context);
        int cid = NavigationActivity.cid;
        final AcademicInfo academicInfo = dbHandler.getSingleAI(cid);

        pgUniversity = (EditText) rootView.findViewById(R.id.pgBoardET);
        pgName = (EditText) rootView.findViewById(R.id.pgNameET);
        pgYear = (EditText) rootView.findViewById(R.id.pgYearET);
        pgPersuing = (CheckBox) rootView.findViewById(R.id.pgPersuing);
        pgPercentage = (EditText) rootView.findViewById(R.id.pgPercentageET);
        gUniversity = (EditText) rootView.findViewById(R.id.gradBoardET);
        gName = (EditText) rootView.findViewById(R.id.gradNameET);
        gYear = (EditText) rootView.findViewById(R.id.gradYearET);
        gPersuing = (CheckBox) rootView.findViewById(R.id.gradPersuing);
        gPercentage = (EditText) rootView.findViewById(R.id.gradPercentageET);
        sBoard = (EditText) rootView.findViewById(R.id.schoolBoardET);
        sName = (EditText) rootView.findViewById(R.id.schoolNameET);
        sYear = (EditText) rootView.findViewById(R.id.schoolYearET);
        sPercentage = (EditText) rootView.findViewById(R.id.percentageSclET);
        cBoard = (EditText) rootView.findViewById(R.id.collegeBoardET);
        cName = (EditText) rootView.findViewById(R.id.collgeNameET);
        cYear = (EditText) rootView.findViewById(R.id.collegeYearET);
        cPercentage = (EditText) rootView.findViewById(R.id.percentageColET);
        pgTV = (TextView) rootView.findViewById(R.id.pgTV);
        pgNotYet = (CheckBox) rootView.findViewById(R.id.pgNotYet);

        aiSave = (Button) rootView.findViewById(R.id.aiSaveBtn);
        aiUpdate = (Button) rootView.findViewById(R.id.aiUpdateBtn);

        pgPersuing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    pgYear.setText("");
                    pgYear.setEnabled(false);
                } else if (!((CheckBox) v).isChecked()) {
                    pgYear.setEnabled(true);

                }
            }
        });

        gPersuing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {

                    gYear.setText("");
                    gYear.setEnabled(false);

                } else if (!((CheckBox) v).isChecked()) {
                    gYear.setEnabled(true);

                }
            }
        });

        pgNotYet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((CheckBox) v).isChecked()) {


                    pgUniversity.setText("");
                    pgUniversity.setEnabled(false);
                    pgName.setText("");
                    pgName.setEnabled(false);
                    pgYear.setText("");
                    pgYear.setEnabled(false);
                    pgPersuing.setChecked(false);
                    pgPersuing.setEnabled(false);
                    pgPercentage.setText("");
                    pgPercentage.setEnabled(false);

                    pgNotYet.setChecked(true);
                    pgUniversity.setVisibility(View.GONE);
                    pgName.setVisibility(View.GONE);
                    pgYear.setVisibility(View.GONE);
                    pgPersuing.setVisibility(View.GONE);
                    pgPercentage.setVisibility(View.GONE);


                } else if (!((CheckBox) v).isChecked()) {

                    pgNotYet.setChecked(false);
                    pgUniversity.setVisibility(View.VISIBLE);
                    pgName.setVisibility(View.VISIBLE);
                    pgYear.setVisibility(View.VISIBLE);
                    pgPersuing.setVisibility(View.VISIBLE);
                    pgPercentage.setVisibility(View.VISIBLE);

                    pgUniversity.setEnabled(true);
                    pgName.setEnabled(true);
                    pgYear.setEnabled(true);
                    pgPersuing.setEnabled(true);
                    pgPercentage.setEnabled(true);

                }
            }
        });


        if (academicInfo != null) {

            if (academicInfo.getPgnotyet() != null) {
                pgNotYet.setChecked(true);
                pgUniversity.setVisibility(View.GONE);
                pgName.setVisibility(View.GONE);
                pgYear.setVisibility(View.GONE);
                pgPersuing.setVisibility(View.GONE);
                pgPercentage.setVisibility(View.GONE);

//                if (academicInfo.getPguniversity() != null) {
//                    pgUniversity.setText(academicInfo.getPguniversity());
//                    pgName.setText(academicInfo.getPgname());
//                    pgYear.setText(academicInfo.getPgyear());
//                    if (academicInfo.getPgpersuing() != null) {
//                        pgPersuing.setChecked(true);
//                        pgYear.setText("");
//                        pgYear.setEnabled(false);
//                    } else {
//                        pgPersuing.setChecked(false);
//                    }
//                    pgPercentage.setText(academicInfo.getPgpercentage());
//                }


                gUniversity.setText(academicInfo.getGuniversity());
                gName.setText(academicInfo.getGname());
                gYear.setText(academicInfo.getGyear());
                if (academicInfo.getGpersuing() != null) {
                    gPersuing.setChecked(true);
                    gYear.setText("");
                    gYear.setEnabled(false);
                } else {
                    gPersuing.setChecked(false);
                }
                gPercentage.setText(academicInfo.getGpercentage());

                cBoard.setText(academicInfo.getCboard());
                cName.setText(academicInfo.getCname());
                cYear.setText(academicInfo.getCyear());
                cPercentage.setText(academicInfo.getCpercentage());

                sBoard.setText(academicInfo.getSboard());
                sName.setText(academicInfo.getSname());
                sYear.setText(academicInfo.getSyear());
                sPercentage.setText(academicInfo.getSpercentage());

                aiSave.setVisibility(View.GONE);
                aiUpdate.setVisibility(View.VISIBLE);
                aiUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AcademicInfo academicInfo = modelAI();
                        long n = dbHandler.updateAcademicInfo(academicInfo);
                        if (n > 0) {
                            Toast.makeText(context, "AI updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Problem in updating", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            } else {

                if (academicInfo.getPgnotyet() == null) {
                    pgUniversity.setVisibility(View.VISIBLE);
                    pgName.setVisibility(View.VISIBLE);
                    pgYear.setVisibility(View.VISIBLE);
                    pgPersuing.setVisibility(View.VISIBLE);
                    pgPercentage.setVisibility(View.VISIBLE);
                }
                if (academicInfo.getPguniversity() != null) {
                    pgUniversity.setText(academicInfo.getPguniversity());
                    pgName.setText(academicInfo.getPgname());
                    pgYear.setText(academicInfo.getPgyear());
                    if (academicInfo.getPgpersuing() != null) {
                        pgPersuing.setChecked(true);
                        pgYear.setText("");
                        pgYear.setEnabled(false);
                    } else {
                        pgPersuing.setChecked(false);
                    }
                    pgPercentage.setText(academicInfo.getPgpercentage());
                }


                gUniversity.setText(academicInfo.getGuniversity());
                gName.setText(academicInfo.getGname());
                gYear.setText(academicInfo.getGyear());
                if (academicInfo.getGpersuing() != null) {
                    gPersuing.setChecked(true);
                    gYear.setText("");
                    gYear.setEnabled(false);
                } else {
                    gPersuing.setChecked(false);
                }
                gPercentage.setText(academicInfo.getGpercentage());

                cBoard.setText(academicInfo.getCboard());
                cName.setText(academicInfo.getCname());
                cYear.setText(academicInfo.getCyear());
                cPercentage.setText(academicInfo.getCpercentage());

                sBoard.setText(academicInfo.getSboard());
                sName.setText(academicInfo.getSname());
                sYear.setText(academicInfo.getSyear());
                sPercentage.setText(academicInfo.getSpercentage());

                aiSave.setVisibility(View.GONE);
                aiUpdate.setVisibility(View.VISIBLE);
                aiUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        AcademicInfo academicInfo = modelAI();
                        long n = dbHandler.updateAcademicInfo(academicInfo);
                        //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                        if (n > 0) {
                            Toast.makeText(context, "AI updated", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Problem in updating", Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        } else {
            aiSave.setVisibility(View.VISIBLE);
            aiUpdate.setVisibility(View.GONE);
            aiSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AcademicInfo academicInfo = modelAI();
                    long n = dbHandler.addAcademicInfo(academicInfo);
                    //Toast.makeText(AddProfilesActivity.this, "count"+n, Toast.LENGTH_SHORT).show();
                    if (n > 0) {
                        Toast.makeText(context, "AI saved", Toast.LENGTH_SHORT).show();
                        aiSave.setVisibility(View.GONE);
                        aiUpdate.setVisibility(View.VISIBLE);

                    } else {
                        Toast.makeText(context, "Problem in adding", Toast.LENGTH_SHORT).show();

                    }

                }
            });
        }

        return rootView;
    }

    public AcademicInfo modelAI() {
        AcademicInfo academicInfo = new AcademicInfo();

        academicInfo.setCid(NavigationActivity.cid);

        academicInfo.setPguniversity(pgUniversity.getText().toString());
        academicInfo.setPgname(pgName.getText().toString());
        academicInfo.setPgyear(pgYear.getText().toString());
        if (pgPersuing.isChecked()) {
            academicInfo.setPgpersuing(pgPersuing.getText().toString());
        }
        academicInfo.setPgpercentage(pgPercentage.getText().toString());
        if (pgNotYet.isChecked()) {
            academicInfo.setPgnotyet(pgNotYet.getText().toString());

        }
        academicInfo.setGuniversity(gUniversity.getText().toString());
        academicInfo.setGname(gName.getText().toString());
        academicInfo.setGyear(gYear.getText().toString());
        if (gPersuing.isChecked()) {
            academicInfo.setGpersuing(gPersuing.getText().toString());
        }
        academicInfo.setGpercentage(gPercentage.getText().toString());

        academicInfo.setSboard(sBoard.getText().toString());
        academicInfo.setSname(sName.getText().toString());
        academicInfo.setSyear(sYear.getText().toString());
        academicInfo.setSpercentage(sPercentage.getText().toString());

        academicInfo.setCboard(cBoard.getText().toString());
        academicInfo.setCname(cName.getText().toString());
        academicInfo.setCyear(cYear.getText().toString());
        academicInfo.setCpercentage(cPercentage.getText().toString());

        return academicInfo;
    }
}