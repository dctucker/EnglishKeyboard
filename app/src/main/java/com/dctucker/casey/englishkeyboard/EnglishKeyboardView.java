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

    // from LatinKeyboardView in HackerKeyboard
    //
    // The keycode list needs to stay in sync with the
    // res/values/keycodes.xml file.

    // FIXME: The following keycodes should really be renumbered
    // since they conflict with existing KeyEvent keycodes.
    static final int KEYCODE_OPTIONS = -100;
    static final int KEYCODE_OPTIONS_LONGPRESS = -101;
    static final int KEYCODE_VOICE = -102;
    static final int KEYCODE_F1 = -103;
    static final int KEYCODE_NEXT_LANGUAGE = -104;
    static final int KEYCODE_PREV_LANGUAGE = -105;
    static final int KEYCODE_COMPOSE = -10024;

    // The following keycodes match (negative) KeyEvent keycodes.
    // Would be better to use the real KeyEvent values, but many
    // don't exist prior to the Honeycomb API (level 11).
    static final int KEYCODE_DPAD_UP = -19;
    static final int KEYCODE_DPAD_DOWN = -20;
    static final int KEYCODE_DPAD_LEFT = -21;
    static final int KEYCODE_DPAD_RIGHT = -22;
    static final int KEYCODE_DPAD_CENTER = -23;
    static final int KEYCODE_ALT_LEFT = -57;
    static final int KEYCODE_PAGE_UP = -92;
    static final int KEYCODE_PAGE_DOWN = -93;
    static final int KEYCODE_ESCAPE = -111;
    static final int KEYCODE_FORWARD_DEL = -112;
    static final int KEYCODE_CTRL_LEFT = -113;
    static final int KEYCODE_CAPS_LOCK = -115;
    static final int KEYCODE_SCROLL_LOCK = -116;
    static final int KEYCODE_META_LEFT = -117;
    static final int KEYCODE_FN = -119;
    static final int KEYCODE_SYSRQ = -120;
    static final int KEYCODE_BREAK = -121;
    static final int KEYCODE_HOME = -122;
    static final int KEYCODE_END = -123;
    static final int KEYCODE_INSERT = -124;
    static final int KEYCODE_FKEY_F1 = -131;
    static final int KEYCODE_FKEY_F2 = -132;
    static final int KEYCODE_FKEY_F3 = -133;
    static final int KEYCODE_FKEY_F4 = -134;
    static final int KEYCODE_FKEY_F5 = -135;
    static final int KEYCODE_FKEY_F6 = -136;
    static final int KEYCODE_FKEY_F7 = -137;
    static final int KEYCODE_FKEY_F8 = -138;
    static final int KEYCODE_FKEY_F9 = -139;
    static final int KEYCODE_FKEY_F10 = -140;
    static final int KEYCODE_FKEY_F11 = -141;
    static final int KEYCODE_FKEY_F12 = -142;
    static final int KEYCODE_NUM_LOCK = -143;


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
            if(key.popupCharacters != null && key.popupCharacters.length() > 0) {
                String label = key.popupCharacters.subSequence(0,1).toString();
                canvas.drawText(label, key.x + (key.width / 2), key.y + 25, paint);
            }
        }
    }
}
