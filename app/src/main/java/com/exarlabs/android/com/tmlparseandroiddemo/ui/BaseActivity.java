package com.exarlabs.android.com.tmlparseandroiddemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.norbsoft.typefacehelper.TypefaceHelper;

import butterknife.ButterKnife;

/**
 * Base activity for all the Activities, it provides some common operation for all of the sub-activities.
 * Created by becze on 9/21/2015.
 */
public class BaseActivity extends AppCompatActivity {

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

    public Toolbar mToolbar;

    public FrameLayout mContentLayout;



    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        // Get an inflater
        ButterKnife.bind(this);
        TypefaceHelper.typeface(this);
    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
