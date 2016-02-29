package com.exarlabs.android.com.tmlparseandroiddemo.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.DictionaryManager;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.TmlMocker;
import com.exarlabs.android.com.tmlparseandroiddemo.model.dictionary.DictionaryTuple;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.translator.TranslatorEditorDialogFragment;
import com.parse.FindCallback;

/**
 * Custom text view for translations
 * Created by becze on 2/26/2016.
 */
public class TmlTextView extends TextView implements View.OnClickListener {
    private Drawable mBackground;

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    private static final String TAG = TmlTextView.class.getSimpleName();


    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    private String mKey;
    private DictionaryManager mDictionaryManager = DictionaryManager.getInstance();
    private FindCallback<DictionaryTuple> mFindCallback;
    private String mTextValue;
    private BufferType mBufferType;
    private BroadcastReceiver mBroadcastReceiver;

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    public TmlTextView(Context context) {
        super(context);
        initUI();
    }

    public TmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initUI();
    }

    public TmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUI();
    }

    @TargetApi (Build.VERSION_CODES.LOLLIPOP)
    public TmlTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initUI();
    }

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------
    private void initUI() {

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                updateText();
            }
        };

        // Start to listen to the configuration change
        IntentFilter filter = new IntentFilter();
        filter.addAction(TmlMocker.Message.CONFIG_CHANGED);
        getContext().registerReceiver(mBroadcastReceiver, filter);
        mBackground = getBackground();
        setOnClickListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getContext().unregisterReceiver(mBroadcastReceiver);
    }

    /**
     * Resets the text view enabling to add a new key
     */
    public void reset() {
        mKey = "";
    }

    @Override
    public void setText(final CharSequence text, BufferType type) {
        super.setText(text, type);
        if (mDictionaryManager == null) {
            mDictionaryManager = DictionaryManager.getInstance();
        }

        // We query the local datastore first if we have a translation for it first
        Log.w("BSZ", "setText: " + text);

        mKey = text.toString();
        mTextValue = text.toString();
        mBufferType = type;

        updateText();
    }


    /**
     * It check if there is a translation for it and if not then it just simply skips and adds the default text.
     * Note: if the default translation does not exist then we just add it for now.
     */
    private void updateText() {

        if (!TextUtils.isEmpty(mKey) && !TextUtils.isEmpty(mTextValue)) {

            boolean mTranslationAvailable = mDictionaryManager.hasTranslation(TmlMocker.getSelectedLanguage().mCode, mKey);

            // If we are in default mode and the string does not exist we just add it to our translation database
            if (TmlMocker.getSelectedLanguage() == TmlMocker.getDefaultLanguage() && !mTranslationAvailable) {
                // add as a new translation
                mDictionaryManager.addNewTranslation(TmlMocker.getDefaultLanguage(), mKey, mTextValue);
            }
            // In Translate mode we highlight the view
            if (TmlMocker.getSelecetdMode() == TmlMocker.Mode.TRANSLATE) {
                setBackgroundColor(mTranslationAvailable ? getResources().getColor(R.color.text_translate_mode_translated) : getResources().getColor(
                                R.color.text_translate_mode_untranslated));
            } else {
                setBackground(mBackground);
            }


            // Just update the text with the translations
            String languageCode = mTranslationAvailable ? TmlMocker.getSelectedLanguage().mCode : TmlMocker.getDefaultLanguage().mCode;
            TmlTextView.super.setText(mDictionaryManager.getTranslation(languageCode, mKey), mBufferType);
        }
    }


    @Override
    public void onClick(View v) {
        if (TmlMocker.getSelecetdMode() == TmlMocker.Mode.TRANSLATE) {
            if (getContext() instanceof Activity) {
                // In translation mode we open a new editor fragment
                TranslatorEditorDialogFragment fragment = TranslatorEditorDialogFragment.newInstance(mKey);
                fragment.show(((Activity) getContext()).getFragmentManager(), this.getClass().getSimpleName());
            }
        }
    }


    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
