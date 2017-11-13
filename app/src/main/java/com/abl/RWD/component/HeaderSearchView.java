package com.abl.RWD.component;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.abl.RWD.R;

/**
 * Created by yas on 2016/10/13.
 */
public class HeaderSearchView extends LinearLayout {
    private EditText mEdit;
    public HeaderSearchView(Context context) {
        super(context);
        init();
    }

    public HeaderSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderSearchView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.search_edittext_view, this, true);
        mEdit= (EditText) this.findViewById(R.id.edit_search);
    }
    public void addTextChangeListener(TextWatcher mListener){
        mEdit.addTextChangedListener(mListener);
    }
    public void setHint(String strHint){
        mEdit.setHint(strHint);
    }
    public void clear(){
        mEdit.setText("");
    }
}
