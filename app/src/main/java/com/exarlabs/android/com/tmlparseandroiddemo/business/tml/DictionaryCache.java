package com.exarlabs.android.com.tmlparseandroiddemo.business.tml;

import java.util.HashMap;
import java.util.List;

import com.exarlabs.android.com.tmlparseandroiddemo.model.dictionary.DictionaryTuple;

/**
 * Optimized cache for holding the dictionary values
 * Created by becze on 2/29/2016.
 */
public class DictionaryCache {

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

    // cache hash map
    private HashMap<String, HashMap<String, String>> mLanguageCache;
    private boolean mInitialized = false;

    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    public DictionaryCache() {
        mLanguageCache = new HashMap<>();
    }


    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------


    /**
     * @param languageCode
     *
     * @return Returns a cache based on the language code. Note it never return null.
     */
    public HashMap<String, String> getCacheForLanguage(String languageCode) {
        HashMap<String, String> languageMap = mLanguageCache.get(languageCode);

        // Just create a enw empty language map
        if (languageMap == null) {
            languageMap = new HashMap<>();
        }

        mLanguageCache.put(languageCode, languageMap);

        return languageMap;
    }

    /**
     * Returns the translation for the given language and the key.
     * Note: it return null if there is no such translation.
     *
     * @param languageCode
     * @param key
     *
     * @return
     */
    public String getTranslation(String languageCode, String key) {
        return getCacheForLanguage(languageCode).get(key);
    }

    /**
     * @param languageCode
     * @param key
     *
     * @return true if there is a valid translation for the given string
     */
    public boolean hasTranslation(String languageCode, String key) {
        return getTranslation(languageCode, key) != null;
    }

    /**
     * Adds a new translation to the cache.
     * @param languageCode
     * @param key
     * @param translation
     */
    public void addTranslation (String languageCode, String key, String translation) {
        HashMap<String, String> languageCache = getCacheForLanguage(languageCode);
        languageCache.put(key, translation);
    }

    public void init(List<DictionaryTuple> tuples) {
        mLanguageCache.clear();

        for (DictionaryTuple tuple : tuples) {
            HashMap<String, String> cacheForLanguage = getCacheForLanguage(tuple.getLanguageCode());
            cacheForLanguage.put(tuple.getKey(), tuple.getValue());
        }

        mInitialized = true;
    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------


    public boolean isInitialized() {
        return mInitialized;
    }

    public void setInitialized(boolean initialized) {
        mInitialized = initialized;
    }
}
