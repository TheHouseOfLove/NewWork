package com.abl.RWD.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.RWD.R;
import com.abl.RWD.adapter.AdapterAccepterItem;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.entity.VAccepterItemEntity;
import com.abl.RWD.listener.IAcceptrChangeListener;
import com.abl.RWD.listener.IItemCheckedListener;

import java.util.ArrayList;
/**
 * Created by yas on 2017/11/23.
 */

public class AcceptDepartmentView extends LinearLayout{
    private RecyclerView mMsgPage;
    private AdapterAccepterItem mAdapter;
    private TextView text_bumen;
    private ArrayList<String> userIds;
    private ArrayList<VAccepterItemEntity> arr;
    private String isRadio;
    private IItemCheckedListener mCheckedListener;
    private VAcceptDepartmentEntity mEntity;
    private int index;
    public AcceptDepartmentView(Context context,IItemCheckedListener mCheckedListener) {
        super(context);
        init();
        this.mCheckedListener=mCheckedListener;
    }

    public AcceptDepartmentView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AcceptDepartmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.jieshouren_item, this, true);
        mMsgPage =  this.findViewById(R.id.mMsgPage);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mMsgPage.setLayoutManager(manager);
        text_bumen =  this.findViewById(R.id.text_bumen);
    }
    public void setData(VAcceptDepartmentEntity t,int index) {
        this.index=index;
        userIds = new ArrayList<>();
        arr = new ArrayList<>();
        mEntity = t;
        if (t != null) {
//            if (t.type == VAcceptDepartmentEntity.TYPE_HT) {
//                text_bumen.setText(t.HTInfoEntity.nodeName);
//                String userList = t.HTInfoEntity.usersList;
//                if (!TextUtils.isEmpty(userList)) {
//                    String[] users = userList.split(",");
//                    for (int i = 0; i < users.length; i++) {
//                        userIds.add(users[i].split("\\|")[0]);
//                        VAccepterItemEntity entity = new VAccepterItemEntity();
//                        entity.name = users[i].split("\\|")[1];
//                        arr.add(entity);
//                    }
//                }
//            } else if (t.type == VAcceptDepartmentEntity.TYPE_TJ) {
//                text_bumen.setText(t.TJInfoEntity.nodeName);
//                isRadio = t.TJInfoEntity.userBLType;
//                String userList = t.TJInfoEntity.usersList;
//                if (!TextUtils.isEmpty(userList)) {
//                    String[] users = userList.split(",");
//                    for (int i = 0; i < users.length; i++) {
//                        userIds.add(users[i].split("\\|")[0]);
//                        VAccepterItemEntity entity = new VAccepterItemEntity();
//                        entity.name = users[i].split("\\|")[1];
//                        arr.add(entity);
//                    }
//                }
//            }
            for (int i = 1; i < 5; i++) {
                VAccepterItemEntity entity=new VAccepterItemEntity();
                entity.name="接收人" + i;
                arr.add(entity);
            }
            mAdapter = new AdapterAccepterItem(getContext(), arr, mListener);
            mMsgPage.setAdapter(mAdapter);
            if (!mEntity.isChecked) {
                mAdapter.clearAllChecked();
            }
        }
    }

    private IAcceptrChangeListener mListener = new IAcceptrChangeListener() {
        @Override
        public void selectedListener(int position) {
            mEntity.isChecked = true;
            mAdapter.clearOtherChecked(position);
            mCheckedListener.itemChecked(index);
        }

        @Override
        public void cancelSelectListener(int position) {
            if (!mAdapter.hasItemChecked()) {
                mEntity.isChecked = false;
            }
        }
    };
}
