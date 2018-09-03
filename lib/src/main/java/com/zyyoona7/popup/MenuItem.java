package com.zyyoona7.popup;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

/**
 * Author: zcj
 * Date: 18-8-29
 * Email: zhengcj01@vanke.com
 */
public class MenuItem {
    private int mImageResId;
    private String mText;
    private View.OnClickListener mOnClickListener;
    private int mColor;
    private int mGravity;

    private MenuItem() {
    }

    public int getImageResId() {
        return mImageResId;
    }

    public String getText() {
        return mText;
    }

    public View.OnClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public int getColor() {
        if (mColor == 0) {
            return Color.BLACK;
        } else {
            return mColor;
        }
    }

    public int getGravity() {
        if (mGravity == 0) {
            return Gravity.START;
        } else {
            return mGravity;
        }
    }

    public static class Builder {
        private int mImageResId;
        private String mText;
        private View.OnClickListener mOnClickListener;
        private int mColor;
        private int mGravity;

        public Builder setImageResId(int imageResId) {
            mImageResId = imageResId;
            return this;
        }

        public int getImageResId() {
            return mImageResId;
        }

        public Builder setText(String text) {
            mText = text;
            return this;
        }

        public Builder setOnClickListener(View.OnClickListener onClickListener) {
            mOnClickListener = onClickListener;
            return this;
        }

        public Builder setColor(int color) {
            mColor = color;
            return this;
        }

        public Builder setGravity(int gravity) {
            mGravity = gravity;
            return this;
        }

        public MenuItem create() {
            MenuItem menuItem = new MenuItem();
            menuItem.mImageResId = mImageResId;
            menuItem.mText = mText;
            menuItem.mOnClickListener = mOnClickListener;
            menuItem.mColor = mColor;
            menuItem.mGravity = mGravity;
            return menuItem;
        }
    }
}