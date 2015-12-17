package com.line.krishna.investmentapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.line.krishna.investmentapp.R;
import com.line.krishna.investmentapp.core.Line;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LineAdapter extends BaseAdapter implements Filterable{

    private final LayoutInflater mInflater;
    private ArrayList<Line> mLines=new ArrayList<>();
    private ArrayList<Line> mOriginalLines;

    public LineAdapter(Context context, ArrayList<Line> lines) {
        mOriginalLines=lines;
        mLines.addAll(mOriginalLines);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mLines.size();
    }

    @Override
    public Line getItem(int position) {
        return mLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.item_line, null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        holder.updateView(getItem(position));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    private static class ViewHolder {
        private TextView tvName;
        private ViewHolder(View view){
            tvName= (TextView) view.findViewById(R.id.tvName);
        }

        private void updateView(Line line){
            tvName.setText(getLineValue(line));
        }
    }

    private static String getLineValue(Line line) {
        return line.getName()+" ("+line.getWeekDay()+")";
    }

    Filter myFilter = new Filter() {

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            System.out.println("Constraint " + constraint);
            Log.d("-----------", "publishResults");
            if (results!=null && results.count > 0) {
                mLines= (ArrayList<Line>) results.values;
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
            Log.d("-----------", "publishResults:"+mLines);
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            Log.d("-----------", "performFiltering");
            FilterResults results = new FilterResults();
            List<Line> filteredArrList = new ArrayList<Line>();
            Locale locale = Locale.getDefault();
            if (constraint == null || constraint.length() == 0) {
                // set the Original result to return
                results.count = mOriginalLines.size();
                results.values = mOriginalLines;
            } else {
                constraint = constraint.toString().toLowerCase(locale);

                for (int i = 0; i < mOriginalLines.size(); i++) {
                    Line line = mOriginalLines.get(i);

                    if (getLineValue(line).toLowerCase(locale).contains(constraint)) {
                        filteredArrList.add(line);
                    }
                }
                // set the Filtered result to return
                results.count = filteredArrList.size();
                results.values = filteredArrList;
            }
            return results;
        }
    };

}