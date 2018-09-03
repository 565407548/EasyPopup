package com.zyyoona7.easypopup.easypop;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.zyyoona7.easypopup.R;
import com.zyyoona7.popup.BasePopup;
import com.zyyoona7.popup.MenuItem;

import java.util.List;

/**
 * Created by zyyoona7 on 2017/8/4.
 */
public class ContextPopup extends BasePopup<ContextPopup> {
    private static final String TAG = "ContextPopup";
    private final static int OFFSET_IN_DP = 10;

    private Context mContext;
    private List<MenuItem> mMenuItemList;
    private Orientation mOrientation;
    private LinearLayout mLinearLayout;

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public enum Direction {
        ABOVE,
        BELOW,
        LEFT,
        RIGHT
    }

    public static ContextPopup create(Context context, Orientation orientation, List<MenuItem> list) {
        return new ContextPopup(context, orientation, list);
    }

    private ContextPopup(Context context, Orientation orientation, List<MenuItem> list) {
        mContext = context;
        mOrientation = orientation;
        mMenuItemList = list;
        setContext(context);
    }


    @Override
    protected void initAttributes() {
        if (Orientation.HORIZONTAL == mOrientation) {
            setContentView(R.layout.layout_horizontal_context_popup,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            setContentView(R.layout.layout_vertical_context_popup,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        setFocusAndOutsideEnable(true)
                .setBackgroundDimEnable(true)
                .setDimValue(0.5f);

    }

    @Override
    protected void initViews(View view, ContextPopup basePopup) {
        mLinearLayout = findViewById(R.id.context_popup_ll);

        for (final MenuItem menuItem : mMenuItemList) {
            View menuItemView = LayoutInflater.from(mContext).inflate(R.layout.menu_item_in_context, null);
            ImageView iv = menuItemView.findViewById(R.id.iv);
            TextView tv = menuItemView.findViewById(R.id.tv);
            if (menuItem.getImageResId() > 0) {
                iv.setImageResource(menuItem.getImageResId());
                iv.setVisibility(View.VISIBLE);
            } else {
                iv.setVisibility(View.GONE);
            }
            tv.setText(menuItem.getText());
            menuItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuItem.getOnClickListener().onClick(v);
                }
            });

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
//            menuItemView.setLayoutParams(params);
            mLinearLayout.addView(menuItemView, params);
        }
    }

    /**
     * 如果没有显示过, getHeight, getWidth都是0, 所以先 INVISIBLE显示一次,获得正常的 height, width
     */
    public ContextPopup showInContext(final View anchorView) {
        if (!isRealWHAlready()) {
            mLinearLayout.setVisibility(View.INVISIBLE);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0);
            AppUtils.runOnUIDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                            showInternalInContext(anchorView);
                        }
                    },
                    100);
        } else {
            showInternalInContext(anchorView);
        }
        return this;
    }

    private void showInternalInContext(View anchorView) {
//        int screenHeight = ScreenUtils.getScreenHeight();
        int offsetX;
        int offsetY;
        int width = getWidth();
        int height = getHeight();
        Point point1, point2, point3, point4, point5, point6, point7;

        mLinearLayout.setVisibility(View.VISIBLE);
        int screenWidth = ScreenUtils.getScreenWidth();
        Rect rect = new Rect();
        anchorView.getGlobalVisibleRect(rect);
        int centerX = (rect.left + rect.right) / 2;
        if (centerX < getWidth() / 2) {
            offsetX = 0;
        } else if (centerX + getWidth() / 2 > screenWidth) {
            offsetX = screenWidth - getWidth();
        } else {
            offsetX = centerX - getWidth() / 2;
        }
        int overlayLeft = rect.left > offsetX ? rect.left : offsetX;
        int overlayRight = rect.right < offsetX + getWidth() ? rect.right : offsetX + getWidth();
        int overlayCenterX = (overlayLeft + overlayRight) / 2;

        if (rect.top > getHeight()) {
            offsetY = rect.top - getHeight();

            point1 = new Point(offsetX, offsetY);
            point2 = new Point(offsetX + getWidth(), offsetY);
            point3 = new Point(offsetX + getWidth(), offsetY + getHeight() - SizeUtils.dp2px(OFFSET_IN_DP));
            point4 = new Point(overlayCenterX + SizeUtils.dp2px(OFFSET_IN_DP), offsetY + getHeight() - SizeUtils.dp2px(OFFSET_IN_DP));
            point5 = new Point(overlayCenterX, offsetY + getHeight());
            point6 = new Point(overlayCenterX - SizeUtils.dp2px(OFFSET_IN_DP), offsetY + getHeight() - SizeUtils.dp2px(OFFSET_IN_DP));
            point7 = new Point(offsetX, offsetY + getHeight() - SizeUtils.dp2px(OFFSET_IN_DP));

            getPopupWindow().setAnimationStyle(R.style.TopPopAnim);
        } else {
            offsetY = rect.bottom;

            point1 = new Point(offsetX, offsetY + SizeUtils.dp2px(OFFSET_IN_DP));
            point2 = new Point(overlayCenterX - SizeUtils.dp2px(OFFSET_IN_DP), offsetY + SizeUtils.dp2px(OFFSET_IN_DP));
            point3 = new Point(overlayCenterX, offsetY);
            point4 = new Point(overlayCenterX + SizeUtils.dp2px(OFFSET_IN_DP), offsetY + SizeUtils.dp2px(OFFSET_IN_DP));
            point5 = new Point(offsetX + getWidth(), offsetY + SizeUtils.dp2px(OFFSET_IN_DP));
            point6 = new Point(offsetX + getWidth(), offsetY + getHeight());
            point7 = new Point(offsetX, offsetY + getHeight());

            getPopupWindow().setAnimationStyle(R.style.BottomPopAnim);
        }

        Drawable drawable = new BitmapDrawable(
                anchorView.getResources(),
                createBackgroundBitmapInContext(width, height,
                        offsetInContext(point1, offsetX, offsetY),
                        offsetInContext(point2, offsetX, offsetY),
                        offsetInContext(point3, offsetX, offsetY),
                        offsetInContext(point4, offsetX, offsetY),
                        offsetInContext(point5, offsetX, offsetY),
                        offsetInContext(point6, offsetX, offsetY),
                        offsetInContext(point7, offsetX, offsetY)));

        mLinearLayout.setBackground(drawable);

        showAtLocation(anchorView, Gravity.NO_GRAVITY, offsetX, offsetY);
    }

    private Point offsetInContext(Point point, int startX, int startY) {
        return new Point(point.x - startX, point.y - startY);
    }

    private Bitmap createBackgroundBitmapInContext(int width, int height,
                                                   Point point1, Point point2,
                                                   Point point3, Point point4,
                                                   Point point5, Point point6,
                                                   Point point7) {
        Bitmap bm = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bm);

        Paint paint = new Paint();
        paint.setColor(Color.RED);

        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.lineTo(point5.x, point5.y);
        path.lineTo(point6.x, point6.y);
        path.lineTo(point7.x, point7.y);
        path.close();

        canvas.drawPath(path, paint);

        return bm;
    }

    public ContextPopup showInContext(final View anchorView, final Direction direction, final int marginInDp) {
        if (!isRealWHAlready()) {
            mLinearLayout.setVisibility(View.INVISIBLE);
            showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0);
            AppUtils.runOnUIDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                            showInternalInContext(anchorView, direction, marginInDp);
                        }
                    },
                    100);
        } else {
            showInternalInContext(anchorView, direction, marginInDp);
        }
        return this;
    }

    private void showInternalInContext(View anchorView, final Direction direction, int marginInDp) {
        int offsetX;
        int offsetY;
        mLinearLayout.setVisibility(View.VISIBLE);
        Rect rect = new Rect();
        anchorView.getGlobalVisibleRect(rect);
        int centerX = (rect.left + rect.right) / 2;
        int centerY = (rect.top + rect.bottom) / 2;
        if (direction == Direction.ABOVE) {
            offsetX = centerX - getWidth() / 2;
            offsetY = rect.top - getHeight() - SizeUtils.dp2px(marginInDp);
            getPopupWindow().setAnimationStyle(R.style.TopPopAnim);
        } else if (direction == Direction.BELOW) {
            offsetX = centerX - getWidth() / 2;
            offsetY = rect.bottom + SizeUtils.dp2px(marginInDp);
        } else if (direction == Direction.LEFT) {
            offsetX = rect.left - getWidth() - SizeUtils.dp2px(marginInDp);
            offsetY = centerY - getHeight() / 2;
        } else {
            offsetX = rect.right + SizeUtils.dp2px(marginInDp);
            offsetY = centerY - getHeight() / 2;
        }
        showAtLocation(anchorView, Gravity.NO_GRAVITY, offsetX, offsetY);
    }
}
