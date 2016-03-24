package com.example.administrator.contactdemo.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 让其内部判断其一直获取焦点就能实现跑马灯效果
 */
public class MarqueeTextView extends TextView {

        public MarqueeTextView(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        @Override
        protected void onFocusChanged(boolean focused, int direction,
                                      Rect previouslyFocusedRect) {
            if (focused)
                super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }

        @Override
        public void onWindowFocusChanged(boolean focused) {
            if (focused)
                super.onWindowFocusChanged(focused);
        }

        @Override
        public boolean isFocused() {
            return true;//一直返回true，假装这个控件一直获取着焦点
        }
}
