package com.exarlabs.android.com.tmlparseandroiddemo.ui.food;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.FoodProvider;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.BaseActivity;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.HeaderGridView;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.translator.TranslatorSettingsDialogFragment;
import com.exarlabs.android.com.tmlparseandroiddemo.ui.utils.SimpleTwoFingerDoubleTapDetector;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

public class FoodListActivity extends BaseActivity implements View.OnTouchListener {

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

    @Bind (R.id.header)
    public LinearLayout mHeader;

    @Bind (R.id.foodlist)
    public HeaderGridView mFoodList;

    @Bind (R.id.header_image)
    public ImageView mHeaderImage;

    private FoodAdapter adapter;

    private FoodProvider foodProvider;

    private SimpleTwoFingerDoubleTapDetector mTouchDetector;


    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        // creating a new food provider
        foodProvider = new FoodProvider();

        // create the touch detector
        mTouchDetector = new SimpleTwoFingerDoubleTapDetector() {
            @Override
            public void onTwoFingerDoubleTap() {
                TranslatorSettingsDialogFragment settings = TranslatorSettingsDialogFragment.newInstance();
                settings.show(getFragmentManager(), TranslatorSettingsDialogFragment.class.getSimpleName());
            }
        };

        // Init the UI
        unitUI();
    }

    private void unitUI() {

        // Load the header image
        Picasso.with(this).load(getString(R.string.header_image_url)).into(mHeaderImage);

        // add the header
        ((ViewGroup) mHeader.getParent()).removeView(mHeader);
        mFoodList.addHeaderView(mHeader);

        adapter = new FoodAdapter(this);
        adapter.addAll(foodProvider.getFoodList());
        mFoodList.setAdapter(adapter);
        mFoodList.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mTouchDetector.onTouchEvent(event)) {
            return true;
        }
        return super.onTouchEvent(event);
    }
}
