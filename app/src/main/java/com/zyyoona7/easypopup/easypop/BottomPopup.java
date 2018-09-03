package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;
import com.zyyoona7.popup.MenuItem;

import java.util.List;

/**
 * Created by zyyoona7 on 2017/8/4.
 */
public class BottomPopup extends BasePopup<BottomPopup> {
    private static final String TAG = "BottomPopup";

    private MenuInBottomAdapter mMenuInBottomAdapter;
    private Context mContext;
    private String mTitleText;
    private String mCancelText;
    private List<MenuItem> mMenuItemList;

    public static BottomPopup create(Context context, String titleText, List<MenuItem> list) {
        return new BottomPopup(context, titleText, list, "取消");
    }

    public static BottomPopup create(Context context, List<MenuItem> list) {
        return new BottomPopup(context, null, list, "取消");
    }

    private BottomPopup(Context context, String titleText, List<MenuItem> list, String cancelText) {
        mContext = context;
        mTitleText = titleText;
        mMenuItemList = list;
        mCancelText = cancelText;
        setContext(context);
    }


    @Override
    protected void initAttributes() {
        setContentView(R.layout.layout_bottom_popup,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        setFocusAndOutsideEnable(false)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);

    }

    @Override
    protected void initViews(View view, BottomPopup basePopup) {

        TextView titleTv = findViewById(R.id.title_tv);
        if (!TextUtils.isEmpty(mTitleText)) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(mTitleText);
        } else {
            titleTv.setVisibility(View.GONE);
        }

        TextView cancelTv = findViewById(R.id.cancel_tv);
        cancelTv.setText(mCancelText);

        RecyclerView mRecyclerView = findViewById(R.id.rv_complex);

        findViewById(R.id.cancel_tv).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                }
        );

        mMenuInBottomAdapter = new MenuInBottomAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mMenuInBottomAdapter);

        mMenuInBottomAdapter.setNewData(mMenuItemList);

        mMenuInBottomAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MenuItem menuItem = mMenuInBottomAdapter.getItem(position);
                if (menuItem != null && menuItem.getOnClickListener() != null) {
                    menuItem.getOnClickListener().onClick(view);
                }
                dismiss();
            }
        });
    }
}
