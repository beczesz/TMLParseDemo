package com.exarlabs.android.com.tmlparseandroiddemo;

import android.app.Application;
import android.graphics.Typeface;

import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.DictionaryManager;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.TmlMocker;
import com.facebook.stetho.Stetho;
import com.norbsoft.typefacehelper.TypefaceCollection;
import com.norbsoft.typefacehelper.TypefaceHelper;

/**
 * Main application file
 * Created by becze on 2/26/2016.
 */
public class TMLParseApplication extends Application {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------


    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize  the libs
        initLibs();
    }

    private void initLibs() {
        if (BuildConfig.DEBUG) {
            // Initialize the Layout Cats
            Stetho.initializeWithDefaults(this);
        }

        initMocker();
        initTypeFaceHelper();
        initParse();
    }

    private void initMocker() {
        // register the mocker
        TmlMocker.init(this);
    }

    /**
     * Provides custom fonts throughout the application
     */
    private void initTypeFaceHelper() {
        //@formatter:off
        // Initialize typeface helper
        TypefaceCollection typeface = new TypefaceCollection.Builder()
                .set(Typeface.NORMAL, Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf"))
                .set(Typeface.BOLD, Typeface.createFromAsset(getAssets(), "fonts/Roboto-Medium.ttf"))
                .create();
        TypefaceHelper.init(typeface);
        //@formatter:on
    }

    /**
     * Initialize the framework wich connects to the Parse server-side technology
     */
    private void initParse() {
        DictionaryManager.getInstance().init(this);
    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
