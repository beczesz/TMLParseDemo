package com.exarlabs.android.com.tmlparseandroiddemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.DictionaryManager;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.food.FoodListActivity;
import com.victor.loading.newton.NewtonCradleLoading;

import butterknife.Bind;

/**
 * Created by becze on 2/29/2016.
 */
public class SplashScreenActivity extends BaseActivity {
    private DictionaryManager mDictionaryManager;

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

    @Bind (R.id.loader)
    public NewtonCradleLoading mLoader;

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        mLoader.start();
        mDictionaryManager = DictionaryManager.getInstance();
        mDictionaryManager.fullSyncWithServer(new DictionaryManager.CacheListener() {
            @Override
            public void onCacheSynchronized() {
                startActivity(new Intent(SplashScreenActivity.this, FoodListActivity.class));
                finish();
            }
        });
    }



    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
