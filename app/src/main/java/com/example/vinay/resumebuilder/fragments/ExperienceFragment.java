package com.example.vinay.resumebuilder.fragments;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.vinay.resumebuilder.DatabaseHandler;
import com.example.vinay.resumebuilder.Ex_ListViewAdapter;
import com.example.vinay.resumebuilder.NavigationActivity;
import com.example.vinay.resumebuilder.R;
import com.example.vinay.resumebuilder.model.WorkExperience;

import java.util.List;


public  class ExperienceFragment extends Fragment {
    //extends DialogFragment implements DatePickerDialog.DatePickerlistener {
    FloatingActionButton ex_fab;
    DatabaseHandler dbhandler;
    Activity context;
    String JobTitle[] = null;
    String JobDescrition[]= null;
    String CompanyName[]= null;
    String Workfrom[]= null;
    String Workto[]= null;
    String stillWorking[]= null;
    int ids[]= null;
    Ex_ListViewAdapter ex_listViewAdapter;
    ListView lv;
    Fragment frg = null;
//    CheckBox ex_cb;
//    EditText ex_et4,ex_et5;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        dbhandler = new DatabaseHandler(getContext());
        View rootView = inflater.inflate(R.layout.fragment_tab_experience, container, false);
        lv = (ListView)rootView.findViewById(R.id.ex_list);
        int id = NavigationActivity.cid;
        List<WorkExperience> cd = dbhandler.getAllExperienceDetails(id);
        if (cd.size() > 0) {
            JobTitle = new String[cd.size()];
            JobDescrition = new String[cd.size()];
            CompanyName = new String[cd.size()];
            ids   = new int[cd.size()];
            Workfrom = new String[cd.size()];
            Workto = new String[cd.size()];
            stillWorking = new String[cd.size()];

            int i = 0;
            for(WorkExperience we : cd){
                JobTitle[i] = we.getexJobtitle();
                JobDescrition[i] = we.getexJobdescription();
                CompanyName[i] = we.getexCompanyname();
                Workfrom[i] = we.getexStartdate();
                Workto[i] = we.getexEnddate();
                stillWorking[i] = we.getExStlworking();
                ids[i] = we.getexCid();
                i++;
            }

            ex_listViewAdapter = new Ex_ListViewAdapter(getActivity(), JobTitle,CompanyName,Workfrom,Workto,stillWorking,ids);
            lv.setAdapter(ex_listViewAdapter);
        }
        else{
            Toast.makeText(getContext(), "No Data Found", Toast.LENGTH_SHORT).show();
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                PopupMenu menu = new PopupMenu (getContext(), view);
                menu.setOnMenuItemClickListener (new PopupMenu.OnMenuItemClickListener ()
                {
                    @Override
                    public boolean onMenuItemClick (MenuItem item)
                    {
                        int id = item.getItemId();
                        switch (id)
                        {
                            case R.id.edit:

                                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View dialogView = inflater.inflate(R.layout.ex_customdialog, null);
                                dialogBuilder.setView(dialogView);

                                final EditText jTitle = (EditText) dialogView.findViewById(R.id.jobTitleET);
                                final EditText jDesc = (EditText) dialogView.findViewById(R.id.jobDescET);
                                final EditText cName = (EditText) dialogView.findViewById(R.id.compNameET);
                                final EditText wFrom = (EditText) dialogView.findViewById(R.id.wrkFromET);
                                final EditText wTo = (EditText) dialogView.findViewById(R.id.wrkToET);
                                final CheckBox stlWrk = (CheckBox) dialogView.findViewById(R.id.stlWrkCB);

                                stlWrk.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (((CheckBox) v).isChecked()) {
                                            wTo.setText("");
                                            wTo.setEnabled(false);
                                        }
                                        else if (!((CheckBox) v).isChecked()) {
                                            wTo.setEnabled(true);
                                        }
                                    }
                                });

                                final String title = JobTitle[position];
                                final String desc = JobDescrition[position];
                                final String comp = CompanyName[position];
                                final String wF = Workfrom[position];
                                final String wT = Workto[position];
                                final String sWork = stillWorking[position];
                                final int iD= ids[position];

                                jTitle.setText(title);
                                jDesc.setText(desc);
                                cName.setText(comp);
                                wFrom.setText(wF);
                                wTo.setText(wT);
                                if(sWork!=null) {
                                    stlWrk.setChecked(true);
                                    wTo.setText("");
                                    wTo.setEnabled(false);
                                }
                                else{
                                    stlWrk.setChecked(false);
                                }

                                dialogBuilder.setTitle("Enter Company Details");
                                dialogBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {

                                        WorkExperience we = new WorkExperience();
                                        we.setexCid(iD);
                                        we.setexJobtitle(jTitle.getText().toString());
                                        we.setexJobdescription(jDesc.getText().toString());
                                        we.setexCompanyname(cName.getText().toString());
                                        we.setexStartdate(wFrom.getText().toString());
                                        we.setexEnddate(wTo.getText().toString());
                                        we.setExStlworking(stlWrk.getText().toString());
                                        long n = dbhandler.updateExperience(we);
                                        if(n>0){
                                            Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                                            dbhandler.updateDate(NavigationActivity.cid);
                                            getFragmentManager().beginTransaction().detach(ExperienceFragment.this).attach(ExperienceFragment.this).commit();

                                        }
                                        else{
                                            Toast.makeText(getContext(), "problem in updating", Toast.LENGTH_SHORT).show();
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

                                break;
                            case R.id.delete:
                                AlertDialog.Builder dialogBuilderDel = new AlertDialog.Builder(getContext());
                                LayoutInflater inflaterDel = getActivity().getLayoutInflater();
                                final View dialogViewDel = inflaterDel.inflate(R.layout.delete_dialog, null);
                                dialogBuilderDel.setView(dialogViewDel);

                                dialogBuilderDel.setMessage("Do you want to delete this Profile ?");
                                dialogBuilderDel.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //do something with edt.getText().toString();
                                        int pos = ids[position];
                                        String title = JobTitle[position];
                                        boolean b = dbhandler.deleteWorkExperience(title);
                                        if (b == true) {
                                            Toast.makeText(getContext(), "deleted " + ids[position] + JobTitle[position] + "pos" + position, Toast.LENGTH_SHORT).show();
                                            getFragmentManager().beginTransaction().detach(ExperienceFragment.this).attach(ExperienceFragment.this).commit();

                                        } else {
                                            Toast.makeText(getContext(), "cant delete", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                dialogBuilderDel.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        //pass
                                    }
                                });
                                AlertDialog bDel = dialogBuilderDel.create();
                                bDel.show();
                                break;
                        }
                        return true;
                    }
                });

                menu.inflate (R.menu.popup_menu);
                menu.show();

            }
        });


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

        final EditText jTitle = (EditText) dialogView.findViewById(R.id.jobTitleET);
        final EditText jDesc = (EditText) dialogView.findViewById(R.id.jobDescET);
        final EditText cName = (EditText) dialogView.findViewById(R.id.compNameET);
        final EditText wFrom = (EditText) dialogView.findViewById(R.id.wrkFromET);
        final EditText wTo = (EditText) dialogView.findViewById(R.id.wrkToET);
        final CheckBox stlWrk = (CheckBox) dialogView.findViewById(R.id.stlWrkCB);


        dialogBuilder.setTitle("Enter Company Details");
                dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {

                String jobTitle = jTitle.getText().toString();
                String jobDescription = jDesc.getText().toString();
                String companyName = cName.getText().toString();
                String startDate = wFrom.getText().toString();
                String endDate = wTo.getText().toString();
                String sWorking= stlWrk.getText().toString();
                int exCid = NavigationActivity.cid;

                WorkExperience workExperience = new WorkExperience();
                workExperience.setexCid(exCid);
                workExperience.setexJobtitle(jobTitle);
                workExperience.setexJobdescription(jobDescription);
                workExperience.setexCompanyname(companyName);
                workExperience.setexStartdate(startDate);
                workExperience.setexEnddate(endDate);
                workExperience.setExStlworking(sWorking);
                long n = dbhandler.addWorkExperience(workExperience);
                if (n > 0) {
                    Toast.makeText(getContext(), "Details are saved Successfully", Toast.LENGTH_SHORT).show();
                    getFragmentManager().beginTransaction().detach(ExperienceFragment.this).attach(ExperienceFragment.this).commit();

                } else {
                    Toast.makeText(getContext(), "Problem in adding", Toast.LENGTH_SHORT).show();

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

}







