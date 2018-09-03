package com.zyyoona7.easypopup.easypop;

import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.easypopup.base.BaseActivity;
import com.zyyoona7.easypopup.views.TitleBar;
import com.zyyoona7.easypopup.views.TriangleDrawable;
import com.zyyoona7.popup.EasyPopup;
import com.zyyoona7.popup.MenuItem;
import com.zyyoona7.popup.XGravity;
import com.zyyoona7.popup.YGravity;

import java.util.ArrayList;
import java.util.List;

public class EasyPopActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "EasyPopActivity";

    private TitleBar mTitleBar;

    private ImageView mCircleIv;
    private Button mShowPopAboveTabBtn;
    private Button mCircleBtn;
    private Button mAboveBtn;
    private Button mRightBtn;
    private Button mBgDimBtn;
    private Button mAnyBgDimBtn;
    private Button mGiftBtn;
    private Button mCmmtBtn;
    private Button mBottomPop1Btn;
    private Button mBottomPop2Btn;
    private Button mContextPopupRightBtn;
    private Button mContextPopupLeftBtn;
    private AppCompatTextView mEverywhereTv;
    private TextView mTab1Tv;
    private TextView mTab2Tv;
    private TextView mTab3Tv;
    private TextView mTab4Tv;
    private TextView mTab5Tv;
    private TextView mCurrentTabTv;
    private TextView mAboveTv;
    private TextView mBelowTv;
    private TextView mLeftTv;
    private TextView mRightTv;
    private ContextPopup.Direction mCurrentDirection;

    private Button mBottomBtn;

    private LinearLayout mComplexBgDimView;

    private EasyPopup mWeiboPop;
    private EasyPopup mQQPop;
    private EasyPopup mCirclePop;
    private ContextPopup mContextPopup;
    private ContextPopup mContextPopupRight;
    private EasyPopup mAbovePop;
    private EasyPopup mBgDimPop;
    private EasyPopup mAnyBgDimPop;
    private GiftPopup mGiftPopup;
    private CommentPopup mCommentPopup;
    private BottomPopup mBottomPopup1;
    private BottomPopup mBottomPopup2;

    private EverywherePopup mEverywherePopup;
    private float mLastX;
    private float mLastY;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_easy_pop;
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews() {
        mTitleBar = findViewById(R.id.tb_easy);
        mTitleBar.setTile("Easy Pop");
        mCircleIv = findViewById(R.id.circle_iv);
        mShowPopAboveTabBtn = findViewById(R.id.show_pop_above_tab_btn);
        mCircleBtn = findViewById(R.id.btn_circle_comment);
        mContextPopupLeftBtn = findViewById(R.id.context_popup_left_btn);
        mContextPopupRightBtn = findViewById(R.id.context_popup_right_btn);
        mAboveBtn = findViewById(R.id.btn_above);
        mRightBtn = findViewById(R.id.btn_right);
        mBgDimBtn = findViewById(R.id.btn_bg_dim);
        mAnyBgDimBtn = findViewById(R.id.btn_bg_dim_any);
        mGiftBtn = findViewById(R.id.btn_gift);
        mCmmtBtn = findViewById(R.id.btn_pop_cmmt);
        mBottomPop1Btn = findViewById(R.id.bottom_pop1_btn);
        mBottomPop2Btn = findViewById(R.id.bottom_pop2_btn);
        mComplexBgDimView = findViewById(R.id.ll_complex_bg_dim);
        mTab1Tv = findViewById(R.id.tab1_tv);
        mTab2Tv = findViewById(R.id.tab2_tv);
        mTab3Tv = findViewById(R.id.tab3_tv);
        mTab4Tv = findViewById(R.id.tab4_tv);
        mTab5Tv = findViewById(R.id.tab5_tv);
        mCurrentTabTv = mTab1Tv;
        mAboveTv = findViewById(R.id.above_tv);
        mBelowTv = findViewById(R.id.below_tv);
        mLeftTv = findViewById(R.id.left_tv);
        mRightTv = findViewById(R.id.right_tv);
        mCurrentDirection = ContextPopup.Direction.ABOVE;
        mBottomBtn = findViewById(R.id.bottom_btn);

        mEverywhereTv = findViewById(R.id.tv_pop_everywhere);
        initQQPop();
        initWeiboPop();
        initCirclePop();
        initContextPopup();
        initContextPopupRight();
        initAbovePop();
        initBgDimPop();
        initAnyBgDimPop();
        initGiftPop();
        initCmmtPop();
        initBottomPop1();
        initBottomPop2();

        mEverywherePopup = EverywherePopup.create(this)
                .apply();

        mEverywhereTv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mLastX = event.getRawX();
                    mLastY = event.getRawY();
                    LogUtils.i("onTouch x=" + mLastX + ",y=" + mLastY);
                }
                return false;
            }
        });
        mEverywhereTv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mEverywherePopup.showEverywhere(v, (int) mLastX, (int) mLastY);
                return true;
            }
        });
    }

    @Override
    protected void initEvents() {
        mTitleBar.setOnTitleListener(new TitleBar.OnTitleListener() {
            @Override
            public void onLeftClick(View view) {
                finish();
            }

            @Override
            public void onRightClick(View view) {
                showQQPop(view);
            }

            @Override
            public void onTitleClick(View view) {
                showWeiboPop(view);
            }
        });
        mCircleIv.setOnClickListener(this);
        mShowPopAboveTabBtn.setOnClickListener(this);
        mCircleBtn.setOnClickListener(this);
        mContextPopupRightBtn.setOnClickListener(this);
        mContextPopupLeftBtn.setOnClickListener(this);
        mAboveBtn.setOnClickListener(this);
        mRightBtn.setOnClickListener(this);
        mBgDimBtn.setOnClickListener(this);
        mAnyBgDimBtn.setOnClickListener(this);
        mGiftBtn.setOnClickListener(this);
        mCmmtBtn.setOnClickListener(this);
        mBottomPop1Btn.setOnClickListener(this);
        mBottomPop2Btn.setOnClickListener(this);
        mTab1Tv.setOnClickListener(this);
        mTab2Tv.setOnClickListener(this);
        mTab3Tv.setOnClickListener(this);
        mTab4Tv.setOnClickListener(this);
        mTab5Tv.setOnClickListener(this);
        mAboveTv.setOnClickListener(this);
        mBelowTv.setOnClickListener(this);
        mLeftTv.setOnClickListener(this);
        mRightTv.setOnClickListener(this);
        mBottomBtn.setOnClickListener(this);

    }

    private void initQQPop() {
        mQQPop = EasyPopup.create()
                .setContext(this)
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.RightTop2PopAnim)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, EasyPopup basePopup) {
                        View arrowView = view.findViewById(R.id.v_arrow);
                        arrowView.setBackground(new TriangleDrawable(TriangleDrawable.TOP, Color.parseColor("#88FF88")));
                    }
                })
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .apply();

    }

    private void showQQPop(View view) {
        int offsetX = SizeUtils.dp2px(20) - view.getWidth() / 2;
        int offsetY = (mTitleBar.getHeight() - view.getHeight()) / 2;
        mQQPop.showAtAnchorView(view, YGravity.BELOW, XGravity.ALIGN_RIGHT, offsetX, offsetY);
    }

    private void initWeiboPop() {
        mWeiboPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_center_pop)
                .setAnimationStyle(R.style.TopPopAnim)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, EasyPopup basePopup) {
                        View arrowView = view.findViewById(R.id.v_arrow_weibo);
                        arrowView.setBackground(new TriangleDrawable(TriangleDrawable.TOP, Color.WHITE));
                    }
                })
                .setFocusAndOutsideEnable(true)
                .apply();
    }

    private void showWeiboPop(View view) {
        int offsetY = (mTitleBar.getHeight() - view.getHeight()) / 2;
        mWeiboPop.showAtAnchorView(view, YGravity.BELOW, XGravity.CENTER, 0, offsetY);
    }

    private void initCirclePop() {
        mCirclePop = EasyPopup.create()
                .setContentView(this, R.layout.layout_circle_comment)
                .setAnimationStyle(R.style.RightPopAnim)
                .setFocusAndOutsideEnable(true)
                .setOnViewListener(new EasyPopup.OnViewListener() {
                    @Override
                    public void initViews(View view, final EasyPopup popup) {
                        view.findViewById(R.id.tv_zan).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort("赞");
                                popup.dismiss();
                            }
                        });
                        view.findViewById(R.id.tv_comment).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ToastUtils.showShort("评论");
                                popup.dismiss();
                            }
                        });
                    }
                })
                .apply();

        mCirclePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                Log.e(TAG, "onDismiss: mCirclePop");
            }
        });
    }

    private void showCirclePop(View view) {
        mCirclePop.showAtAnchorView(view, YGravity.CENTER, XGravity.LEFT, 0, 0);
//        mCirclePop.getPopupWindow().setAnimationStyle(R.style.QQPopAnim);
    }

    private void initAbovePop() {
        mAbovePop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        Log.e(TAG, "onDismiss: mAbovePop");
                    }
                })
                .apply();
    }

    private void showAbovePop(View view) {
        mAbovePop.showAtAnchorView(view, YGravity.ABOVE, XGravity.CENTER);
    }

    private void showRightPop(View view) {
        mAbovePop.showAtAnchorView(view, YGravity.CENTER, XGravity.RIGHT);
    }

    private void initBgDimPop() {
        mBgDimPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .apply();
    }

    private void showBgDimPop(View view) {
        mBgDimPop.showAtAnchorView(view, YGravity.ALIGN_TOP, XGravity.ALIGN_LEFT);
    }

    private void initAnyBgDimPop() {
        mAnyBgDimPop = EasyPopup.create()
                .setContentView(this, R.layout.layout_any)
                .setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.4f)
                .setDimView(mTitleBar)
                .setDimColor(Color.YELLOW)
                .apply();
    }

    private void showAnyBgDimPop(View view) {
        mAnyBgDimPop.showAtAnchorView(view, YGravity.ALIGN_BOTTOM, XGravity.ALIGN_RIGHT);
    }

    private void initGiftPop() {
        mGiftPopup = GiftPopup.create()
                .setContext(this)
                .apply();
    }

    private void showGiftPop(View view) {
        mGiftPopup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initContextPopupRight() {
        List<MenuItem> list = new ArrayList<>(1);

        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("表扬表扬表扬表扬表扬")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("表扬Right");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("批评")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("批评Right");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("Test")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("Test Right");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("Test1")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("Test1 Right");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("Test2")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("Test2 Right");
                    }
                }).create());
        mContextPopupRight = ContextPopup.create(this, ContextPopup.Orientation.VERTICAL, list)
                .setDimView(mComplexBgDimView)
                .apply();
    }

    private void showContextPopupRight(View view) {
//        mContextPopupRight.showAtAnchorView(view, YGravity.CENTER, XGravity.LEFT);
//        mContextPopupRight.showAsDropDown(view);
        mContextPopupRight.showInContext(view);
    }

    private void initContextPopup() {
        List<MenuItem> list = new ArrayList<>(1);

        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("表扬")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("表扬Left");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("批评")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("批评Left");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("Test")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("Test Left");
                    }
                }).create());
        mContextPopup = ContextPopup.create(this, ContextPopup.Orientation.VERTICAL, list)
                .setAnimationStyle(R.style.RightBottomPopAnim)
                .setDimView(mComplexBgDimView)
                .apply();
    }

    private void showContextPopup(View view) {
//        mContextPopup.showAtAnchorView(view, YGravity.ABOVE, XGravity.RIGHT);
        mContextPopup.showInContext(view, mCurrentDirection, 5);
    }

    private void initBottomPop1() {
        List<MenuItem> list = new ArrayList<>(1);

        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("邻居聊天")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("邻居聊天");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("微信好友")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("微信好友");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setImageResId(R.drawable.share_wx)
                .setText("朋友圈")
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("朋友圈");
                    }
                }).create());
        mBottomPopup1 = BottomPopup.create(this, "分享到", list)
                .setDimView(mComplexBgDimView)
                .apply();
    }

    private void showBottomPop1(View view) {
//        mBottomPopup1.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
        mBottomPopup1.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initBottomPop2() {
        List<MenuItem> list = new ArrayList<>(1);
        list.add(new MenuItem.Builder()
                .setText("删除")
                .setGravity(Gravity.CENTER_HORIZONTAL)
                .setColor(Color.RED)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("删除");
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setText("分享")
                .setGravity(Gravity.CENTER_HORIZONTAL)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("分享");
                        showBottomPop1(mBottomPop1Btn);
                    }
                }).create());
        list.add(new MenuItem.Builder()
                .setText("编辑")
                .setGravity(Gravity.CENTER_HORIZONTAL)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showShort("编辑");
                    }
                }).create());
        mBottomPopup2 = BottomPopup.create(this, list)
                .setDimView(mComplexBgDimView)
                .apply();
    }

    private void showBottomPop2(View view) {
//        mBottomPopup1.showAtAnchorView(view, YGravity.ABOVE, XGravity.LEFT);
        mBottomPopup2.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    private void initCmmtPop() {
        mCommentPopup = CommentPopup.create(this)
                .setOnCancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCommentPopup.isShowing()) {
                            //无法隐藏输入法。只有toggle方法起作用...
                            KeyboardUtils.hideSoftInput(EasyPopActivity.this);
                            mCommentPopup.hideSoftInput()
                                    .dismiss();
                        }
                    }
                })
                .setOnOkClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mCommentPopup.isShowing()) {
                            //无法隐藏输入法。只有toggle方法起作用...
                            KeyboardUtils.hideSoftInput(EasyPopActivity.this);
                            mCommentPopup
                                    .dismiss();
                        }
                    }
                })
                .apply();

    }

    private void showCommentPop(View view) {
        mCommentPopup.showSoftInput()
                .showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.circle_iv:
                mContextPopup.showAtAnchorView(mCircleIv, YGravity.ABOVE, XGravity.LEFT, SizeUtils.dp2px(20), SizeUtils.dp2px(20));
                break;
            case R.id.bottom_btn:
                mContextPopup.showAtLocation(mBottomBtn, Gravity.BOTTOM, 0, SizeUtils.dp2px(30));
                break;
            case R.id.above_tv:
                mCurrentDirection = ContextPopup.Direction.ABOVE;
                break;
            case R.id.below_tv:
                mCurrentDirection = ContextPopup.Direction.BELOW;
                break;
            case R.id.left_tv:
                mCurrentDirection = ContextPopup.Direction.LEFT;
                break;
            case R.id.right_tv:
                mCurrentDirection = ContextPopup.Direction.RIGHT;
                break;
            case R.id.show_pop_above_tab_btn:
                showContextPopup(mCurrentTabTv);
                break;
            case R.id.tab1_tv:
                mCurrentTabTv = mTab1Tv;
                break;
            case R.id.tab2_tv:
                mCurrentTabTv = mTab2Tv;
                break;
            case R.id.tab3_tv:
                mCurrentTabTv = mTab3Tv;
                break;
            case R.id.tab4_tv:
                mCurrentTabTv = mTab4Tv;
                break;
            case R.id.tab5_tv:
                mCurrentTabTv = mTab5Tv;
                break;
            case R.id.context_popup_right_btn:
                showContextPopupRight(v);
                break;
            case R.id.context_popup_left_btn:
                showContextPopup(v);
                break;
            case R.id.btn_circle_comment:
                showCirclePop(v);
                break;
            case R.id.btn_above:
                showAbovePop(v);
                break;
            case R.id.btn_right:
                showRightPop(v);
                break;
            case R.id.btn_bg_dim:
                showBgDimPop(v);
                break;
            case R.id.btn_bg_dim_any:
                showAnyBgDimPop(v);
                break;
            case R.id.btn_gift:
                showGiftPop(v);
                break;
            case R.id.btn_pop_cmmt:
                showCommentPop(v);
                break;
            case R.id.bottom_pop1_btn:
                showBottomPop1(v);
                break;
            case R.id.bottom_pop2_btn:
                showBottomPop2(v);
                break;
        }
    }
}
