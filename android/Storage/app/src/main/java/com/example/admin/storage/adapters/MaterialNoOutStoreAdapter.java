package com.example.admin.storage.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.Material;

import java.util.List;

/**
 * Created by admin on 2018/4/17.
 */

public class MaterialNoOutStoreAdapter extends BaseAdapter {
    //
    private Context context;
    public List<Material> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public MaterialNoOutStoreAdapter(Context context, List<Material> data) {
        this.context = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MaterialNoOutStoreAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_material_no_outstore, parent, false);
            holder = new MaterialNoOutStoreAdapter.ViewHolder();

            holder.mEncode_tv = (TextView) convertView.findViewById(R.id.id_encode);
            holder.mDescribe_tv = (TextView) convertView.findViewById(R.id.id_describe);
            holder.applyNum_tv = (TextView) convertView.findViewById(R.id.id_apply_num);
            holder.nooutNum_tv = (TextView) convertView.findViewById(R.id.id_noout_num);
//            holder.canReceiveNum_tv = (TextView) convertView.findViewById(R.id.id_canreceive_num);
            holder.unit_tv = (TextView) convertView.findViewById(R.id.id_unit);

            convertView.setTag(holder);
        } else {
            holder = (MaterialNoOutStoreAdapter.ViewHolder) convertView.getTag();
        }
        final Material material = mData.get(position);
        Log.i("adapter11", "size--" + mData.size());
        mData.set(position, material);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);

        holder.mEncode_tv.setText(material.getEncode());
        holder.mDescribe_tv.setText(material.getDescribe());
        holder.applyNum_tv.setText(material.getApplyoutNum());
        holder.nooutNum_tv.setText(material.getApplyNooutNum());
        holder.unit_tv.setText(material.getUnit());


        return convertView;

    }


    private static class ViewHolder {

        TextView mEncode_tv;
        TextView mDescribe_tv;
        TextView applyNum_tv;
        TextView nooutNum_tv;
        TextView unit_tv;

    }
}
