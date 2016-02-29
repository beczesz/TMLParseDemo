package com.exarlabs.android.com.tmlparseandroiddemo.ui.translator;

import java.util.List;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.DictionaryManager;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.TmlMocker;
import com.exarlabs.android.com.tmlparseandroiddemo.model.dictionary.DictionaryTuple;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Editor fragment for inline translations
 * Created by becze on 2/26/2016.
 */
public class TranslatorEditorDialogFragment extends DialogFragment {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------


    public static class RowHolder {
        private TmlMocker.Language mLanguage;

        public RowHolder(View v) {
            ButterKnife.bind(this, v);
        }

        @Bind (R.id.flag_icon)
        public ImageView mFlagIcon;

        @Bind (R.id.translated_text)
        public EditText mTranslatedText;

        public void setLanguage(TmlMocker.Language language) {
            mLanguage = language;
        }

        public TmlMocker.Language getLanguage() {
            return mLanguage;
        }
    }

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    private static final String KEY_DICTIONARY_KEY = "DICTIONARY_KEY";
    private DictionaryManager mDictionaryManager;
    private List<DictionaryTuple> mDictionaryTuples;
    private String mKey;

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    /**
     * @return newInstance of TranslatorSettingsDialogFragment
     */
    public static TranslatorEditorDialogFragment newInstance(String key) {
        Bundle args = new Bundle();
        args.putString(KEY_DICTIONARY_KEY, key);
        TranslatorEditorDialogFragment fragment = new TranslatorEditorDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    @Bind (R.id.inline_translation_container)
    public LinearLayout mContainer;


    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infalte the layout
        View layout = inflater.inflate(R.layout.inline_editor_layout, null);
        ButterKnife.bind(this, layout);

        // get the key from the arguments
        mKey = getArguments().getString(KEY_DICTIONARY_KEY);

        // Initialize the dictionary manager
        mDictionaryManager = DictionaryManager.getInstance();

        populateTranslations();

        return layout;
    }


    /**
     * For each of the supported languages we insert one row.
     */
    private void populateTranslations() {
        // first remove all of the children
        mContainer.removeAllViews();

        for (TmlMocker.Language language : TmlMocker.getSupportedLanguages()) {
            // inflate new a row
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.inline_editor_row, null);
            final RowHolder rowHolder = new RowHolder(row);

            // get the flag image
            Picasso.with(getActivity()).load(language.mFlagURL).resizeDimen(R.dimen.flag_width, R.dimen.flag_height).into(rowHolder.mFlagIcon);

            String translation = mDictionaryManager.getTranslation(language.mCode, mKey);
            rowHolder.mTranslatedText.setText(translation != null ? translation : "");
            rowHolder.setLanguage(language);
            rowHolder.mTranslatedText.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    rowHolder.mTranslatedText.setText("(" + rowHolder.getLanguage().mCode + ") " + mKey);
                    return false;
                }
            });

            row.setTag(rowHolder);
            mContainer.addView(row);
        }

    }

    @OnClick (R.id.apply_settings)
    public void onApply() {

        // For each of the row we check if we have an available translation and we save it.
        for (int i = 0; i < mContainer.getChildCount(); i++) {
            View row = mContainer.getChildAt(i);
            RowHolder holder = (RowHolder) row.getTag();

            // Update the laready existing translations
            String transaltionText = holder.mTranslatedText.getText().toString().trim();
            if (!TextUtils.isEmpty(transaltionText)) {
                mDictionaryManager.addNewTranslation(holder.getLanguage(), mKey, transaltionText);
            }
        }

        // set the edit mode
        TmlMocker.notifyConfigChange();
        dismiss();
    }

    @OnClick (R.id.cancel_settings)
    public void onCancel() {
        dismiss();
    }


    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
