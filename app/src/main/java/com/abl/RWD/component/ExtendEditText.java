package com.abl.RWD.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.abl.common.util.KeyBoardUtils;
import com.abl.RWD.R;
import com.abl.RWD.common.Global;


/***
 * 带清除功能的EditText
 * @author yas
 *
 */
public class ExtendEditText extends LinearLayout implements OnClickListener, TextWatcher {
    private final String TAG = "ExtendEditText";
    private EditText text;
    private ImageView img;
    private boolean isEmpty;
    private ITextListener mTxtListener;
    private LinearLayout mLinear;
    private ImageView imgIcon;

    public ExtendEditText(Context paramContext) {
        super(paramContext);
        init();
    }

    public ExtendEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramAttributeSet);
    }

    private void init() {
        init(null);
    }

    private void init(AttributeSet attrs) {
        // TODO Auto-generated method stub
        LayoutInflater li = (LayoutInflater) Global.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.extend_edittext, this, true);

        this.imgIcon = this.findViewById(R.id.img_icon);
        this.mLinear = this.findViewById(R.id.linear_main);
        this.text = this.findViewById(R.id.edit);
        this.text.setHintTextColor(Global.mContext.getResources().getColor(R.color.comment_text_hint));
        this.text.addTextChangedListener(this);
        this.img = this.findViewById(R.id.img);
        this.img.setOnClickListener(this);

        if (attrs != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ExtendEditText);
            if (typedArray != null) {
                int resId = typedArray.getResourceId(R.styleable.ExtendEditText_icon, 0);
                if (resId != 0) {
                    imgIcon.setVisibility(View.VISIBLE);
                    imgIcon.setImageResource(resId);
                }
                String hint = typedArray.getString(R.styleable.ExtendEditText_hint);
                text.setHint(hint);
            }
        }
        text.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件
                    KeyBoardUtils.hideSoftKeyBroad(getContext(), text);
                    return true;
                }
                return false;
            }
        });
    }

    public void setBGDrawable(Drawable d) {
        this.mLinear.setBackgroundDrawable(d);
        this.text.setBackgroundDrawable(null);
    }

    public void setTxtChangeListener(ITextListener mListener) {
        this.mTxtListener = mListener;
    }

    public String getText() {
        String str = text.getText().toString();
        return str;
    }

    public void setText(String str) {
        text.setText(str);
    }

    public void setHint(String str) {
        text.setHint(str);
    }

    public void setInputType(int type) {
        text.setInputType(type);
    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        this.text.setText("");
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    @Override
    public void afterTextChanged(Editable arg0) {
        // TODO Auto-generated method stub
        if (mTxtListener != null) {
            String str = getText().toString();
            if (TextUtils.isEmpty(str)) {
                mTxtListener.onTxtState(true);
            } else {
                mTxtListener.onTxtState(false);
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        String str = text.getText().toString();
        if (!TextUtils.isEmpty(str)) {
            isEmpty = false;
            img.setVisibility(View.VISIBLE);
        } else {
            isEmpty = true;
            img.setVisibility(View.GONE);
        }
    }

    /***
     * 获取编辑输入控件
     * @return
     */
    public EditText getEditTxt() {
        return this.text;
    }

    public void setMaxLength(int max) {
        text.setFilters(new InputFilter[]{new InputFilter.LengthFilter(max)});
    }

    public interface ITextListener {

        /**
         * Txt文本是否为空
         *
         * @param isEmpty
         */
        void onTxtState(boolean isEmpty);

    }

    public void setIcon(int resId) {
        if (resId > 0)
            imgIcon.setImageResource(resId);
    }
}