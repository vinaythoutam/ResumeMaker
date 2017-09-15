package com.example.vinay.resumebuilder;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewAdapter extends BaseAdapter
{

    DatabaseHandler dbHandler;

    Activity context;
    String names[];
    String dates[];
    String types[];
    int ids[];
    public ListViewAdapter(Activity context, String[] names, String[] dates, String types[],int ids[]) {

        super();
        this.context = context;
        this.names = names;
        this.dates = dates;
        this.types= types;
        this.ids=ids;

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

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.pName.setText(names[position]);
        holder.pDate.setText(dates[position]);
        holder.pType.setText(types[position]);

                 holder.del = (ImageView) convertView.findViewById(R.id.delCard);
                holder.del.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int pos = ids[position];
                        //Toast.makeText(context, "id"+pos, Toast.LENGTH_SHORT).show();
                        dbHandler= new DatabaseHandler(context);
                        boolean n = dbHandler.deleteProfile(pos);

                        Toast.makeText(context, "deleted " + ids[position] +"pos"+position , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,AddProfilesActivity.class);
                        context.overridePendingTransition(0,0);
                        context.finish();
                        context.startActivity(intent);
                        context.overridePendingTransition(0,0);
                    }
                });

        return convertView;
    }


}
