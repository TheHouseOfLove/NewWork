package com.abl.RWD.msglist.item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.adapter.AdapterAccpterItem;
import com.abl.RWD.entity.VJieShouRenEntity;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.msglist.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.abl.RWD.common.Global.getContext;

/**
 * Created by yas on 2017/11/23.
 */

public class AccpterHolder extends BaseViewHolder<VJieShouRenEntity> {
    private static final String TAG = "AccpterHolder";
    private RecyclerView mMsgPage;
    private AdapterAccpterItem mAdapter;
    private TextView text_bumen;
    private ArrayList<String> userIds;
    private ArrayList<String> arr;
    private String isRadio;
    private IItemCheckedListener mCheckedListener;

    public AccpterHolder(Context mContext, ViewGroup parent) {
        super(mContext, parent, R.layout.jieshouren_item);
    }

    @Override
    public void initView() {
        mMsgPage = itemView.findViewById(R.id.mMsgPage);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mMsgPage.setLayoutManager(manager);
        text_bumen = itemView.findViewById(R.id.text_bumen);
    }

    @Override
    public void setMsg(VJieShouRenEntity t) {
        userIds = new ArrayList<>();
        arr = new ArrayList<>();
        if (t != null) {
//            if (t.type == VJieShouRenEntity.TYPE_HT) {
//            text_bumen.setText(t.HTInfoEntity.nodeName);
//                String userList = t.HTInfoEntity.usersList;
//                if (!TextUtils.isEmpty(userList)) {
//                    String[] users = userList.split(",");
//                    for (int i = 0; i < users.length; i++) {
//                        userIds.add(users[i].split("\\|")[0]);
//                        String name = users[i].split("\\|")[1];
//                        arr.add(name);
//                    }
//                }
//            } else if (t.type == VJieShouRenEntity.TYPE_TJ) {
//            text_bumen.setText(t.TJInfoEntity.nodeName);
//                isRadio = t.TJInfoEntity.userBLType;
//                String userList = t.TJInfoEntity.usersList;
//                if (!TextUtils.isEmpty(userList)) {
//                    String[] users = userList.split(",");
//                    for (int i = 0; i < users.length; i++) {
//                        userIds.add(users[i].split("\\|")[0]);
//                        String name = users[i].split("\\|")[1];
//                        arr.add(name);
//                    }
//                }
            for (int i = 1; i < 5; i++) {
                arr.add("接收人" + i);
            }
        }
        mAdapter = new AdapterAccpterItem(getContext(), arr, null);
        mMsgPage.setAdapter(mAdapter);
    }
}
