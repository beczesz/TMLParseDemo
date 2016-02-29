package com.exarlabs.android.com.tmlparseandroiddemo.business.tml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.content.Intent;

/**
 * Created by becze on 2/26/2016.
 */
public class TmlMocker {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    public enum Mode {
        NORMAL, TRANSLATE
    }

    public enum Language {
        ENGLISH("en", "English", "https://cdn1.iconfinder.com/data/icons/famfamfam_flag_icons/us.png"),
        RUSSIAN("ru", "Russian", "https://cdn1.iconfinder.com/data/icons/famfamfam_flag_icons/ru.png"),
        CHINESE("cn", "Chinese", "https://cdn1.iconfinder.com/data/icons/famfamfam_flag_icons/cn.png"),
        HUNGARIAN("hu", "Hungarian", "https://cdn1.iconfinder.com/data/icons/famfamfam_flag_icons/hu.png");

        Language(String code, String name, String flagURL) {
            mCode = code;
            mName = name;
            mFlagURL = flagURL;
        }

        public String mCode;
        public String mName;
        public String mFlagURL;
    }

    public static class Message {
        public static final String CONFIG_CHANGED = "com.exarlabs.android.com.tmlparseandroiddemo.CONFIG_CHANGED";
    }


    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    private static Language mDefaultLanguage = Language.ENGLISH;
    private static Mode mSelecetdMode = Mode.TRANSLATE;
    private static Language mSelectedLanguage = Language.ENGLISH;
    private static Context mContext;

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    public static void init(Context context) {
        mContext = context;
    }

    /**
     * Notify all the listeners that the configuration has changed.
     */
    public static void notifyConfigChange() {
        if (mContext != null) {
            mContext.sendBroadcast(new Intent(Message.CONFIG_CHANGED));
        }
    }

    public static Language getDefaultLanguage() {
        return mDefaultLanguage;
    }

    public static void setDefaultLanguage(Language mDefaultLanguage) {
        TmlMocker.mDefaultLanguage = mDefaultLanguage;
    }

    public static Mode getSelecetdMode() {
        return mSelecetdMode;
    }

    public static void setSelecetdMode(Mode selecetdMode) {
        mSelecetdMode = selecetdMode;
    }

    public static Language getSelectedLanguage() {
        return mSelectedLanguage;
    }

    public static void setSelectedLanguage(Language selectedLanguage) {
        mSelectedLanguage = selectedLanguage;
    }

    /**
     * @return the list of supported languages.
     */
    public static List<Language> getSupportedLanguages() {
        return new ArrayList<>(Arrays.asList(Language.values()));
    }


    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    public void saveTranslation() {

    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
