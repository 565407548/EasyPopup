package com.zyyoona7.easypopup.easypop;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.MenuItem;

public class MenuInBottomAdapter extends BaseQuickAdapter<MenuItem, BaseViewHolder> {

    public MenuInBottomAdapter() {
        super(R.layout.menu_item_in_bottom, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MenuItem item) {
        LinearLayout menuItemLl = helper.getView(R.id.menu_item_ll);
        menuItemLl.setGravity(item.getGravity());
        ImageView iv = helper.getView(R.id.iv);
        if (item.getImageResId() != 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(R.drawable.share_wx);
        } else {
            iv.setVisibility(View.GONE);
        }
        helper.setText(R.id.menu_item_tv, item.getText())
                .setTextColor(R.id.menu_item_tv, item.getColor());
    }
}
