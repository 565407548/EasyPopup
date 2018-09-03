package com.zyyoona7.easypopup;

import android.view.View;
import android.widget.Button;

import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.basic.BasicActivity;
import com.zyyoona7.easypopup.easypop.EasyPopActivity;
import com.zyyoona7.easypopup.easypop.RecyclerViewActivity;
import com.zyyoona7.easypopup.easypop.VideoAct;

public class MainActivity extends BaseActivity {

    //使用场景 QQ+号，直播礼物弹窗，微信朋友圈评论

    private Button mBasicBtn;

    private Button mEasyBtn;

    private Button mRvBtn;

    private Button mVideoBtn;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mBasicBtn =  findViewById(R.id.btn_basic);
        mEasyBtn =  findViewById(R.id.btn_easy);
        mRvBtn =  findViewById(R.id.btn_recycler);
        mVideoBtn = findViewById(R.id.video_btn);
    }

    @Override
    protected void initEvents() {
        mBasicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(BasicActivity.class);
            }
        });

        mEasyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(EasyPopActivity.class);
            }
        });

        mRvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(RecyclerViewActivity.class);
            }
        });

        mVideoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goTo(VideoAct.class);
            }
        });
    }
}
