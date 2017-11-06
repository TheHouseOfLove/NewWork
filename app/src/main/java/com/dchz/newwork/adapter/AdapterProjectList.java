package com.dchz.newwork.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.dchz.newwork.R;
import com.dchz.newwork.adapter.base.BaseRecyclerAdapter;
import com.dchz.newwork.entity.PProjectItemEntity;
import com.dchz.newwork.msglist.item.ProjectItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/11/6.
 */

public class AdapterProjectList extends BaseRecyclerAdapter<ProjectItemHolder,PProjectItemEntity>{
    public AdapterProjectList(Context mContext, ArrayList<PProjectItemEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected ProjectItemHolder getViewHolder(ViewGroup parent) {
        return new ProjectItemHolder(mContext,parent, R.layout.search_project_itemview);
    }
}
