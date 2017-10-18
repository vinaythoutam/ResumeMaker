package com.example.vinay.resumebuilder;


import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinay.resumebuilder.model.CardDetails;

public class ListViewAdapter extends BaseAdapter
{

    DatabaseHandler dbHandler;

    Activity context;
    String names[];
    String dates[];
    String types[];
    int ids[];
    String uDates[];
    public ListViewAdapter(Activity context, String[] names, String[] dates, String types[], int ids[], String[] uDates) {

        super();
        this.context = context;
        this.names = names;
        this.dates = dates;
        this.types= types;
        this.ids=ids;
        this.uDates=uDates;
        dbHandler= new DatabaseHandler(context);


    }

    public int getCount() {
        // TODO Auto-generated method stub
        return names.length;
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
        TextView pName;
        TextView pDate;
        TextView pType;
        ImageView del;
        TextView pUdate;

    }

    public View getView(final int position, View convertView, final ViewGroup parent)
    {
        // TODO Auto-generated method stub
        final ViewHolder holder;
        final LayoutInflater inflater =  context.getLayoutInflater();


        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.add_profile, null);
            holder = new ViewHolder();
            holder.pName = (TextView) convertView.findViewById(R.id.cvName);
            holder.pDate = (TextView) convertView.findViewById(R.id.cvCreatedOn);
            holder.pType = (TextView) convertView.findViewById(R.id.cvType);
            holder.pUdate=(TextView) convertView.findViewById(R.id.cvUpdatedOn);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.pName.setText(names[position]);
        holder.pDate.setText(dates[position]);
        holder.pType.setText(types[position]);
        holder.pUdate.setText(uDates[position]);

        holder.del = (ImageView) convertView.findViewById(R.id.delCard);
        holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        showDeleteProfileDialog(position);

                    }
                });

        return convertView;
    }
    public void showDeleteProfileDialog(final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = context.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.delete_dialog, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setMessage("Do you want to delete this Profile ?");
        dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                int pos = ids[position];

                boolean n = dbHandler.deleteProfile(pos);

                Toast.makeText(context, "deleted " + ids[position]+names[position]+"pos"+position , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,AddProfilesActivity.class);
                context.overridePendingTransition(0,0);
                context.finish();
                context.startActivity(intent);
                context.overridePendingTransition(0,0);

            }
        });
        dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


}
