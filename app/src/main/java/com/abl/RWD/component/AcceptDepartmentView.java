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
import com.abl.RWD.entity.PReferInfoItemEntity;
import com.abl.RWD.entity.PReturnInfoItemEntity;
import com.abl.RWD.entity.VAcceptDepartmentEntity;
import com.abl.RWD.entity.VAccepterItemEntity;
import com.abl.RWD.listener.IAcceptrChangeListener;
import com.abl.RWD.listener.IItemCheckedListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private int index;
//    private Map<Integer,String> mapName=new HashMap<>();
//    private Map<Integer,String> mapId=new HashMap<>();
//    private String name="";
//    private String id="";
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
    public void setReferInfo(PReferInfoItemEntity t, int index){
        this.index=index;
        userIds = new ArrayList<>();
        arr = new ArrayList<>();
        if(t!=null){
            text_bumen.setText(t.nodeName);
            isRadio = t.userBLType;
            String userList = t.usersList;
            if (!TextUtils.isEmpty(userList)) {
                String[] users = userList.split(",");
                for (int i = 0; i < users.length; i++) {
                    userIds.add(users[i].split("\\|")[0]);
                    VAccepterItemEntity entity = new VAccepterItemEntity();
                    entity.name = users[i].split("\\|")[1];
                    arr.add(entity);
                }
                mAdapter = new AdapterAccepterItem(getContext(), arr, mListener);
                mMsgPage.setAdapter(mAdapter);
            }
        }
    }
    public void setReturnInfo(PReturnInfoItemEntity t, int index) {
        this.index=index;
        userIds = new ArrayList<>();
        arr = new ArrayList<>();
        if (t != null) {
                text_bumen.setText(t.nodeName);
                String userList = t.usersList;
                if (!TextUtils.isEmpty(userList)) {
                    String[] users = userList.split(",");
                    for (int i = 0; i < users.length; i++) {
                        userIds.add(users[i].split("\\|")[0]);
                        VAccepterItemEntity entity = new VAccepterItemEntity();
                        entity.name = users[i].split("\\|")[1];
                        arr.add(entity);
                    }
            }
            mAdapter = new AdapterAccepterItem(getContext(), arr, mListener);
            mMsgPage.setAdapter(mAdapter);
        }
    }

    private IAcceptrChangeListener mListener = new IAcceptrChangeListener() {
        @Override
        public void selectedListener(int position) {
//            mAdapter.clearOtherChecked(position);
//            mCheckedListener.itemChecked(index);
            if (mCheckedListener!=null){
                if ("radio".equals(isRadio)){
                    mCheckedListener.itemChecked(index);
                    mAdapter.clearOtherChecked(position);
                }else{

                }
            }
        }

        @Override
        public void cancelSelectListener(int position) {
        }
    };
    public void cancelChecked(){
        mAdapter.clearAllChecked();
    }
}
