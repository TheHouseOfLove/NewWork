package com.abl.RWD.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


import com.abl.RWD.listener.OnItemClickListener;
import com.abl.RWD.msglist.base.BaseViewHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/7/19.
 */

public abstract class BaseRecyclerAdapter<VM extends BaseViewHolder,T>extends RecyclerView.Adapter implements View.OnClickListener {
    protected Context mContext;
    private ArrayList<T> mList;
    private OnItemClickListener mItemListener;
    public BaseRecyclerAdapter(Context mContext, ArrayList<T> mList) {
        this.mContext=mContext;
        this.mList = mList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        BaseViewHolder baseViewHolder= (BaseViewHolder) holder;
        VM vm= (VM) holder;
        if (vm!=null){
            vm.setPos(position);
            vm.setSize(getItemCount());
            vm.initView();
            vm.setMsg(mList.get(position));
            vm.itemView.setOnClickListener(this);
            vm.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }


    public void appendList(ArrayList<T> mList){
        if(this.mList == null){
            this.mList = mList;
        }else{
            this.mList.addAll(mList);
        }
        notifyDataSetChanged();
    }

    public void reSetList(ArrayList<T> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public T getItemEntity(int pos){
        if (mList!=null&&mList.size()>pos){
            return mList.get(pos);
        }
        return null;
    }
    public ArrayList<T> getList(){
        return mList;
    }
    protected abstract VM getViewHolder(ViewGroup parent);
    public void setOnItemClickListener(OnItemClickListener mItemListener){
        this.mItemListener=mItemListener;
    }

    @Override
    public void onClick(View view) {
        if (mItemListener!=null){
            int position = (int) view.getTag();
            if (position >= 0 && position <= mList.size() - 1)
                mItemListener.onItemClick(view, position);
        }
    }
}
