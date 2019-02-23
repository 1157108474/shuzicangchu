package com.example.admin.storage.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.admin.storage.R;
import com.example.admin.storage.activity.buyer.BuyerReceiveActivity;
import com.example.admin.storage.activity.check.CheckStoreActivity;
import com.example.admin.storage.activity.intostore.IntoStoreActivity;
import com.example.admin.storage.activity.movestore.MoveStoreActivity;
import com.example.admin.storage.activity.outstore.OutStoreActivity;
import com.example.admin.storage.activity.storequery.StoreQueryActivity;
import com.example.admin.storage.baseclass.UserInfo;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by shang
 */
public class WorkFragment extends Fragment implements View.OnClickListener{
    private List<Integer> mImageList = new ArrayList<>();
    private int[] mImages  = {R.drawable.lunbo1,R.drawable.lunbo2};
    private ConvenientBanner mCb;

    TextView tv1,tv2,tv3,tv4,tv5,tv6;
    String userPower;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);

        mCb = (ConvenientBanner) view.findViewById(R.id.id_cb);
        tv1 = (TextView) view.findViewById(R.id.id_buyer_tv);
        tv1.setOnClickListener(this);
        tv2 = (TextView) view.findViewById(R.id.id_intostore_tv);
        tv2.setOnClickListener(this);
        tv3 = (TextView) view.findViewById(R.id.id_outstore_tv);
        tv3.setOnClickListener(this);
        tv4 = (TextView) view.findViewById(R.id.id_storetake_tv);
        tv4.setOnClickListener(this);
        tv5 = (TextView) view.findViewById(R.id.id_move_tv);
        tv5.setOnClickListener(this);
        tv6 = (TextView) view.findViewById(R.id.id_chek_tv);
        tv6.setOnClickListener(this);
        for (int i = 0; i < 2; i++) {
            mImageList.add(mImages[i]);
        }
        mCb.setPages(new CBViewHolderCreator<ImageViewHolder>() {
            @Override
            public ImageViewHolder createHolder() {
                return new ImageViewHolder();
            }
        },mImageList)
                .setPageIndicator(new int[]{R.drawable.ponit_normal,R.drawable.point_select}) //设置两个点作为指示器
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL); //设置指示器的方向水平居中

        mCb.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if(mCb!=null){
                    mCb.stopTurning();   //停止轮播
                }
//                Toast.makeText(MainActivity.this,"点击了条目"+position,Toast.LENGTH_LONG).show();
            }
        });
        SharedPreferences sp = this.getActivity().getSharedPreferences("user",MODE_PRIVATE);
        userPower = sp.getString("userpower","");

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();

        mCb.startTurning(2000);      //设置开始轮播以及轮播时间
    }

    @Override
    public void onPause() {
        if(mCb!=null){
            mCb.stopTurning();   //停止轮播
        }
        super.onPause();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.id_buyer_tv){
            if(userPower.contains("wzjs_sheet")){
                Intent intent = new Intent(getActivity(), BuyerReceiveActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }

        } else if(id == R.id.id_intostore_tv){
            if(userPower.contains("receipt_details")){
                Intent intent = new Intent(getActivity(), IntoStoreActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }

        }else if(id == R.id.id_outstore_tv){
            if(userPower.contains("sheet_CK")){
                Intent intent = new Intent(getActivity(), OutStoreActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.id_storetake_tv){
            if(userPower.contains("KCPDZD")){
                Intent intent = new Intent(getActivity(), CheckStoreActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }
        }
        else if(id == R.id.id_move_tv){
            if(userPower.contains("sheet_YW")){
                Intent intent = new Intent(getActivity(), MoveStoreActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }
        }else if(id == R.id.id_chek_tv){
            if(userPower.contains("querys")){
                Intent intent = new Intent(getActivity(), StoreQueryActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(),"无此权限！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
