package com.example.admin.storage.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.admin.storage.R;
import com.example.admin.storage.baseclass.UserInfo;
import com.example.admin.storage.utiles.APKVersionCodeUtils;

import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by shang
 */
public class SettingFragment extends Fragment {

    private UserInfo userInfo;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2, container, false);
        sp = this.getActivity().getSharedPreferences("user",MODE_PRIVATE);
        String ustr = sp.getString("userinfo","");
        userInfo = com.alibaba.fastjson.JSONObject.parseObject(ustr,UserInfo.class);
        String versionCode = APKVersionCodeUtils.getVerName(getActivity()) + "";
        TextView count = (TextView)view.findViewById(R.id.account_tv);
        count.setText(userInfo.code);
        TextView version = (TextView) view.findViewById(R.id.version_tv);
        version.setText(versionCode);
        Button btn_logout = (Button) view.findViewById(R.id.btn_logout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("userinfo", "");
//                editor.putInt("rememb", 0);
                editor.commit();
                getActivity().finish();
                Intent it = new Intent(getActivity(),LoginActivity.class);
                startActivity(it);
            }
        });

        return view;
    }
}
