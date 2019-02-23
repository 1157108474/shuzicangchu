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

public class MaterialNoIntoStoreAdapter extends BaseAdapter {
    //
    private Context context;
    public List<Material> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public MaterialNoIntoStoreAdapter(Context context, List<Material> data) {
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
        MaterialNoIntoStoreAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_material_nointostore, parent, false);
            holder = new MaterialNoIntoStoreAdapter.ViewHolder();

            holder.mEncode_tv = (TextView) convertView.findViewById(R.id.id_encode);
            holder.mDescribe_tv = (TextView) convertView.findViewById(R.id.id_describe);
            holder.gongyingshang_tv = (TextView) convertView.findViewById(R.id.id_provider);
            holder.receivedNum_tv = (TextView) convertView.findViewById(R.id.id_received_num);
            holder.canIntoNum_tv = (TextView) convertView.findViewById(R.id.id_caninto_num);
            holder.isSerial_tv = (TextView) convertView.findViewById(R.id.id_is_serial_number);
            holder.salesType_tv = (TextView) convertView.findViewById(R.id.id_sales_type);
//            holder.canReceiveNum_tv = (TextView) convertView.findViewById(R.id.id_canreceive_num);
            holder.unit_tv = (TextView) convertView.findViewById(R.id.id_unit);

            convertView.setTag(holder);
        } else {
            holder = (MaterialNoIntoStoreAdapter.ViewHolder) convertView.getTag();
        }
        final Material material = mData.get(position);
        Log.i("adapter11", "size--" + mData.size());
        mData.set(position, material);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);

        holder.mEncode_tv.setText(material.getEncode());
        holder.mDescribe_tv.setText(material.getDescribe());
        holder.gongyingshang_tv.setText(material.getProvider());
        holder.receivedNum_tv.setText(material.getReceivedNum());
        holder.canIntoNum_tv.setText(material.getCanPutawayNum());
        holder.unit_tv.setText(material.getUnit());
        holder.isSerial_tv.setText(material.getIsSerialInt()==0?"不启用":"启用");
        holder.salesType_tv.setText(material.getSalesType());


        return convertView;

    }


    private static class ViewHolder {

        TextView mEncode_tv;
        TextView mDescribe_tv;
        TextView gongyingshang_tv;
        TextView receivedNum_tv;
        TextView canIntoNum_tv;
        TextView isSerial_tv;


        TextView salesType_tv;
        TextView unit_tv;

    }
}
