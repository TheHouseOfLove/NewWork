package com.dchz.newwork.component;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dchz.newwork.R;
import com.dchz.newwork.listener.IBtnClickListener;


/**
 * Created by yas on 2017/3/28.
 * activity头部
 */
public class CommonHeaderView extends RelativeLayout implements View.OnClickListener {
    public static final int TYPE_EMPTY=0;                           //什么都不显示的类型
    public static final int TYPE_ONLY_TITLE=1;                      //只有中间文字title的类型
    public static final int TYPE_ONLY_LEFT_IMAGE=2;                 //左侧图片，右侧无
    public static final int TYPE_ONLY_LEFT_TEXT=3;                  //左侧文字，右侧无
    public static final int TYPE_LEFT_IMAGE_AND_RIGHT_IMAGE=4;      //左右都为图片类型
    public static final int TYPE_LEFT_TEXT_AND_RIGHT_IMAGE=5;       //左侧文字，右侧图片类型
    public static final int TYPE_LEFT_IMAGE_AND_RIGHT_TEXT=6;       //左侧图片，右侧文字类型
    public static final int TYPE_LEFT_TEXT_AND_RIGHT_TEXT=7;        //左右都为文本的类型


    private RelativeLayout relaLeft;   //左侧区域
    private ImageView imgLeft;          //左侧图片
    private TextView txtLeft;           //左侧文本

    private RelativeLayout relaRight;  //右侧区域
    private ImageView imgRight;         //右侧图片
    private TextView txtRight;          //右侧文本

    private TextView txtTitle;         //title
    private TextView txtRedNum;         //红色消息数量
    private IBtnClickListener mListener; //点击事件监听
    public CommonHeaderView(Context context) {
        super(context);
        init();
    }

    /**
     * 设置headerView的显示类型
     * @param type
     */
    public void updateType(int type){
        switch (type){
            case TYPE_ONLY_TITLE:
                this.relaRight.setVisibility(View.GONE);
                this.relaLeft.setVisibility(View.GONE);
                break;
            case TYPE_ONLY_LEFT_IMAGE:
                this.relaRight.setVisibility(View.GONE);
                this.txtLeft.setVisibility(View.GONE);
                break;
            case TYPE_ONLY_LEFT_TEXT:
                this.relaRight.setVisibility(View.GONE);
                this.imgLeft.setVisibility(View.GONE);
                break;
            case TYPE_LEFT_IMAGE_AND_RIGHT_IMAGE:
                this.txtLeft.setVisibility(View.GONE);
                this.txtRight.setVisibility(View.GONE);
                break;
            case TYPE_LEFT_TEXT_AND_RIGHT_IMAGE:
                this.imgLeft.setVisibility(View.GONE);
                this.txtRight.setVisibility(View.GONE);
                break;
            case TYPE_LEFT_IMAGE_AND_RIGHT_TEXT:
                this.txtLeft.setVisibility(View.GONE);
                this.imgRight.setVisibility(View.GONE);
                break;
            case TYPE_LEFT_TEXT_AND_RIGHT_TEXT:
                this.imgLeft.setVisibility(View.GONE);
                this.imgRight.setVisibility(View.GONE);
                break;
            default:
                this.relaRight.setVisibility(View.GONE);
                this.relaLeft.setVisibility(View.GONE);
                this.txtTitle.setVisibility(View.GONE);
                break;
        }
    }
    public CommonHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.common_header_view,this,true);
        this.relaLeft= (RelativeLayout) this.findViewById(R.id.rela_left);
        this.imgLeft= (ImageView) this.findViewById(R.id.img_back);
        this.txtLeft= (TextView) this.findViewById(R.id.text_back);

        this.relaRight= (RelativeLayout) this.findViewById(R.id.rela_right);
        this.imgRight= (ImageView) this.findViewById(R.id.img_header_right);
        this.txtRight= (TextView) this.findViewById(R.id.textView_header_right);

        this.txtTitle= (TextView) this.findViewById(R.id.title);
        this.txtRedNum= (TextView) this.findViewById(R.id.txt_rednum);

        this.relaLeft.setOnClickListener(this);
        this.relaRight.setOnClickListener(this);

    }

    public void setHeaderClickListener(IBtnClickListener mListener){
        this.mListener=mListener;
    }

    /**
     * 设置title
     * @param title
     */
    public void setTitle(String title){
        this.txtTitle.setText(title);
    }

    /**
     * 设置左侧文字
     * @param leftText
     */
    public void setLeftText(String leftText){
        this.txtLeft.setText(leftText);
    }

    /**
     * 设置右侧文字
     * @param rightText
     */
    public void setRightText(String rightText){
        this.txtRight.setText(rightText);
    }

    /**
     * 设置左侧img
     * @param leftImage
     */
    public void setLeftImage(Drawable leftImage){
        this.imgLeft.setImageDrawable(leftImage);
    }

    /**
     * 设置右侧img
     * @param rightImage
     */
    public void setRightImage(Drawable rightImage){
        this.imgRight.setImageDrawable(rightImage);
    }

    /**
     * 设置
     * @param num
     */
    public void setSysNum(int num){
        if (num>0) {
            txtRedNum.setVisibility(View.VISIBLE);
            txtRedNum.setText(num + "");
        }
    }
    @Override
    public void onClick(View v) {
        if (mListener!=null){
            if (v==relaLeft){
                mListener.btnLeftClick();
            }else if (v==relaRight){
                mListener.btnRightClick();
            }
        }
    }
}
