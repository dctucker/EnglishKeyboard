package com.dctucker.casey.englishkeyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

class EnglishKeyboardView extends KeyboardView {
    public EnglishKeyboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EnglishKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(25);
        paint.setColor(Color.RED);

        List<Keyboard.Key> keys = getKeyboard().getKeys();
        for(Keyboard.Key key: keys) {
            if(key.label != null)
                canvas.drawText(key.label.toString(), key.x + (key.width/2), key.y + 25, paint);
        }
    }
}
