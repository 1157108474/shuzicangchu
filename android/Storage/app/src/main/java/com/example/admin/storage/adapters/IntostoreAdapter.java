package com.example.admin.storage.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.Receipt;

import java.util.List;

/**
 * Created by Administrator on 2018/1/30.
 */

public class IntostoreAdapter extends BaseAdapter {
//
private Context context;
    public List<Receipt> mData;//定义数据。
    private LayoutInflater mInflater;//定义Inflater,加载我们自定义的布局。

    /*
    定义构造器，在Activity创建对象Adapter的时候将数据data和Inflater传入自定义的Adapter中进行处理。
    */
    public IntostoreAdapter(Context context, List<Receipt> data){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_adapter_intostore, parent, false);
            holder = new ViewHolder();
            holder.jieshoudan_tv = (TextView) convertView.findViewById(R.id.id_jieshoudanhao);
            holder.dingdan_tv = (TextView) convertView.findViewById(R.id.caigoudingdan);
            holder.gongyinshang_tv = (TextView) convertView.findViewById(R.id.gongyingshang);
            holder.time_tv = (TextView) convertView.findViewById(R.id.id_time);
//            holder.fafanghao_tv = (TextView) convertView.findViewById(R.id.fafanghao);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Receipt receipt = mData.get(position);
        Log.i("adapter11","size--"+mData.size() );
        mData.set(position, receipt);


//        holder.image2.setImageResource(R.mipmap.ic_launcher);
        holder.jieshoudan_tv.setText(receipt.getJieshouOrder());
        holder.dingdan_tv.setText(receipt.getOrder());
        holder.gongyinshang_tv.setText(receipt.getProvider());
        holder.time_tv.setText(receipt.getJieshouTime());


        return convertView;

    }

    private static class ViewHolder {
        TextView jieshoudan_tv;
        TextView dingdan_tv;
        TextView gongyinshang_tv;
//        TextView leixing_tv;
        TextView time_tv;

    }

}