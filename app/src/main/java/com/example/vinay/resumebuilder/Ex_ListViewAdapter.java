package com.example.vinay.resumebuilder;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinay.resumebuilder.fragments.ExperienceFragment;

/**
 * Created by raskolnikov on 18/10/17.
 */

public class Ex_ListViewAdapter extends BaseAdapter {
    DatabaseHandler dbhandler;
    Activity context;
    String JobTitle[];
    String CompanyName[];
    String Workfrom[];
    String Workto[];
    String stillWorking[];
    int ids[];

    public Ex_ListViewAdapter(Activity context, String[] JobTitle, String CompanyName[], String Workfrom[], String Workto[], String[] stillWorking, int ids[]) {
        super();
        this.context = context;
        this.JobTitle = JobTitle;
        this.CompanyName = CompanyName;
        this.Workfrom = Workfrom;
        this.Workto = Workto;
        this.stillWorking = stillWorking;
        this.ids = ids;
        dbhandler = new DatabaseHandler(context);
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return JobTitle.length;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder {
        TextView exJobtitle, exCompanyname, exStartdate, exEnddate;
        ImageView exDel, exEdit;

    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        final LayoutInflater inflater = context.getLayoutInflater();


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.add_experience, null);
            holder = new ViewHolder();
            holder.exJobtitle = (TextView) convertView.findViewById(R.id.ex_tv1);
            holder.exCompanyname = (TextView) convertView.findViewById((R.id.ex_tv2));
            holder.exDel = (ImageView) convertView.findViewById(R.id.ex_iv2);
            holder.exEdit = (ImageView) convertView.findViewById(R.id.ex_iv1);
            holder.exStartdate = (TextView) convertView.findViewById(R.id.ex_tv3);
            holder.exEnddate = (TextView) convertView.findViewById(R.id.ex_tv4);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.exJobtitle.setText(JobTitle[position]);
        holder.exCompanyname.setText(CompanyName[position]);
        holder.exStartdate.setText(Workfrom[position]);
        if (stillWorking[position] != null) {
            holder.exEnddate.setText("till now");
        } else {
            holder.exEnddate.setText(Workto[position]);
        }

        holder.exDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //showDeleteProfileDialog(position);




            }
        });

        return convertView;
    }

//    public void showDeleteProfileDialog(final int position) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
//        LayoutInflater inflater = context.getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.delete_dialog, null);
//        dialogBuilder.setView(dialogView);
//
//        dialogBuilder.setMessage("Do you want to delete this Profile ?");
//        dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //do something with edt.getText().toString();
//                int pos = ids[position];
//                String title = JobTitle[position];
//                Toast.makeText(context, "" + title, Toast.LENGTH_SHORT).show();
//
//                boolean b = dbhandler.deleteWorkExperience(title);
//                if (b == true) {
//                    Toast.makeText(context, "deleted " + ids[position] + JobTitle[position] + "pos" + position, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, "cant delete", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//        dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                //pass
//            }
//        });
//        AlertDialog b = dialogBuilder.create();
//        b.show();
//    }


}
