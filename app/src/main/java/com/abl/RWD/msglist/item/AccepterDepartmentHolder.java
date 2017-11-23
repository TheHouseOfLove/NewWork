package com.abl.RWD.msglist.item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.adapter.AdapterAccepterItem;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.entity.VAccepterItemEntity;
import com.abl.RWD.listener.IAcceptrChangeListener;
import com.abl.RWD.listener.IItemCheckedListener;
import com.abl.RWD.msglist.base.BaseViewHolder;

import java.util.ArrayList;

import static com.abl.RWD.common.Global.getContext;

/**
 * Created by yas on 2017/11/23.
 */

public class AccepterDepartmentHolder extends BaseViewHolder<VAcceptDepartmentEntity> {
    private static final String TAG = "AccpterHolder";
    private RecyclerView mMsgPage;
    private AdapterAccepterItem mAdapter;
    private TextView text_bumen;
    private ArrayList<String> userIds;
    private ArrayList<VAccepterItemEntity> arr;
    private String isRadio;
    private IItemCheckedListener mCheckedListener;

    public AccepterDepartmentHolder(Context mContext, ViewGroup parent) {
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
    public void setMsg(VAcceptDepartmentEntity t) {
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
                VAccepterItemEntity entity=new VAccepterItemEntity();
                entity.name="接收人" + i;
                arr.add(entity);
            }
        }
        mAdapter = new AdapterAccepterItem(getContext(), arr, mListener);
        mMsgPage.setAdapter(mAdapter);
    }

    private IAcceptrChangeListener mListener=new IAcceptrChangeListener() {
        @Override
        public void selectedListener(int pos) {
            mAdapter.clearOtherChecked(pos);
        }

        @Override
        public void cancelSelectListener(int pos) {

        }
    };
}
