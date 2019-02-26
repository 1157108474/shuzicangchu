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

public class MaterialCheckAdapter extends BaseAdapter {
    //
    private Context context;
    public List<Material> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public MaterialCheckAdapter(Context context, List<Material> data) {
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
        MaterialCheckAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_material_check, parent, false);
            holder = new MaterialCheckAdapter.ViewHolder();

            holder.mEncode_tv = (TextView) convertView.findViewById(R.id.id_encode);
            holder.mDescribe_tv = (TextView) convertView.findViewById(R.id.id_describe);
            holder.gongyingshang_tv = (TextView) convertView.findViewById(R.id.id_provider);
            holder.stockNum_tv = (TextView) convertView.findViewById(R.id.id_count);

            holder.strage_tv = (TextView) convertView.findViewById(R.id.id_storage);
            holder.storageLocation_tv = (TextView) convertView.findViewById(R.id.id_storage_location);
//            holder.canReceiveNum_tv = (TextView) convertView.findViewById(R.id.id_canreceive_num);
            holder.unit_tv = (TextView) convertView.findViewById(R.id.id_unit);

            convertView.setTag(holder);
        } else {
            holder = (MaterialCheckAdapter.ViewHolder) convertView.getTag();
        }
        final Material material = mData.get(position);
        Log.i("adapter11", "size--" + mData.size());
        mData.set(position, material);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);

        holder.mEncode_tv.setText(material.getEncode());
        holder.mDescribe_tv.setText(material.getDescribe());
        holder.gongyingshang_tv.setText(material.getProvider());
        holder.stockNum_tv.setText(material.getStockNum());
        holder.strage_tv.setText(material.getStoreName());
        holder.unit_tv.setText(material.getUnit());
        holder.storageLocation_tv.setText(material.getStoreLocation());


        return convertView;

    }


    private static class ViewHolder {

        TextView mEncode_tv;
        TextView mDescribe_tv;
        TextView gongyingshang_tv;
        TextView stockNum_tv;
        TextView strage_tv;
        TextView storageLocation_tv;
        TextView unit_tv;

    }
}