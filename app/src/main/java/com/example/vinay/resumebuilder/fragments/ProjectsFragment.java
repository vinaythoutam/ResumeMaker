package com.example.vinay.resumebuilder.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.Ex_ListViewAdapter;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.Project;
import com.example.vinay.resumebuilder.model.WorkExperience;
import com.reginald.editspinner.EditSpinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ProjectsFragment extends Fragment {

    FloatingActionButton proj_fab;
    DatabaseHandler dbhandler;
    Activity context;
    String JobTitle[] = null;
    String JobDescrition[] = null;
    String CompanyName[] = null;
    String Workfrom[] = null;
    String Workto[] = null;
    String stillWorking[] = null;
    int ids[] = null;
    Ex_ListViewAdapter ex_listViewAdapter;
    ListView lv;
    Fragment frg = null;
//    CheckBox ex_cb;
//    EditText ex_et4,ex_et5;


    Calendar myCalendar;
    EditText projName, projClient, projDomain, projDesc, role, respo, teamSize, techUsed;

    private EditText fromDateEtxt;
    private EditText toDateEtxt;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        dbhandler = new DatabaseHandler(getContext());
        View rootView = inflater.inflate(R.layout.fragment_tab_projects, container, false);
        lv = (ListView) rootView.findViewById(R.id.proj_list);

        int id = NavigationActivity.cid;
//        List<WorkExperience> cd = dbhandler.getAllExperienceDetails(id);
//        if (cd.size() > 0) {
//            JobTitle = new String[cd.size()];
//            JobDescrition = new String[cd.size()];
//            CompanyName = new String[cd.size()];
//            ids   = new int[cd.size()];
//            Workfrom = new String[cd.size()];
//            Workto = new String[cd.size()];
//            stillWorking = new String[cd.size()];
//
//            int i = 0;
//            for(WorkExperience we : cd){
//                JobTitle[i] = we.getexJobtitle();
//                JobDescrition[i] = we.getexJobdescription();
//                CompanyName[i] = we.getexCompanyname();
//                Workfrom[i] = we.getexStartdate();
//                Workto[i] = we.getexEnddate();
//                stillWorking[i] = we.getExStlworking();
//                ids[i] = we.getexCid();
//                i++;
//            }
//
//            ex_listViewAdapter = new Ex_ListViewAdapter(getActivity(), JobTitle,CompanyName,Workfrom,Workto,stillWorking,ids);
//            lv.setAdapter(ex_listViewAdapter);
//        }
//        else{
//            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
//        }
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                PopupMenu menu = new PopupMenu (getContext(), view);
//                menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ()
//                {
//                    @Override
//                    public boolean onMenuItemClick (MenuItem item)
//                    {
//                        int id = item.getItemId();
//                        switch (id)
//                        {
//                            case R.id.edit:
//
//                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
//                                LayoutInflater inflater = getActivity().getLayoutInflater();
//                                final View dialogView = inflater.inflate(R.layout.ex_customdialog, null);
//                                dialogBuilder.setView(dialogView);
//
//                                final EditText jTitle = (EditText) dialogView.findViewById(R.id.jobTitleET);
//                                final EditText jDesc = (EditText) dialogView.findViewById(R.id.jobDescET);
//                                final EditText cName = (EditText) dialogView.findViewById(R.id.compNameET);
//                                final EditText wFrom = (EditText) dialogView.findViewById(R.id.wrkFromET);
//                                final EditText wTo = (EditText) dialogView.findViewById(R.id.wrkToET);
//                                final CheckBox stlWrk = (CheckBox) dialogView.findViewById(R.id.stlWrkCB);
//
//                                stlWrk.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        if (((CheckBox) v).isChecked()) {
//                                            wTo.setText("");
//                                            wTo.setEnabled(false);
//                                        }
//                                        else if (!((CheckBox) v).isChecked()) {
//                                            wTo.setEnabled(true);
//                                        }
//                                    }
//                                });
//
//                                final String title = JobTitle[position];
//                                final String desc = JobDescrition[position];
//                                final String comp = CompanyName[position];
//                                final String wF = Workfrom[position];
//                                final String wT = Workto[position];
//                                final String sWork = stillWorking[position];
//                                final int iD= ids[position];
//
//                                jTitle.setText(title);
//                                jDesc.setText(desc);
//                                cName.setText(comp);
//                                wFrom.setText(wF);
//                                wTo.setText(wT);
//                                if(sWork!=null) {
//                                    stlWrk.setChecked(true);
//                                    wTo.setText("");
//                                    wTo.setEnabled(false);
//                                }
//                                else{
//                                    stlWrk.setChecked(false);
//                                }
//
//                                dialogBuilder.setTitle("Enter Company Details");
//                                dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
//
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//
//                                        WorkExperience we = new WorkExperience();
//                                        we.setexCid(iD);
//                                        we.setexJobtitle(jTitle.getText().toString());
//                                        we.setexJobdescription(jDesc.getText().toString());
//                                        we.setexCompanyname(cName.getText().toString());
//                                        we.setexStartdate(wFrom.getText().toString());
//                                        we.setexEnddate(wTo.getText().toString());
//                                        we.setExStlworking(stlWrk.getText().toString());
//                                        long n = dbhandler.updateExperience(we);
//                                        if(n>0){
//                                            Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
//                                            //dbhandler.updateDate(NavigationActivity.cid);
//                                            getFragmentManager().beginTransaction().detach(ProjectsFragment.this).attach(ProjectsFragment.this).commit();
//
//                                        }
//                                        else{
//                                            Toast.makeText(getContext(), "problem in updating", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//                                dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        dialog.dismiss();
//
//                                    }
//                                });
//                                AlertDialog b = dialogBuilder.create();
//                                b.show();
//
//                                break;
//                            case R.id.delete:
//                                AlertDialog.Builder dialogBuilderDel = new AlertDialog.Builder(getContext());
//                                LayoutInflater inflaterDel = getActivity().getLayoutInflater();
//                                final View dialogViewDel = inflaterDel.inflate(R.layout.delete_dialog, null);
//                                dialogBuilderDel.setView(dialogViewDel);
//
//                                dialogBuilderDel.setMessage("Do you want to delete this Profile ?");
//                                dialogBuilderDel.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        //do something with edt.getText().toString();
//                                        int pos = ids[position];
//                                        String title = JobTitle[position];
//                                        boolean b = dbhandler.deleteWorkExperience(title);
//                                        if (b == true) {
//                                            Toast.makeText(getContext(), "deleted " + ids[position] + JobTitle[position] + "pos" + position, Toast.LENGTH_SHORT).show();
//                                            getFragmentManager().beginTransaction().detach(ProjectsFragment.this).attach(ProjectsFragment.this).commit();
//
//                                        } else {
//                                            Toast.makeText(getContext(), "cant delete", Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    }
//                                });
//                                dialogBuilderDel.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int whichButton) {
//                                        //pass
//                                    }
//                                });
//                                AlertDialog bDel = dialogBuilderDel.create();
//                                bDel.show();
//                                break;
//                        }
//                        return true;
//                    }
//                });
//
//                menu.inflate (R.menu.popup_menu);
//                menu.show();
//
//            }
//        });


        proj_fab = (FloatingActionButton) rootView.findViewById(R.id.project_fab);
        proj_fab.setOnClickListener(new View.OnClickListener() {
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
        final View dialogView = inflater.inflate(R.layout.dialog_add_project, null);
        dialogBuilder.setView(dialogView);

        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener dateFrom = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);

                updateFromDate();

            }
        };
        final DatePickerDialog.OnDateSetListener dateTo = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);

                updateToDate();
            }
        };

        //findViewByID
        projName = (EditText) dialogView.findViewById(R.id.edt_project_name);
        projClient = (EditText) dialogView.findViewById(R.id.edt_project_client);
        fromDateEtxt = (EditText) dialogView.findViewById(R.id.dFromET);
        toDateEtxt = (EditText) dialogView.findViewById(R.id.dToET);
        EditSpinner mEditSpinner = (EditSpinner) dialogView.findViewById(R.id.edit_spinner_domain);
        ListAdapter adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.domainSpinner));
        mEditSpinner.setAdapter(adapter);
        projDesc = (EditText) dialogView.findViewById(R.id.edt_project_details);
        role = (EditText) dialogView.findViewById(R.id.dToET);
        respo = (EditText) dialogView.findViewById(R.id.dToET);
        teamSize = (EditText) dialogView.findViewById(R.id.dToET);
        EditSpinner mEditSpinnerTech = (EditSpinner) dialogView.findViewById(R.id.edit_spinner_tech);
        ListAdapter adapterTech = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.domainSpinner));
        mEditSpinnerTech.setAdapter(adapterTech);

        //onClick listeners
        fromDateEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    new DatePickerDialog(getContext(), dateFrom, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });
        toDateEtxt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus == true) {
                    new DatePickerDialog(getContext(), dateTo, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        dialogBuilder.setTitle("Enter Project Details");
        dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                String pjName = projName.getText().toString();
                String pjClient = projClient.getText().toString();
                String pjFrom = fromDateEtxt.getText().toString();
                String pjTo = toDateEtxt.getText().toString();
                String pjDomain = projDomain.getText().toString();
                String pjDesc= projDesc.getText().toString();
                String pjRole = role.getText().toString();
                String pjResp= respo.getText().toString();
                String pjTS = teamSize.getText().toString();
                String pjTech= techUsed.getText().toString();
                int pjCid = NavigationActivity.cid;

                Project project = new Project();
                project.setPjCid(pjCid);
                project.setPjName(pjName);
                project.setPjClient(pjClient);
                project.setPjFrom(pjFrom);
                project.setPjTo(pjTo);
                project.setPjDomain(pjDomain);
                project.setPjDesc(pjDesc);
                project.setPjRole(pjRole);
                project.setPjResp(pjResp);
                project.setPjSize(pjTS);
                project.setPjTech(pjTech);
//                long n = dbhandler.addProject(project);
//                if (n > 0) {
//                    Toast.makeText(getContext(), "Details are saved Successfully", Toast.LENGTH_SHORT).show();
//                    getFragmentManager().beginTransaction().detach(ProjectsFragment.this).attach(ProjectsFragment.this).commit();
//
//                } else {
//                    Toast.makeText(getContext(), "Problem in adding", Toast.LENGTH_SHORT).show();
//
//                }
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

    private void updateFromDate() {
        String myFormat = "MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        fromDateEtxt.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateToDate() {
        String myFormat = "MMM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        toDateEtxt.setText(sdf.format(myCalendar.getTime()));
    }

}