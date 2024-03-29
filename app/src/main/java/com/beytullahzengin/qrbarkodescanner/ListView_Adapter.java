package com.beytullahzengin.qrbarkodescanner;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by SONU on 29/03/16.
 */
public class ListView_Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item_Model> item_modelArrayList;
    private SparseBooleanArray mSelectedItemsIds;

    public ListView_Adapter(Context context, ArrayList<Item_Model> item_modelArrayList) {
        this.context = context;
        this.item_modelArrayList = item_modelArrayList;
        mSelectedItemsIds = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return item_modelArrayList.size();
    }

    @Override
    public Item_Model getItem(int position) {
        return item_modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_row, parent, false);
            holder = new ViewHolder();
            holder.Result = (TextView) convertView.findViewById(R.id.result);
            holder.Date_time = (TextView) convertView.findViewById(R.id.date_time);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.Result.setText(item_modelArrayList.get(position).getResult());
        holder.Date_time.setText(item_modelArrayList.get(position).getDate_time());


        /** Change background color of the selected items in list view  **/
        convertView
                .setBackgroundColor(mSelectedItemsIds.get(position) ? 0x48FF9800
                        : Color.TRANSPARENT);

        return convertView;
    }

    private class ViewHolder {
        TextView Result, Date_time;
    }


    /***
     * Methods required for do selections, remove selections, etc.
     */

    //Toggle selection methods
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }


    //Remove selected selections
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }


    //Put or delete selected position into SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);

        notifyDataSetChanged();
    }

    //Get total selected count
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    //Return all selected ids
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
