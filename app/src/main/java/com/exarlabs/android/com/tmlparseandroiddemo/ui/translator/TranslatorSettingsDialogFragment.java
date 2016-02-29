package com.exarlabs.android.com.tmlparseandroiddemo.ui.translator;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.exarlabs.android.com.tmlparseandroiddemo.R;
import com.exarlabs.android.com.tmlparseandroiddemo.business.tml.TmlMocker;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by becze on 2/26/2016.
 */
public class TranslatorSettingsDialogFragment extends DialogFragment {

    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    /**
     * @return newInstance of TranslatorSettingsDialogFragment
     */
    public static TranslatorSettingsDialogFragment newInstance() {
        return new TranslatorSettingsDialogFragment();
    }

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    @Bind (R.id.translate_mode_switch)
    public Switch mTranslateModeSwitch;

    @Bind (R.id.language_selector_group)
    public RadioGroup mRadioGroup;


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
        View layout = inflater.inflate(R.layout.translator_settings, null);
        ButterKnife.bind(this, layout);

        mTranslateModeSwitch.setChecked(TmlMocker.getSelecetdMode() == TmlMocker.Mode.TRANSLATE);


        for (TmlMocker.Language language : TmlMocker.getSupportedLanguages()) {
            RadioButton button = new RadioButton(getActivity());
            button.setText(language.mName);
            button.setTag(language);
            mRadioGroup.addView(button);
        }

        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) mRadioGroup.getChildAt(i);
            TmlMocker.Language language = (TmlMocker.Language) button.getTag();
            if (language == TmlMocker.getSelectedLanguage()) {
                button.setChecked(true);
            }
        }

    }

    @OnClick (R.id.apply_settings)
    public void onApply() {

        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            RadioButton button = (RadioButton) mRadioGroup.getChildAt(i);
            if (button.isChecked()) {
                TmlMocker.Language language = (TmlMocker.Language) button.getTag();
                TmlMocker.setSelectedLanguage(language);
            }
        }

        // set the edit mode
        TmlMocker.setSelecetdMode(mTranslateModeSwitch.isChecked() ? TmlMocker.Mode.TRANSLATE : TmlMocker.Mode.NORMAL);
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
