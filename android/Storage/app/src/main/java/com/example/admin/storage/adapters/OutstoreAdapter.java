package com.example.admin.storage.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.Bill;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class OutstoreAdapter extends BaseAdapter {
//
private Context context;
    public List<Bill> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public OutstoreAdapter(Context context, List<Bill> data){
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_outstore, parent, false);
            holder = new ViewHolder();
            holder.apply_number_tv = (TextView) convertView.findViewById(R.id.id_apply_number);
            holder.use_unit_tv = (TextView) convertView.findViewById(R.id.id_use_unit);
            holder.time_tv = (TextView) convertView.findViewById(R.id.id_apply_tine);
//            holder.fafanghao_tv = (TextView) convertView.findViewById(R.id.fafanghao);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Bill mbill = mData.get(position);
        Log.i("adapter11","size--"+mData.size() );
        mData.set(position, mbill);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);
        holder.apply_number_tv.setText(mbill.getOrderNumber());
        holder.use_unit_tv.setText(mbill.getUseUnit());
        holder.time_tv.setText(mbill.getCreateTime());


        return convertView;

    }

    private static class ViewHolder {
        TextView apply_number_tv;
        TextView use_unit_tv;
        TextView time_tv;

    }

}
