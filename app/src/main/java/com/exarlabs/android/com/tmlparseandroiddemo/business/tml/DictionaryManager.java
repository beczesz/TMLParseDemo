package com.exarlabs.android.com.tmlparseandroiddemo.business.tml;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.AsyncTask;

import com.exarlabs.android.com.tmlparseandroiddemo.model.dictionary.DictionaryTuple;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

/**
 * Manages the dictionary rows in the database. It also helps with syncronization with the server.
 * Created by becze on 2/29/2016.
 */
public class DictionaryManager {


    // ------------------------------------------------------------------------
    // TYPES
    // ------------------------------------------------------------------------
    private static class Holder {
        static final DictionaryManager INSTANCE = new DictionaryManager();
    }

    /**
     * Listener for cache events
     */
    public interface CacheListener {

        /**
         * Called when the cache is synchronized
         */
        void onCacheSynchronized();
    }

    // ------------------------------------------------------------------------
    // STATIC FIELDS
    // ------------------------------------------------------------------------


    // ------------------------------------------------------------------------
    // STATIC METHODS
    // ------------------------------------------------------------------------
    public static DictionaryManager getInstance() {
        return Holder.INSTANCE;
    }

    // ------------------------------------------------------------------------
    // FIELDS
    // ------------------------------------------------------------------------

    private final DictionaryCache mDictionaryCache;
    // ------------------------------------------------------------------------
    // CONSTRUCTORS
    // ------------------------------------------------------------------------

    private DictionaryManager() {
        mDictionaryCache = new DictionaryCache();
    }

    // ------------------------------------------------------------------------
    // METHODS
    // ------------------------------------------------------------------------

    public void init(Context context) {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(context);

        // Add your initialization code here
        ParseObject.registerSubclass(DictionaryTuple.class);
        Parse.initialize(context);

        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }


    /**
     * Starts a full synchronization with the server.
     * @param cacheListener
     */
    public void fullSyncWithServer(final CacheListener cacheListener) {

        ParseQuery<DictionaryTuple> query = DictionaryTuple.getQuery();
        query.findInBackground(new FindCallback<DictionaryTuple>() {
            @Override
            public void done(final List<DictionaryTuple> objects, final ParseException e) {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            if (e == null) {
                                for (DictionaryTuple tuple : objects) {
                                    tuple.pin();
                                }
                            }
                            mDictionaryCache.init(objects);
                        } catch (ParseException e1) {
                            e1.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        if (cacheListener != null) {
                            cacheListener.onCacheSynchronized();
                        }
                    }
                }.execute();
            }
        });
    }


    /**
     * Queries the local cache for all of the translations.
     * Note: Call this from a background thread.
     *
     * @param key
     *
     * @return
     */
    public List<DictionaryTuple> getTranslationsFromLocalDatastore(String key) {
        try {
            ParseQuery<DictionaryTuple> query = DictionaryTuple.getQuery();
            query.whereEqualTo(DictionaryTuple.KEY, key);
            query.fromLocalDatastore();
            return query.find();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }


    /**
     * Queries the local cache for all of the translations.
     * Note: Call this from a background thread.
     *
     * @param key
     * @param callback
     *
     * @return
     */
    public void getTranslationsFromLocalDatastore(String key, FindCallback<DictionaryTuple> callback) {
        ParseQuery<DictionaryTuple> query = DictionaryTuple.getQuery();
        query.whereEqualTo(DictionaryTuple.KEY, key);
        query.fromLocalDatastore();
        query.findInBackground(callback);
    }

    /**
     * Queries the local cache for all of the translations.
     * Note: Call this from a background thread.
     *
     * @param key
     * @param callback
     *
     * @return
     */
    public void getTranslationsFromLocalDatastore(String language, String key, FindCallback<DictionaryTuple> callback) {
        ParseQuery<DictionaryTuple> query = DictionaryTuple.getQuery();
        query.whereEqualTo(DictionaryTuple.LANGUAGE_CODE, language);
        query.whereEqualTo(DictionaryTuple.KEY, key);
        query.fromLocalDatastore();
        query.findInBackground(callback);
    }

    public void addNewTranslation(TmlMocker.Language language, String key, final String translation) {

        if (!mDictionaryCache.hasTranslation(language.mCode, key)) {
            // Create a new tuple
            DictionaryTuple tuple = new DictionaryTuple();
            tuple.setLanguageCode(language.mCode);
            tuple.setKey(key);
            tuple.setValue(translation);
            tuple.pinInBackground();
            tuple.saveEventually();
        } else {
            getTranslationsFromLocalDatastore(language.mCode, key, new FindCallback<DictionaryTuple>() {
                @Override
                public void done(List<DictionaryTuple> objects, ParseException e) {
                    if (objects.size() > 0) {
                        DictionaryTuple dictionaryTuple = objects.get(0);
                        dictionaryTuple.setValue(translation);
                        dictionaryTuple.saveInBackground();
                    }
                }
            });
        }

        // add to the cache as well
        mDictionaryCache.addTranslation(language.mCode, key, translation);
    }

    public String getTranslation(String languageCode, String key) {
        return mDictionaryCache.getTranslation(languageCode, key);
    }

    public boolean hasTranslation(String languageCode, String key) {
        return mDictionaryCache.hasTranslation(languageCode, key);
    }

    // ------------------------------------------------------------------------
    // GETTERS / SETTTERS
    // ------------------------------------------------------------------------
}
