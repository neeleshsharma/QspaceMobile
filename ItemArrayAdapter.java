package com.TechM.QSpace;

/**
 * Created by User on 10-10-2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter {
    private List iqaList = new ArrayList();

    static class ItemViewHolder {
        TextView ncstatus;
        TextView projectid;
        TextView projectname;
        TextView auditmonth;
        TextView opennccount;
        TextView ncage;
    }

    public ItemArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }


    public void add(String[] object) {
        iqaList.add(object);
        super.add(object);
    }

    @Override
    public int getCount() {
        return this.iqaList.size();
    }

    @Override
    public String[] getItem(int index) {
        return (String[]) this.iqaList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder = new ItemViewHolder();
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_fetch, parent, false);

            /*viewHolder.ncstatus = (TextView) row.findViewById(R.id.ncstatus);
            viewHolder.projectid = (TextView) row.findViewById(R.id.projectid);
            viewHolder.projectname = (TextView) row.findViewById(R.id.projectname);
            viewHolder.auditmonth = (TextView) row.findViewById(R.id.auditmonth);
            viewHolder.opennccount = (TextView) row.findViewById(R.id.opennccount);
            viewHolder.ncage = (TextView) row.findViewById(R.id.ncage);*/
            row.setTag(viewHolder);
        } else {
            viewHolder = (ItemViewHolder)row.getTag();
        }
        String[] stat = getItem(position);
        viewHolder.ncstatus.setText(stat[0]) ;
        viewHolder.projectid.setText(stat[1]);
        viewHolder.projectname.setText(stat[2]);
        viewHolder.auditmonth.setText(stat[3]);
        viewHolder.opennccount.setText(stat[4]);
        viewHolder.ncage.setText(stat[5]);
        return row;
    }
}