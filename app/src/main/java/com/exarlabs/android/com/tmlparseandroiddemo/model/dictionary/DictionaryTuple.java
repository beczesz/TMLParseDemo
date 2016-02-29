package com.exarlabs.android.com.tmlparseandroiddemo.model.dictionary;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * * Represents a jog entry with date, distance and time.
 * Created by becze on 10/29/2015.
 */
@ParseClassName ("DictionaryTuple")
public class DictionaryTuple extends ParseObject {


    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------
    public static final String LANGUAGE_CODE = "language_code";

    public static final String KEY = "key";

    public static final String VALUE = "value";


    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    public DictionaryTuple() {
    }


    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------

    public static final ParseQuery<DictionaryTuple> getQuery() {
        return ParseQuery.getQuery(DictionaryTuple.class);
    }

    public String getLanguageCode() {
        return getString(LANGUAGE_CODE);
    }

    public void setLanguageCode(String code) {
        put(LANGUAGE_CODE, code);
    }


    public String getKey() {
        return getString(KEY);
    }

    public void setKey(String key) {
        put(KEY, key);
    }

    public String getValue() {
        return getString(VALUE);
    }

    public void setValue(String value) {
        put(VALUE, value);
    }
}
