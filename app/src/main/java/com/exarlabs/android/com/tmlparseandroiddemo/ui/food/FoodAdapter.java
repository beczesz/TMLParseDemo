package com.exarlabs.android.com.tmlparseandroiddemo.ui.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.FoodProvider;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by becze on 2/26/2016.
 */
public class FoodAdapter extends ArrayAdapter<FoodProvider.Food> {


    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    public static class FoodCardHolder {
        public FoodCardHolder(View v) {
            ButterKnife.bind(this, v);
        }

        @Bind (R.id.background_image)
        public ImageView mBackgroundImage;

        @Bind (R.id.title)
        public TextView mTitle;

        @Bind (R.id.description)
        public TextView mDescription;
    }

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    private final int mResId;
    private final Context mContext;

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    public FoodAdapter(Context context) {
        super(context, R.layout.food_card_layout);
        mContext = context;
        mResId = R.layout.food_card_layout;
    }

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FoodCardHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResId, null);
            viewHolder = new FoodCardHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (FoodCardHolder) convertView.getTag();
        FoodProvider.Food food = getItem(position);


        Picasso.with(getContext()).load(food.mImageURL).into(viewHolder.mBackgroundImage);
        // TODO for now we solve it by invalidating the text view
        viewHolder.mTitle.setText(food.mTitle);
        viewHolder.mDescription.setText(food.mSmallDescription);
        return convertView;
    }


    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
