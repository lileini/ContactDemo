package com.example.administrator.contactdemo.ui.widget;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/4/14.
 */
public class MyEditText extends EditText{
    public MyEditText(Context context) {
        super(context);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.length() == 4){
            setText(text+"");
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);

    }
}
