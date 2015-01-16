package com.dctucker.casey.englishkeyboard;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.Keyboard;
import android.media.AudioManager;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;

import com.dctucker.casey.englishkeyboard.EnglishKeyboardView;

/**
 * Created by casey on 2015-01-09.
 */
public class EnglishIME extends InputMethodService
    implements KeyboardView.OnKeyboardActionListener {

    //private KeyboardView kv;

    private KeyboardView kv;
    //private EnglishKeyboardView ekv;
    private Keyboard keyboard;

    private boolean caps=false;

    @Override
    public void onKey(int primaryCode, int[] keyCodes){
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);

        switch(primaryCode) {
            case Keyboard.KEYCODE_DELETE:
                ic.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                if( caps )
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
                else
                    ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));

                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                doKeypress(primaryCode);
                //this.sendDownUpKeyEvents(code);
                //ic.commitText(String.valueOf(code),1);
                /*
                EditorInfo ei = getCurrentInputEditorInfo();
                int shift = ic.getCursorCapsMode(Integer.MAX_VALUE);
                if( shift > 0 ) {
                    caps = true;
                    keyboard.setShifted(caps);
                    kv.invalidateAllKeys();
                }
                */
        }

    }

    private void doKeypress(int primaryCode) {
        InputConnection ic = getCurrentInputConnection();
        long eventTime = SystemClock.uptimeMillis();
        boolean shift = caps;
        //if( primaryCode == 67 ) // backspace
        //    shift = false;

        char code = (char) primaryCode;
        //if( shift ) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
        ic.sendKeyEvent(new KeyEvent( eventTime, eventTime, KeyEvent.ACTION_DOWN, code, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD|KeyEvent.FLAG_KEEP_TOUCH_MODE));
        ic.sendKeyEvent(new KeyEvent( SystemClock.uptimeMillis(), eventTime, KeyEvent.ACTION_UP, code, 0, 0, 0, 0, KeyEvent.FLAG_SOFT_KEYBOARD|KeyEvent.FLAG_KEEP_TOUCH_MODE));
        //if( shift ) ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));

    }

    @Override
    public void onPress(int primaryCode){}
    @Override
    public void onRelease(int primaryCode){}
    @Override
    public void onText(CharSequence text){}
    @Override
    public void swipeDown(){}

    @Override
    public void swipeUp(){}

    @Override
    public void swipeLeft(){}

    @Override
    public void swipeRight(){}

    public View onCreateInputView(){
        kv = (EnglishKeyboardView) getLayoutInflater().inflate(R.layout.input, null);
        //ekv = new EnglishKeyboardView(kv);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    private void playClick(int keyCode) {
        AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);
        am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
    }
}
