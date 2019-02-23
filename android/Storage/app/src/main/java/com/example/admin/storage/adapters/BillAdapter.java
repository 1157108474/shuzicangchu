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
 * Created by admin on 2018/4/17.
 */

public class BillAdapter extends BaseAdapter {
    //
    private Context context;
    public List<Bill> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public BillAdapter(Context context, List<Bill> data) {
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
        BillAdapter.ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_bill, parent, false);
            holder = new BillAdapter.ViewHolder();

            holder.order_tv = (TextView) convertView.findViewById(R.id.id_bill_number);
            holder.status_tv = (TextView) convertView.findViewById(R.id.id_creator);
            holder.createTime_tv = (TextView) convertView.findViewById(R.id.id_create_time);

            convertView.setTag(holder);
        } else {
            holder = (BillAdapter.ViewHolder) convertView.getTag();
        }
        final Bill mBill = mData.get(position);
        Log.i("adapter11", "size--" + mData.size());
        mData.set(position, mBill);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);

        holder.order_tv.setText(mBill.getOrderNumber());
        holder.status_tv.setText(mBill.getCreator());
        holder.createTime_tv.setText(mBill.getCreateTime());

        return convertView;

    }


    private static class ViewHolder {

        TextView order_tv;
        TextView status_tv;
        TextView createTime_tv;

    }
}
